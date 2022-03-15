# Room Database

## Dependencies

we must configure the Room Database’s dependencies as shown below:

```gradle
    def room_version = "2.4.2"
    implementation "androidx.room:room-runtime:$room_version"
	kapt "androidx.room:room-compiler:$room_version"
```

[from here](https://developer.android.com/training/data-storage/room#groovy)

For kotlin, apply `kapt` plugin at the top of your `build.gradle` dependency file:

```gradle
apply plugin: 'kotlin-kapt'
```

Create the entity that will be our data class in Room Database, for example, Course.