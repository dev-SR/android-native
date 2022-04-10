# Scaffold

- [Scaffold](#scaffold)
	- [Intro](#intro)
	- [Building Templates with Scaffold](#building-templates-with-scaffold)

## Intro

Compose provides convenient layouts for combining Material Components into common screen patterns. Composables such as Scaffold provide slots for various components and other screen elements.

```kotlin
@Composable
fun Scaffold(
    modifier: Modifier! = Modifier,
    scaffoldState: ScaffoldState! = rememberScaffoldState(),
    topBar: (@Composable () -> Unit)? = {},
    bottomBar: (@Composable () -> Unit)? = {},
    snackbarHost: (@Composable (SnackbarHostState) -> Unit)? = { SnackbarHost(it) },
    floatingActionButton: (@Composable () -> Unit)? = {},
    floatingActionButtonPosition: FabPosition! = FabPosition.End,
    isFloatingActionButtonDocked: Boolean! = false,
    drawerContent: (@Composable @ExtensionFunctionType ColumnScope.() -> Unit)? = null,
    drawerGesturesEnabled: Boolean! = true,
    drawerShape: Shape! = MaterialTheme.shapes.large,
    drawerElevation: Dp! = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color! = MaterialTheme.colors.surface,
    drawerContentColor: Color! = contentColorFor(drawerBackgroundColor),
    drawerScrimColor: Color! = DrawerDefaults.scrimColor,
    backgroundColor: Color! = MaterialTheme.colors.background,
    contentColor: Color! = contentColorFor(backgroundColor),
    content: (@Composable (PaddingValues) -> Unit)?
): Unit
```

- [https://developer.android.com/jetpack/compose/layouts/material#scaffold](https://developer.android.com/jetpack/compose/layouts/material#scaffold)

## Building Templates with Scaffold