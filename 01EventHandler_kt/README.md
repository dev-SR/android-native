# Event Handling in Android - Kotlin

- [Event Handling in Android - Kotlin](#event-handling-in-android---kotlin)
  - [Basic Event Handling](#basic-event-handling)
  - [View Binding](#view-binding)
    - [Snippets](#snippets)

## Basic Event Handling

`activity_main.xml`

```xml
<EditText
        android:id="@+id/etName" />

<Button
        android:id="@+id/btnSubmit"
 />
```

`MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etName = findViewById<EditText>(R.id.etName)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        //Using object - anonymous inner class
        btnSubmit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var name = etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }

        })

        //Using lambda
        btnSubmit.setOnClickListener {
            TODO("Not yet implemented")
        }

        //Using scope function
        with(btnSubmit) {

            setOnClickListener {
                TODO("Not yet implemented")
            }
        }
        //or
        btnSubmit.apply {
            setOnClickListener {
                TODO("Not yet implemented")
            }
        }
    }
}
```



## View Binding

New in Android Studio 3.6, view binding gives you the ability to replace `findViewById` with**generated binding objects** to simplify code, remove bugs, and avoid all the boilerplate of `findViewById`. Using a View without adding null checks can lead to `NullPointerException` because findViewById is not null safe indeed. As a result, Google has introduced View binding as a new approach in addition to Data binding because it is a lighter weight solution for replacing findViewById without using the rest of the Data binding library features.

module-level `build.gradle`

```gradle
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```

Once enabled for a project, view binding will generate a binding class for all of your layouts automatically. You don’t have to make changes to your XML — it’ll automatically work with your existing layouts.

For example, given a layout file called `activity_main.xml`. The generated binding class is called `ActivityMainBinding`. This class has two fields: a TextView called `etName` a Button called `btnSubmit`.

`activity_main.xml`

```xml
<EditText
        android:id="@+id/etName" />

<Button
        android:id="@+id/btnSubmit"
 />
```

To use View Binding in Activity, create **an instance of the binding class**(`ActivityMainBinding`), get the root view, and pass it to `setContentView()`.

```kotlin
class MainActivity : AppCompatActivity() {
    //Names _, __, ___, ..., are reserved in Kotlin
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnSubmit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        })
    }
}
```

Other Possible ways of implementing `setOnClickListener()`


```kotlin
        vb.btnSubmit.setOnClickListener {
            var name = vb.etName.text.toString();
            Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
        }
        // using Scope functions - with
        with(vb) {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }
        // using Scope functions - apply
        vb.apply {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }
```

- [https://howtodoandroid.com/view-binding-android/](https://howtodoandroid.com/view-binding-android/)
- [https://developer.android.com/topic/libraries/view-binding#kts](https://developer.android.com/topic/libraries/view-binding#kts)

> Kotlin Android Extensions is deprecated, which means that using Kotlin `synthetics` for view binding is no longer supported. If your app uses Kotlin synthetics for view binding, use this guide to migrate to `Jetpack` view binding.

```kotlin
// Reference to "name" TextView using `synthetic` properties.
`name`.text = viewModel.nameString

// Reference to "name" TextView using the `binding` class instance.
`binding`.`name`.text = viewModel.nameString
```

[https://developer.android.com/topic/libraries/view-binding/migration](https://developer.android.com/topic/libraries/view-binding/migration)

### Snippets

abbreviation:`vb`,  application context: `groovy,others`

```groovy
buildFeatures {
    viewBinding true
}
```

abbreviation:`vbk`,  application context: `kotlin`

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var vb: $BindingClass$
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = $BindingClass$.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
        $END$
    }
}
```
