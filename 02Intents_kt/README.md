# Intent

- [Intent](#intent)
  - [Intro](#intro)
  - [Intent Filters](#intent-filters)
  - [Explicit Intent](#explicit-intent)
    - [Switching Activivtes Using Intents](#switching-activivtes-using-intents)
    - [Send Data to Activities using Extras](#send-data-to-activities-using-extras)
  - [Implicit Intent](#implicit-intent)

## Intro

Intent is an **messaging object** which passes between components like **services, content providers, activities**etc.

Intent performs the following three tasks mainly:

1. `Starting an Activity`: An Activity starts/performs a task when we pass the Intent object to the content.startActivity() method. When an intent object is passed to startActivity(), it starts a new activity or an existing one.

2. `Starting a Service`: An Intent object is passed to the content.startService() method, to start a Service. We can also send a required request to an existing service.

3. `Delivering a Broadcast`:Passing the Intent object in content.sendBroadcast(), sends messages to broadcast receivers.

Any Intent object contains the following six things :

- Component Name
- Action
- Data
- Category
- Extras
- Flag

Two important things in intent:

- `action`: The general action to be performed, such as `ACTION_VIEW`, `ACTION_EDIT`, `ACTION_MAIN`, etc.
- `data`: The data to operate on, such as a person record in the contacts database, is expressed as a `Uri`

> Note: Uniform Resource Identifier (`URI`) is a string of characters used to identify a resource. A URI identifies a resource either by location, or a name, or both.

- `Extras`: A Bundle object which contains additional information. It is just an extra key-value pair for the component.

There are two types of intents in android:

- `Implicit` and
- `Explicit`.

**1. Explicit Intent**

Explicit Type are those Intents that specifically mention the component that it targets. The Android system calls these components by explicitly mentioning them. We can use Explicit Intent for the purpose of launching an application component. For example, you might start a new activity within your app in response to a user action, or start a service to download a file in the background.

<div align="center">
<img src="img/explicit-intent.jpg" alt="explicit-intent.jpg" width="600px">
</div>

Java:

```java
Intent i = new Intent(getApplicationContext(), ActivityTwo.class);
startActivity(i);
```

**2. Implicit Intent**

Implicit Type of Intents are those that do not mention any component name and only declare the actions.

Implicit Intents specifies these actions that are to be performed. These actions can invoke any application component that can perform these actions. These are useful when your action is important but your application is not capable of performing it.


<div align="center">
 <img src="img/implicit-intent.jpg" alt="implicit-intent.jpg" width="600px">
</div>

<div align="center">
<img src="img/exi.jpg" alt="exi.jpg" width="600px">
</div>

For example, you may write the following code to view the webpage.

Java:

```java
Intent intent=new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://www.geeksforgeeks.org"));
startActivity(intent);
```

- [https://developer.android.com/guide/components/intents-filters](https://developer.android.com/guide/components/intents-filters)

## Intent Filters

**Intent Filters**are expressions in the `Manifest` file of an Android Application. These are responsible for deciding the behavior of the Intent.

- Implicit intent uses the intent filter to serve the user request.
- The intent filter specifies the types of intents that an activity, service, or broadcast receiver can respond.
- Intent filters are declared in the Android manifest file.
- Intent filter must contain `<action>`

Generally, the Intent Filters (`<intent-filter>`) whatever we define in the manifest file can be nested in the corresponding app components and we can specify the type of intents to accept using these three elements.

- `<action>`: It defines the name of an intended action to be accepted and it must be a literal string value of an action, not the class constant.
- `<category>`: It defines the name of an intent category to be accepted and it must be the literal string value of an action, not the class constant.
- `<data>`: It defines the type of data to be accepted and by using one or more attributes we can specify various aspects of the data URI (scheme, host, port, path) and MIME type.

Example:

```xml
<activity
    android:name=".MainActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity
```

## Explicit Intent




### Switching Activivtes Using Intents

### Send Data to Activities using Extras

## Implicit Intent

<div align="center">
<img src="img/ii.gif" alt="ii.gif" width="700px">
</div>

<div align="center">
<img src="img/exi.jpg" alt="exi.jpg" width="700px">
</div>

 How an implicit intent is delivered through the system to start another activity:

 - Activity `A` creates an Intent with an action description and passes it to `startActivity()`.
 - The Android System searches all apps for an intent filter that matches the intent. When a match is found,
 - the system starts the matching activity (Activity `B`) by invoking its `onCreate()` method and passing it the Intent.

- [https://developer.android.com/guide/components/intents-common](https://developer.android.com/guide/components/intents-common)
- [https://developer.android.com/guide/components/intents-filters](https://developer.android.com/guide/components/intents-filters)

```xml
    <Button
        android:id="@+id/btnEmail" />

    <Button
        android:id="@+id/btnBrowse" />

    <Button
        android:id="@+id/btnDial"/>

    <EditText
        android:id="@+id/editText"/>
```

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

  //Phone Call

        vb.btnDial.setOnClickListener {
            val mobile = vb.editText.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_DIAL
            i.data = Uri.parse("tel://$mobile")
            startActivity(i)
        }

  //Browse Webpage
        vb.btnBrowse.setOnClickListener {
            val address = vb.editText.text.toString()
            val i = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://$address")
            }
            startActivity(i)
        }

  //Send Email

  vb.btnEmail.setOnClickListener {
            val email = vb.editText.text.toString()
            val intent = Intent().apply {
                action = Intent.ACTION_SENDTO
                data = Uri.parse("mailto:") // only email apps should handle this
                var emails = listOf(email, "").toTypedArray()
                putExtra(Intent.EXTRA_EMAIL, emails)
                putExtra(Intent.EXTRA_SUBJECT, "Implicit Intents")
                putExtra(Intent.EXTRA_TEXT, "Hello Someone")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else Toast.makeText(
                this, "No app to send email. Please install at least one",
                Toast.LENGTH_SHORT
            ).show()

        }

    }
}
```

`AndroidManifest.xml`

```xml
<activity>
 <intent-filter>
  <action android:name="android.intent.action.SENDTO" />
  <category android:name="android.intent.category.DEFAULT" />
  <data android:scheme="mailto" />
 </intent-filter>
</activity>
```

Custom Function For Sending Email:

```kotlin
//  val addresses = listOf(
//                "fake@address.com", "some@add.com").toTypedArray()
//  val subject = "Some title"
//  val text = "Some message<br><br>New Line<br><br>"
//  intentDataEmailCreation(addresses,subject,text)

    private fun intentDataEmailCreation(
        addresses: Array<String>, subject: String, text: String
    ) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else Toast.makeText(
            this, "No app to send email. Please install at least one",
            Toast.LENGTH_SHORT
        ).show()
    }
```

- [https://developer.android.com/guide/components/intents-common#ComposeEmail](https://developer.android.com/guide/components/intents-common#ComposeEmail)
- [https://betterprogramming.pub/the-imperfect-android-send-email-action-59610dfd1c2d](https://betterprogramming.pub/the-imperfect-android-send-email-action-59610dfd1c2d)
