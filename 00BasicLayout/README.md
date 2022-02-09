# Basic Layout

- [Basic Layout](#basic-layout)
	- [`View` and `ViewGroup` Objects](#view-and-viewgroup-objects)
	- [Containers](#containers)
	- [FrameLayout](#framelayout)
	- [LinearLayout](#linearlayout)
	- [Padding versus margin](#padding-versus-margin)
		- [Android match_parent, wrap_content and fill_parent](#android-match_parent-wrap_content-and-fill_parent)

## `View` and `ViewGroup` Objects

A `View` object is a composition unit. You build a UI by arranging one or more View
objects alongside each other or sometimes embedded in each other. There are two kinds
of views as the Android library defines it, a `“View”` and a `“ViewGroup”`. An example of a
`View` object is a *button* or a *text field*. These objects are meant to be composed alongside
other views, but they are not meant to contain child views; they are intended to stand
alone. A `ViewGroup`, on the other hand, can contain child views which are other views and view groups—it’s the reason why they’re sometimes called *containers*.

<div align="center">
	<img src="img/vg.jpg" alt="vg.jpg" width="600px">
</div>

*Each View object ultimately becomes a Java object at runtime*, but we work
with them as XML elements during design time. We don’t have to worry about how
Android inflates the XML into Java objects because that process is invisible to us.

<div align="center">
<img src="img/cp.jpg" alt="cp.jpg" width="600px">
</div>

The compiler transforms the program source files into Java `byte` codes. The resulting byte
codes are then further converted to a `DEX` file. A `DEX` file is a **Dalvik Executable**;
it’s the executable format that the **Android Runtime**(`ART`) understands. Before the `DEX`
files and other resources get wrapped into an **Android package** (`APK`), it also produces
as a side effect a special file named `“R.class”`. We use the `R.class` to get a
**programmatic reference to the UI elements defined in the layout file.**

## Containers

Apart from creating composite views, the ViewGroup class has another use. They form the
basis for layout managers. *A layout manager is a container responsible for controlling how child views are positioned on the screen, relative to the container and each other*. Android comes with a couple of prebuilt layout managers; Table shows us some of them.

- `LinearLayout`:  Positions the widgets in single `row` or `column`, depending on the selected orientation - `horizontal` or `vertical`. *Each widget can be assigned a `weight` value which determines the amount of space the widget occupies compared to the other widgets*
- `TableLayout`: Arranges the widgets in a *grid format of rows and columns*
- `FrameLayout`: Stacks child views on top of each other. The last entry on the XML layout file is the one on top of the stack
- `RelativeLayout`: Views are positioned relative to other views and the container by specifying *alignments* and *margins* on each view
- `ConstraintLayout`: The ConstraintLayout is the newest layout. It positions widgets relative to each other and the container (like RelativeLayout). But it accomplishes the layout management by using more than just *alignments* and *margins*. It introduces the idea of a `“constraint”` object, which *anchors* a widget to target. This target could be another widget or a container or another anchor point.

## FrameLayout

Android Framelayout is a ViewGroup subclass which is used to specify the position of multiple views **placed on the top of each other** to represent a single view screen.

Generally, we can say FrameLayout simply blocks a particular area on the screen to display a single view. Here, all the child views or elements are added in stack format means the most recently added child will be shown on the top of the screen.

But, we can add multiple children view and control their positions only by using `gravity` attributes in FrameLayout.

<div align="center">
<img src="img/fl.jpeg" alt="fl.jpeg" width="700px">
</div>

Creating New Layouts: Under `res/layout` folder. Create `Layout Resource File` and add the following code:

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_launcher_background" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView" />
</FrameLayout>
```

<div align="center">
<img src="img/flex1.jpg" alt="flex1.jpg" width="700px">
</div>

We can see `TextView` is on top of the other child view - `ImageView`, as Stack with the most recently added child on top.


```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:baselineAlignBottom="false"
        android:paddingBottom="10dp"
        android:textAlignment="center"
        app:srcCompat="@drawable/ic_launcher_background" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|center_horizontal"
        android:layout_marginBottom="20dp"
        android:background="@color/black"
        android:gravity="center"
        android:text="TextView"
        android:textColor="@color/white" />
</FrameLayout>
```

<div align="center">
<img src="img/flex2.jpg" alt="flex2.jpg" width="1000px">
</div>

## LinearLayout

In a `LinearLayout` view group, the UI elements are arranged either *horizontally* or *vertically*.

<div align="center">
<img src="img/lr.png" alt="lr.png" width="400px">
</div>

## Padding versus margin

`Padding` is the space inside the boundaries of a view or element. It is the space between the edges of the view and the view's content, as shown in the figure below.

A view's size includes its padding. The following are commonly used padding attributes:

- `android:padding` - specifies padding for all four edges of the view.
- `android:paddingTop` - specifies padding for the top edge.
- `android:paddingBottom` - specifies padding for the bottom edge.
- `android:paddingStart` - specifies padding for the "starting" edge of the view.
- `android:paddingEnd` - specifies padding for the "ending" edge of the view.
- `android:paddingLeft`-  specifies padding for the left edge.
- `android:paddingRight`-  specifies padding for the right edge.

`Margin` is the space added outside of the view's borders. It is the space from the edge of the view to its parent, as shown in the figure above. The following are commonly used margin attributes:

- `android:layout_margin` - specifies a margin for all four sides of the view.
- `android:layout_marginTop` - specifies extra space on the top side of this view
- `android:layout_marginBottom` - specifies space outside the bottom side of this view.
- `android:layout_marginStart` - specifies space outside the "starting" side of this view.
- `android:layout_marginEnd` - specifies space on the end side of this view.
- `android:layout_marginLeft` - specifies space on the left side of this view.
- `android:layout_marginRight` - specifies space on the right side of this view.

<div align="center">
<img src="img/pm.png" alt="pm.png" width="300px">
</div>

### Android match_parent, wrap_content and fill_parent

`wrap_content` — The component just want to display big enough to enclose its content+padding only.
`fill_parent` -The component want to display as big as its parent, and fill in the remaining spaces.
`match_parent` : to match width and height same as its parent attribute tag.

Conclusion :
`fill_parent` and `match_parent` are the same, used when we want the height or width of a view to be as big as its parent view, fill_parent being deprecated.
`wrap_content` is used when we want the view to occupy only as much space as required by it.

<div align="center">
<img src="img/wmfp.jpg" alt="wmfp.jpg" width="300px">
</div>