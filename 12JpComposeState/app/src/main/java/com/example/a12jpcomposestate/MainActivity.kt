package com.example.a12jpcomposestate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.a12jpcomposestate.ui.theme.ComposeTheme
import kotlin.random.Random

data class TodoItem(val todo_id: Int, val data: String)
class TodoViewModel : ViewModel() {

    // remove the LiveData and replace it with a mutableStateListOf
    //private var _todoItems = MutableLiveData(listOf<TodoItem>())
    //val todoItems: LiveData<List<TodoItem>> = _todoItems

    // state: todoItems -captures the same behavior as the LiveData version.
    var todoItems = mutableStateListOf(TodoItem(1, "A"), TodoItem(2, "A"))
        private set

    // event: addItem
    fun addItem(item: TodoItem) {
        todoItems.add(item)
    }

    // event: removeItem
    fun removeItem(item: TodoItem) {
        todoItems.remove(item)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                val vm: TodoViewModel by viewModels()
                Surface(modifier = Modifier.fillMaxSize()) {
                    TodoActivityScreen(vm)
                }
            }
        }
    }
}

@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
    TodoScreen(
        items = todoViewModel.todoItems,
//        onAddItem = todoViewModel::addItem,
//        onRemoveItem = todoViewModel::removeItem
        vm = todoViewModel
    )
}

@Composable
fun TodoScreen(
    items: List<TodoItem>,
//    onAddItem: (TodoItem) -> Unit,
//    onRemoveItem: (TodoItem) -> Unit
    vm: TodoViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = {
            val id = Random.nextInt(3, 100)
//            onAddItem(TodoItem(id, "$id Added"))
            vm.addItem(TodoItem(id, "$id Added"))
        }) {
            Text(text = "Add")
        }
        LazyColumn {
            items(items) { item ->
                Card(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable {
                        vm.removeItem(item)
                    }) {

                    Text(text = "$item", modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}


@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }

    HelloContent(name = name, onNameChange = { name = it })
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NoState()

        }
    }
}

@Composable
fun NoState() {
    var clickCount = 0
    Column {
        Button(onClick = {
            clickCount++
            Log.d("TAGS", "NoState: $clickCount")
        }) {
            Text(text = "$clickCount times clicked")
        }
    }
}

//@Composable
//fun MutableStateClick() {
//    var clickCount by mutableStateOf(0)//Not recommended
//    Column {
//        Button(onClick = { clickCount++ }) {
//            Text(text = "$clickCount times clicked")
//        }
//    }
//}
@Composable
fun RememberSample() {
    var clickCount by remember { mutableStateOf(0) }
    Column {
        Button(onClick = { clickCount++ }) {
            Text(text = "$clickCount times clicked")
        }
    }
}

@Composable
fun MyApp(vm: ViewModelStore) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (val state = vm.uiState.collectAsState().value) {
            is ViewModelStore.UiState.Loading ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            is ViewModelStore.UiState.Error -> Text(state.message)
            is ViewModelStore.UiState.Success -> {
                Greeting(list = state.data)
            }
            else -> {}
        }
    }
}


@Composable
fun MyAppReview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting(listOf(User("A"), User("B"), User("C"), User("D")))
    }
}

@Composable
fun Greeting(list: List<User>) {
    LazyColumn {
        items(list) { i ->
            Text(text = "Hello ${i.name}!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        HelloScreen()
    }
}