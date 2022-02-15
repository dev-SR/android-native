# Event Handling in Android - Kotlin

- [Event Handling in Android - Kotlin](#event-handling-in-android---kotlin)
  - [View Binding](#view-binding)

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
import com.example.a01eventhandler_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Names _, __, ___, ..., are reserved in Kotlin
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        // 1
        vb.btnSubmit.setOnClickListener {
            var name = vb.etName.text.toString();
            Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
        }

        // 2. using Scope functions - with
        with(vb) {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }
        // 3. using Scope functions - apply
        vb.apply {
            btnSubmit.setOnClickListener {
                var name = vb.etName.text.toString();
                Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            }
        }


    }
}
```

- [https://howtodoandroid.com/view-binding-android/](https://howtodoandroid.com/view-binding-android/)
- [https://developer.android.com/topic/libraries/view-binding#kts](https://developer.android.com/topic/libraries/view-binding#kts)