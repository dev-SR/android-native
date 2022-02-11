# Android

- [Android](#android)
	- [Setup and Settings](#setup-and-settings)
		- [Android Emulator - AMD Processor](#android-emulator---amd-processor)
		- [Changing AVD installation location](#changing-avd-installation-location)

## Setup and Settings

### Android Emulator - AMD Processor

- Windows Features:

<div align="center">
<img src="img/vir.jpg" alt="vir.jpg" width="400px">
</div>

### Changing AVD installation location

Moving avd directory from it's default `C:\users\<username>\.android\avd` to `D:\Others\Installations\Android\AVD`

- Move avd directory to desired location (ex. `D:\Others\Installations\Android\AVD`)

<div align="center">
<img src="img/avd.jpg" alt="avd.jpg" width="600px">
</div>

- Change or add environment variable `ANDROID_AVD_HOME` : `D:\Others\Installations\Android\AVD`

<div align="center">
<img src="img/avd-1.jpg" alt="avd-1.jpg" width="600px">
</div>

- If you have already AVD, change path of `avdName.ini `to `D:\Others\Installations\Android\AVD\Pixel_4_API_32.avd`

<div align="center">
<img src="img/avd-2.jpg" alt="avd-2.jpg" width="600px">
</div>

[https://stackoverflow.com/questions/40501341/how-can-i-change-the-path-to-android-avd-in-my-android-studio](https://stackoverflow.com/questions/40501341/how-can-i-change-the-path-to-android-avd-in-my-android-studio)