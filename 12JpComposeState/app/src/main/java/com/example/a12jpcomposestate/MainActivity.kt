package com.example.a12jpcomposestate

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12jpcomposestate.ui.theme.ComposeTheme


import kotlinx.coroutines.launch

// val -> insures immutability
data class User(val id: String, val name: String)
data class Product(val id: String, val name: String)
data class CartItem(var product: Product, val qty: Int, val selected: Boolean = false)
data class CartInfo(val userid: String, val cartItems: List<CartItem>)

val user = User("1", "A")
val item1 = CartItem(Product("1", "iPhone"), 1)
val item2 = CartItem(Product("2", "Xbox"), 1)
val item3 = CartItem(
    Product("3", "Book"), 1
)
val item4 = CartItem(Product("4", "PS5"), 1)
val cart = CartInfo(user.id, mutableListOf(item1, item2, item3, item4))

class MainViewModel : ViewModel() {
    private val _cartInfo = mutableStateOf<CartInfo?>(null)
    val cartInfo: State<CartInfo?> = _cartInfo

    init {
        _cartInfo.value = cart
    }

    fun increaseQty(product: Product, user: User) {
        viewModelScope.launch {
            var cartInfo = cartInfo.value

            if (user.id == cartInfo?.userid) {
                var cartItems = cartInfo.cartItems.map {
                    if (it.product.id == product.id) {
                        /**
                         * !! DO NOT do this
                         * it.qty = it.qty+1
                         * instead create new object of CartItem -> triggers recomposition
                         * */
                        CartItem(it.product, qty = it.qty + 1, it.selected)
                    } else
                        it
                }
                _cartInfo.value = cartInfo.copy(cartItems = cartItems)
            }
        }
    }

    fun decreaseQty(product: Product, user: User) {
        viewModelScope.launch {
            var cartInfo = cartInfo.value

            if (user.id == cartInfo?.userid) {
                var cartItems = cartInfo.cartItems.map {
                    if (it.product.id == product.id) {
                        var qty = it.qty
                        if (qty > 1) {
                            qty -= 1
                        }
                        CartItem(it.product, qty = qty, it.selected)
                    } else
                        it
                }
                _cartInfo.value = cartInfo.copy(cartItems = cartItems)
            }
        }
    }

    private val _numberOfSelectedCartItem = mutableStateOf<Int>(0)
    val numberOfSelectedCartItem: State<Int> = _numberOfSelectedCartItem
    private val _allCartItemSelected = mutableStateOf(false)
    val allCartItemSelected: State<Boolean> = _allCartItemSelected

    fun selectOrDeselect(index: Int) {
        viewModelScope.launch {
            var cartInfo = cartInfo.value
            var cartItems = cartInfo?.cartItems?.mapIndexed { i, item ->
                if (index == i) {
                    val toggleCurrentSelection = !item.selected
                    CartItem(item.product, item.qty, selected = toggleCurrentSelection)
                } else
                    item
            }
            var countSelection = 0
            cartItems?.forEach {
                if (it.selected) countSelection++;
            }
            _numberOfSelectedCartItem.value = countSelection
            _cartInfo.value = cartItems?.let { cartInfo?.copy(cartItems = it) }
        }
    }

    fun selectOrDeselectAll() {
        viewModelScope.launch {
            var cartInfo = cartInfo.value
            var cartItems: List<CartItem>?
            var countSelection = 0

            if (!_allCartItemSelected.value) {
                cartItems = cartInfo?.cartItems?.map {
                    countSelection++
                    CartItem(it.product, it.qty, selected = true)
                }
                _allCartItemSelected.value = true
            } else {
                cartItems = cartInfo?.cartItems?.map {
                    CartItem(it.product, it.qty, selected = false)
                }
                _allCartItemSelected.value = false
            }
            _numberOfSelectedCartItem.value = countSelection
            _cartInfo.value = cartItems?.let { cartInfo?.copy(cartItems = it) }
        }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            var cartInfo = cartInfo.value
            var cartItems = cartInfo?.cartItems?.filter { !it.selected }
            _allCartItemSelected.value = false
            _numberOfSelectedCartItem.value = 0
            _cartInfo.value = cartItems?.let { cartInfo?.copy(cartItems = it) }
        }
    }


    fun checkout(): List<CartItem>? {
        var cartInfo = cartInfo.value
        var selectedCartItems = cartInfo?.cartItems?.filter { it.selected }
        var notSelectedCartItems = cartInfo?.cartItems?.filter { !it.selected }

        _allCartItemSelected.value = false
        _numberOfSelectedCartItem.value = 0
        _cartInfo.value = notSelectedCartItems?.let { cartInfo?.copy(cartItems = it) }
        return selectedCartItems
    }

}

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme   {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CartView(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartView(viewModel: MainViewModel) {
    val cartInfo = viewModel.cartInfo
    val selectedCount = viewModel.numberOfSelectedCartItem
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            Text(text = "Cart", style = MaterialTheme.typography.h4, modifier = Modifier.weight(1f))
            if (selectedCount.value > 0) {
                Text(
                    text = "Delete(${selectedCount.value})",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.clickable {
                        viewModel.deleteSelected()

                    },
                    color = MaterialTheme.colors.primary
                )
            }
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(cartInfo.value!!.cartItems) { index, item ->
                val color = if (item.selected)
                    Color(0xFFDE3163) else Color(0xFF7BB661)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable {
                            Toast
                                .makeText(context, "${item.product.name}", Toast.LENGTH_SHORT)
                                .show()
                        },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(3.dp, color),
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = item.selected, onCheckedChange = {
                            viewModel.selectOrDeselect(index)
                        })
                        Text(
                            text = "${item.product.name}",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp),
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                enabled = item.qty > 1,
                                onClick = {
                                    viewModel.decreaseQty(item.product, user)
                                }) {
                                Icon(
                                    imageVector = Icons.Rounded.Remove,
                                    contentDescription = "minus",
                                    modifier = Modifier.background(MaterialTheme.colors.primary, RoundedCornerShape(5.dp)),
                                )
                            }
                            Text(
                                text = "${item.qty}"
                            )
                            IconButton(onClick = {
                                viewModel.increaseQty(item.product, user)
                            }) {
                                Icon(
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = "add",
                                    modifier = Modifier.background(MaterialTheme.colors.primary, RoundedCornerShape(5.dp)),
                                )
                            }
                        }

                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            if (cartInfo.value!!.cartItems.isEmpty()) {
                Text(
                    text = "Cart is Empty",
                    modifier = Modifier.padding(20.dp),
                    color = Color.LightGray,
                )
            } else {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        Checkbox(checked = viewModel.allCartItemSelected.value, onCheckedChange = {
                            viewModel.selectOrDeselectAll()
                        })
                        Text(text = "All", modifier = Modifier.padding(start = 4.dp))
                    }

                    Button(
                        enabled = selectedCount.value > 0,
                        onClick = {
                            val items = viewModel.checkout()
                            Toast.makeText(context, "${items.toString()}", Toast.LENGTH_SHORT)
                                .show()
                        },
                    ) {
                        Text(
                            text = "Checkout(${selectedCount.value})",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}



@Preview(name = "Light Mode", showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    val viewModel = MainViewModel()
    ComposeTheme {
        CartView(viewModel)
    }
}
