# Event Handling in Android - Java

- [Event Handling in Android - Java](#event-handling-in-android---java)
  - [Android View Binding - Button Onclick Function](#android-view-binding---button-onclick-function)
  - [Android View Binding - Button setOnclickListener](#android-view-binding---button-setonclicklistener)
    - [Introduction to Function Type, Callbacks - typescript/react](#introduction-to-function-type-callbacks---typescriptreact)
    - [Function Type,CallBacks in java??](#function-typecallbacks-in-java)
    - [Bug: Calling `getText()` inside `OnCreate()`](#bug-calling-gettext-inside-oncreate)
    - [Example 1](#example-1)
    - [Example 2: Login Validation](#example-2-login-validation)
  - [Jetpack View Binding 🚀🚀](#jetpack-view-binding-)
    - [Setup View Binding](#setup-view-binding)
    - [Usage](#usage)
    - [Use view binding in activities](#use-view-binding-in-activities)
    - [Snippets](#snippets)
  - [Working with Different Types of Input Views](#working-with-different-types-of-input-views)
    - [CheckBox](#checkbox)
    - [Radio Buttons](#radio-buttons)
    - [Spinner](#spinner)
      - [Populate the Spinner with User Choices](#populate-the-spinner-with-user-choices)
      - [Creating Spinner Dynamically](#creating-spinner-dynamically)

## Android View Binding - Button Onclick Function

`activity_main.xml`

```xml
    <Button
        android:onClick="onButtonClick"/>
```

`MainActivity.java`

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void  onButtonClick(View v){
        Log.d("BTN","Button Clicked");
    }
}
```

<div align="center">
<img src="img/btn.gif" alt="btn.gif" width="800px">
</div>

## Android View Binding - Button setOnclickListener

### Introduction to Function Type, Callbacks - typescript/react

simple javascript callback function:

```html
<button id="myBtn">Try it</button>

<script>
function callback() {
  alert ("Hello World!");
}

const btn = document.getElementById("myBtn");
btn.addEventListener("click", callback);
</script>
```

callback example with react:

```tsx
export default function App() {
  const clickHandler = (e) => {
    alert("Button Clicked");
  };

  return (
    <div className="App">
      <button onClick={clickHandler}>Download</button>
    </div>
  );
}
```

Function types with callbacks:

```tsx
import React from "react";
// Function type
type CallBackType = (n: number) => void;

// callback function
function AfterDownload(n: number) {
  console.log("Download Completed....");
  alert(`completed in ${n} sec.`);
}
// const AfterDownload: CallBackType = (n) =>{
//   console.log("Download Completed....");
//   alert(`completed in ${n} sec.`);
// }

// callback handler
function Downloader(cb: CallBackType) {
  console.log("Starting Download....");
  const time_taken: number = 5000;
   // calling callback function
  setTimeout(function () {
    cb(time_taken);
  }, time_taken);
}

// Function type
interface OnClickListener {
  (event: React.MouseEvent<HTMLButtonElement>): void;
}
// type ClickHandlerCallBackFunctionType = (
//   event: React.MouseEvent<HTMLButtonElement>
// ) => void;

export default function App() {
   // callback
  const clickHandler: OnClickListener = (e) => {
    console.log("Button Clicked")
     // calling callback handler function
    Downloader(AfterDownload);
  };

  return (
    <div className="App">
      <h1>Hello CodeSandbox</h1>
      <button onClick={clickHandler}>Download</button>
    </div>
  );
}

```

### Function Type,CallBacks in java??

Before Java 8, we would usually create a class for every case where we needed to encapsulate a single piece of functionality because In java we can't use a function **stand alone** or as a callback. Instead we use `Single Abstract Method Interfaces (SAM Interfaces)`.For example Android's `setOnClickListener()` method  accepts a SAM interface called `View.OnClickListener` .

```java
public interface OnClickListener {
    void onClick(View var1);
}
```

We can use a function(`onClick`) as a callback by implementing the `OnClickListener` interface.

```java
class OnClickCallback implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        Log.d("BTN","Button Clicked");
    }
}
```

Then we pass the `OnClickCallback` object to the `setOnClickListener` method.

```java
Button btn = findViewById(R.id.btn);
btn.setOnClickListener(new OnClickCallback());
```

Comparing Callback passing in `java` with `javascript`:

`javascript`:

```js
function callback() {
  alert ("Hello World!");
}
// const callback = () => {alert ("Hello World!");}

const btn = document.getElementById("myBtn");
btn.addEventListener("click", callback);

```

```java
class OnClickCallback implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        Log.d("BTN","Button Clicked");
    }
}

Button btn = findViewById(R.id.btn);
btn.setOnClickListener(new OnClickCallback());
```

Using An **Anonymous Class** we can declare and instantiate a class at the same time.

```java
btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BTN", "Button Clicked");
            }
});
// class OnClickCallback implements View.OnClickListener{
//     @Override
//     public void onClick(View view) {
//         Log.d("BTN","Button Clicked");
//     }
// }
// btn.setOnClickListener(new OnClickCallback());


```

Above code still implied a lot of unnecessary boilerplate code to define something that served as a primitive function representation.

Java 8 brought a powerful new syntactic improvement in the form of **lambda expressions**. A lambda is an anonymous function that we can handle as a first-class language citizen. For instance, we can pass it to or return it from a method.

 **Any interface with aSAM(Single Abstract Method) is a functional interface**, and its implementation may be treated as **lambda expressions**.

```java
View.OnClickListener cb= (view) -> Log.d("BTN","clicked");
btn.setOnClickListener(cb);
// btn.setOnClickListener(view -> Log.d("BTN", "Button Clicked"));

// 1.
//class OnClickCallback implements View.OnClickListener{
//
//    @Override
//    public void onClick(View view) {
//        Log.d("BTN","Button Clicked");
//    }
//}
// btn.setOnClickListener(new OnClickCallback());

// 2.
//  btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("BTN", "Button Clicked");
//            }
//        });
```

Now, Comparing Callback passing in `java` with `javascript`:

```ts
// interface OnClickLister {
//  (e: MouseEvent): void;
// }
// const callback: OnClickLister = (e: MouseEvent) => {
//  console.log('first');
// };
const callback = (e) => console.log('first';
};
const btn = document.getElementById("myBtn");
btn.addEventListener("click", callback);

```

```java
// class View{
//     public interface OnClickListener {
//         void onClick(View var1);
//     }
// }
Button btn = (Button) findViewById(R.id.btn);
View.OnClickListener callback = (view) -> Log.d("BTN","clicked");
btn.setOnClickListener(callback);
```

> **Full Code:**

`activity_main.xml`

```xml
    <Button
        android:id="@+id/btn"/>
```

`MainActivity.java`

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(view -> Log.d("BTN", "Button Clicked"));

    }
}
```
### Bug: Calling `getText()` inside `OnCreate()`


```java
public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_layout);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Calling `getText()` inside `OnCreate()`
        String email = etEmail.getText().toString();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // then referring `email` inside `onClick()`
                Log.d("BTN", "Login Successful: " + email);
            }
        });
    }
}
```

But as we run the application and we see the log, we can see that the `email` is not available.

<div align="center">
<img src="img/getText.gif" alt="getText.gif" width="900px">
</div>

Beacuse `getText()` is called inside `OnCreate()` which is a `protected` method. Whereas, `OnClick()` is a `public` method. So, we can't access `email` inside `OnClick()`.

To solve this problem, we have to use `getText().toString()` inside `OnCreate()` and then refer it inside `OnClick()`.

```java
public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_layout);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            Log.d("BTN", "Login Successful: " + email);
        });
    }
}
```

<div align="center">
<img src="img/getText1.gif" alt="getText1.gif" width="900px">
</div>


### Example 1

<div align="center">
<img src="img/ex1.gif" alt="ex1.gif" width="800px">
</div>

```xml
    <EditText
        android:id="@+id/num1"/>

    <EditText
        android:id="@+id/num2"/>

    <Button
        android:id="@+id/btnAdd" />

    <TextView
        android:id="@+id/res" />
```

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example1_layout);
        EditText etNum1 = findViewById(R.id.num1);
        EditText etNum2 = findViewById(R.id.num2);
        Button btnAdd = findViewById(R.id.btnAdd);
        TextView tvResult = findViewById(R.id.res);
        tvResult.setVisibility(View.INVISIBLE);
        etNum1.requestFocus();

        btnAdd.setOnClickListener(view -> {
            int n1 = Integer.valueOf(etNum1.getText().toString());
            int n2 = Integer.valueOf(etNum2.getText().toString());
            int result = n1 + n2;
            String msg = "Result :" + result;
            tvResult.setVisibility(View.VISIBLE);
            tvResult.setText(msg);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
/**
It is not possible to set the gravity of toast in android 11 because this method is deprecated in API 30+
**/
//            Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
//            toast.show();
        });
    }

}
```

### Example 2: Login Validation

<div align="center">
<img src="img/valid.gif" alt="valid.gif" width="500px">
</div>

```java
public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnReset;
    EditText etEmail, etPass;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_layout);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnLogin.setOnClickListener(view -> {
            if (!isEmail(etEmail) || !isPass(etPass)) {
                return;
            }
            Toast.makeText(getApplicationContext(), getData(etEmail, etPass), Toast.LENGTH_LONG).show();
        });
    }


    private boolean isEmail(EditText email) {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private boolean isPass(EditText password) {
        String passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password is too weak");
            return false;
        }
        return true;
    }


    String getData(EditText... fields) {
        StringBuilder s = new StringBuilder("");
        for (EditText field : fields) {
            s.append(field.getText().toString() + "\n");
        }
        return s.toString();
    }
}
```


## Jetpack View Binding 🚀🚀

View binding is a feature that allows us to more easily write code that interacts with views. Once view binding is enabled in a module, **it generates a binding class for each XML layout file present in that module**. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.

New in Android Studio 3.6, view binding gives you the ability to replace `findViewById` with **generated binding objects/classes** to simplify code, remove bugs, and avoid all the boilerplate of `findViewById`.

TL;DR:

- Enable view binding in `build.gradle`
- View binding **generates a binding object for every layout** in your module (`activity_awesome.xml` → `ActivityAwesomeBinding.java`,  `result_profile.xml` -> `ResultProfileBinding.java`)
-**Binding object contains one property for every view with an `id`** in the layout — with the correct type and null-safety
- Full support for both the `Java` programming language and `Kotlin`

Main advantages:

- `Null safety`: view binding creates direct references to views, there’s no risk of a NullPointerException due to an invalid view ID. Also, when a view is only exists regarding some conditions, the field containing its reference in the binding class is marked with @Nullable .
- `Type safety`: All the View binding fields are generated matching the same type as the ones referenced in XML, so there’s no need to typecast. This means that the risk of a class cast exception is much lower, since If for some reason your layout and code doesn’t match, the build will fail at compile time instead at runtime.
- `Speed`: it doesn’t have any build time speed impact since it does not use annotation processors like ButterKnife or DataBinding.

### Setup View Binding

To enable view binding in a module, set the viewBinding build option to true in the module-level `build.gradle ` file.

```xml
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```

### Usage

Once enabled for a project, view binding will **generate a binding class** for all of your layouts automatically. You don’t have to make changes to your XML — it’ll automatically work with your existing layouts.

You can use the b**inding class**whenever you inflate layouts such as `Fragment`, `Activity`, or even a `RecyclerView` `Adapter` (or `ViewHolder`).

For example, given a layout file called `loginpage_layout.xml`:

```java
    <EditText
        android:id="@+id/etEmail" />

    <EditText
        android:id="@+id/etPass" />

    <Button
        android:id="@+id/btnLogin"/>

    <Button
        android:id="@+id/btnReset"
           />
```

The generated binding class is called `LoginpageLayoutBinding` (from `loginpage_layout.xml`). This class has  fields: EditTexts - `etEmail`,`etPass` Buttons - `btnLogin`,`btnReset`.

Every binding class also includes a `getRoot()` method, providing a direct reference for the root view of the corresponding layout file. In this example, the `getRoot()` method in the `LoginpageLayoutBinding` class returns the `ConstraintLayoutroot` view.

### Use view binding in activities

To use View Binding in Activity, **create an instance of the binding clas**s, get the root view, and pass it to `setContentView()`.

```java
public class MainActivity extends AppCompatActivity {
    private LoginpageLayoutBinding _;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _ = LoginpageLayoutBinding.inflate(getLayoutInflater());
        View view = _.getRoot();
        setContentView(view);

        _.btnLogin.setOnClickListener(v -> {
            String email = _.etEmail.getText().toString();
            String pass = _.etPass.getText().toString();
            Toast.makeText(getApplicationContext(), email
                    + " " + pass, Toast.LENGTH_SHORT).show();
        });

   }
}
```

equivalent to:

```java
public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnReset;
    EditText etEmail, etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage_layout);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                Toast.makeText(getApplicationContext(), email
                        + " " + pass, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

### Snippets

abbreviation:`vb`,  application context: `groovy,others`

```groovy
buildFeatures {
    viewBinding true
}
```

abbreviation:`vbj`,  application context: `kotlin`

```kotlin
public class MainActivity extends AppCompatActivity {
    private $BindingClass$ _;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _ = $BindingClass$.inflate(getLayoutInflater());
        View view = _.getRoot();
        setContentView(view);
        $END$
    }
}
```


## Working with Different Types of Input Views

### CheckBox

<div align="center">
<img src="img/checkboxes.png" alt="checkboxes.png" width="350px">
</div>

Checkboxes allow the user to select one or more options from a set.

```xml
    <CheckBox
        android:id="@+id/checkbox_meat" />

    <CheckBox
        android:id="@+id/checkbox_cheese"
 />
```

We can access whether or not a checkbox is checked with:

```java
CheckBox checkCheese = (CheckBox) findViewById(R.id.checkbox_cheese);
// Check if the checkbox is checked
boolean isChecked = checkCheese.isChecked();
// Set the checkbox as checked
checkCheese.setChecked(true);
```

and in our activity, we can manage checkboxes using a checked listener with `OnCheckedChangeListener` as show below:

```java
        CheckBox checkCheese = findViewById(R.id.checkbox_cheese);
        //  new CompoundButton.OnCheckedChangeListener(){}
        checkCheese.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Cheese Checked", Toast.LENGTH_LONG).show();
            Log.d("BTN","checked");
            }
        });
```

Handling Set of Checkboxes:

```java
        CheckBox checkCheese = findViewById(R.id.checkbox_cheese);
        CheckBox checkMeat = findViewById(R.id.checkbox_meat);

        CompoundButton.OnCheckedChangeListener checkListener = (view, checked) -> {

            switch (view.getId()) {
                case R.id.checkbox_meat:
                    if (checked) {
                        Log.d("BTN", "Meat Checked");
                    } else {
                        Log.d("BTN", "Meat UnChecked");
                    }
                    break;
                case R.id.checkbox_cheese:
                    if (checked) {
                        Log.d("BTN", "Cheese Checked");
                    } else {
                        Log.d("BTN", "Cheese UnChecked");

                    }
                    break;
            }
        };

        checkCheese.setOnCheckedChangeListener(checkListener);
        checkMeat.setOnCheckedChangeListener(checkListener);
```

[https://developer.android.com/guide/topics/ui/controls/checkbox.html](https://developer.android.com/guide/topics/ui/controls/checkbox.html)

`xml`

### Radio Buttons

<div align="center">
<img src="img/radiobuttons.png" alt="radiobuttons.png" width="400px">
</div>

Radio buttons allow the user to select one option from a set.

To create each radio button option, create a `RadioButton` in the layout. However, because radio buttons are mutually exclusive, we must group them together inside a `RadioGroup`. By grouping them together, the system ensures that only one radio button can be selected at a time

```xml
<RadioGroup
        android:id="@+id/rdGroup"
        android:orientation="vertical">
        <RadioButton
            android:id="@+id/radio_yes"
            android:text="Yes" />

        <RadioButton
            android:id="@+id/radio_no"
            android:text="No" />
</RadioGroup>
<Button
        android:id="@+id/btnSubmit"
        android:text="Submit"
/>
<Button
        android:id="@+id/btnClear"
        android:text="Clear"
/>
```

and within the activity:

```java
        RadioGroup radioGroup = findViewById(R.id.rdGroup);
        radioGroup.setOnCheckedChangeListener((RadioGroup group, int checkedId) -> {
            switch (checkedId) {
                case R.id.radio_yes:
                    Log.d("BTN", "Yes");
                    break;
                case R.id.radio_no:
                    Log.d("BTN", "No");
                    break;
            }
        });
```

```java
 btnSubmit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(MainActivity.this,
                        "No answer has been selected",
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                // Get the selected Radio Button
                RadioButton radioButton = radioGroup.findViewById(selectedId);

                // Now display the value of selected item
                // by the Toast message
                Toast.makeText(getApplicationContext(),
                        radioButton.getText(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnClear.setOnClickListener(v -> {
            radioGroup.clearCheck();
        });
```

- [https://developer.android.com/guide/topics/ui/controls/radiobutton#java](https://developer.android.com/guide/topics/ui/controls/radiobutton#java)
- [https://www.geeksforgeeks.org/android-how-to-add-radio-buttons-in-an-android-application/](https://www.geeksforgeeks.org/android-how-to-add-radio-buttons-in-an-android-application/)

### Spinner

<div align="center">
<img src="img/spinner.jpg" alt="spinner.jpg" width="400px">
</div>

Spinners provide a quick way to select one value from a set. In the default state, a spinner shows its currently selected value. Touching the spinner displays a dropdown menu with all other available values, from which the user can select a new one.

You can add a spinner to your layout with the Spinner object. You should usually do so in your XML layout with a `<Spinner>` element. For example:

<Spinner
    android:id="@+id/planets_spinner"
/>

To populate the spinner with a list of choices, you then need to specify a `SpinnerAdapter` in your `Activity` or `Fragment` source code.

#### Populate the Spinner with User Choices

The choices you provide for the spinner can come from**any source**, but must be provided through an `SpinnerAdapter`, such as an `ArrayAdapter` if the choices are available in an `array` or a `CursorAdapter` if the choices are available from a database query.

For instance, if the available choices for your spinner are **pre-determined**, you can provide them with a **string array** defined in a **string resource** file:

`spinner_layout.xml`

<Spinner
    android:id="@+id/planets_spinner"
    android:entries="@array/planets_array"
/>

`src/main/res/values/planets_array.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string-array name="planets_array">
        <item>Mercury</item>
        <item>Venus</item>
        <item>Earth</item>
        <item>Mars</item>
    </string-array>
</resources>
```

With an array such as this one, you can use the following code in your `Activity` or `Fragment` to supply the spinner with the array using an instance of `ArrayAdapter`:

```java
        Spinner spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a `default spinner layout`
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        /**
         * `simple_spinner_item` and `simple_spinner_dropdown_item` both are default layout
         * */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
//              Get the selected item out a spinner using:
                String value = spinner.getSelectedItem().toString();
                String value1 = spinner.getItemAtPosition(pos).toString();
                Log.d("BTN", value + " " + pos + " " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
```

#### Creating Spinner Dynamically

`spinner_layout.xml`

```xml
<Spinner
    android:id="@+id/spinner"
/>
```

`MainActivity.java`

```java
        setContentView(R.layout.spinner_layout);
        Spinner spinner = findViewById(R.id.spinner);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Item 5");
        categories.add("Item 6");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("BTN", spinner.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
```