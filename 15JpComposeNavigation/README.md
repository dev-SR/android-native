# Navigation In JetPack Compose

- [Navigation In JetPack Compose](#navigation-in-jetpack-compose)
	- [Setup](#setup)
	- [Getting started](#getting-started)
		- [Creating a NavHost](#creating-a-navhost)
		- [Navigate to a composable](#navigate-to-a-composable)
			- [Navigation and the back stack](#navigation-and-the-back-stack)

## Setup

```groovy
 def nav_version = "2.4.1"
 implementation "androidx.navigation:navigation-compose:$nav_version"
```

[https://developer.android.com/jetpack/compose/navigation#groovy](https://developer.android.com/jetpack/compose/navigation#groovy)

## Getting started

The `NavController` is the central API for the Navigation component. It is stateful and keeps track of the back stack of composables that make up the screens in your app and the state of each screen.

You can create a NavController by using the `rememberNavController`() method in your composable:

```kotlin
val navController = rememberNavController()
```

You should create the `NavController` in the place in your composable hierarchy where all composables that need to reference it have access to it. This follows the principles of `state hoisting` and allows you to use the NavController and the state it provides via `currentBackStackEntryAsState()`to be used as the source of truth for updating composables outside of your screens.

```kotlin
@Composable
fun MyApp() {
    BuildNavigation()
}

@Composable
fun BuildNavigation() {
    val navController = rememberNavController()
    //..
}
```

### Creating a NavHost

Each `NavController` must be associated with a single `NavHost` composable. The NavHost links the NavController with a navigation graph that specifies the composable destinations that you should be able to navigate between. As you navigate between composables, the content of the `NavHost` is automatically `recomposed`. Each composable destination in the navigation graph is associated with a route.

Creating the `NavHost` requires the `NavController` previously created via `rememberNavController()` and the route of the starting destination of your graph.

```kotlin
@Composable
fun BuildNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/list_screen") {
     //..
    }
}
```

You can add to your navigation structure by using the `composable()` method. This method requires that you provide a `route` and the `composable` that should be linked to the destination:

```kotlin
@Composable
fun BuildNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/list_screen") {
        composable(route = "/list_screen") {
            ListScreen(navController = navController)
        }
        composable(route = "/details_screen") {
            DetailsScreen(navController = navController)
        }
    }
}
```

### Navigate to a composable

To navigate to a composable destination in the navigation graph, you must use the `navigate()` method. `navigate()` takes a single String parameter that represents the destination’s route. To navigate from a composable within the navigation graph, call `navigate()`:

```kotlin
@Composable
fun ListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
    ) {
        Text(text = "List")
        Spacer(modifier = Modifier.size(5.dp))
        LazyColumn {
            items(lists) { i ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable {
                            navController.navigate("/details_screen")

                        }, elevation = 4.dp
                ) {
                    Text(text = "$i", modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "DetailsScreen")
        Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = {
//            navController.navigate("/list_screen")
            navController.popBackStack()
        }) {
            Text(text = "Go to List")
        }
    }
}
```

<div align="center">
<img src="img/ss1.gif" alt="ss1.gif" width="400px">
</div>


#### Navigation and the back stack

You should only call navigate() as part of a callback and not as part of your composable itself, to avoid calling navigate() on every recomposition.

By default, navigate() adds your new destination to the back stack. You can modify the behavior of navigate by attaching additional navigation options to our navigate() call:

```kotlin
// Pop everything up to the "home" destination off the back stack before
// navigating to the "friendslist" destination
navController.navigate("friendslist") {
    popUpTo("home")
}

// Pop everything up to and including the "home" destination off
// the back stack before navigating to the "friendslist" destination
navController.navigate("friendslist") {
    popUpTo("home") { inclusive = true }
}

// Navigate to the "search” destination only if we’re not already on
// the "search" destination, avoiding multiple copies on the top of the
// back stack
navController.navigate("search") {
    launchSingleTop = true
}
```

See the [popUpTo](https://developer.android.com/guide/navigation/navigation-navigate#back-stack) guide for more use cases.

