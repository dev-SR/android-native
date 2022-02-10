# Basic Layout

- [Basic Layout](#basic-layout)
  - [`View` and `ViewGroup` Objects](#view-and-viewgroup-objects)
  - [Containers](#containers)
  - [Android Layout Attributes](#android-layout-attributes)
    - [Padding versus margin](#padding-versus-margin)
    - [Android match_parent, wrap_content and fill_parent](#android-match_parent-wrap_content-and-fill_parent)
  - [FrameLayout](#framelayout)
  - [LinearLayout](#linearlayout)
    - [Layout Weight](#layout-weight)
  - [Relative Layout](#relative-layout)
    - [Center relative to Parent View](#center-relative-to-parent-view)
    - [Align by the parent view](#align-by-the-parent-view)
    - [Place new View relative to existing sibling View](#place-new-view-relative-to-existing-sibling-view)
    - [Align new View relative to existing sibling View](#align-new-view-relative-to-existing-sibling-view)
  - [Constraint Layout](#constraint-layout)
    - [Correct Ways of Anchoring](#correct-ways-of-anchoring)
    - [layout_width/height properties:](#layout_widthheight-properties)

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

## Android Layout Attributes

- `android:id` : This is the ID which uniquely identifies the view
- `android:layout_width` : This is the width of the layout
- `android:layout_height` : This is the height of the layout
- `android:layout_margin` : This is the extra space outside of the view. For example if you give android:marginLeft=20dp, then the view will be arranged after 20dp from left
- `android:layout_padding` : This is similar to android:layout_margin except that it specifies the extra space inside the view
- `android:layout_gravity` : This specifies how child Views are positioned
- `android:layout_weight` : This specifies how much of the extra space in the layout should be allocated to the view
- `android:layout_x` : This specifies the x-coordinate of the layout
- `android:layout_y` : This specifies the y-coordinate of the layout
- `android:layout_width=wrap_content` tells the view to size itself to the dimensions required by its content.
- `android:layout_width=match_parent` tells the view to become as big as its parent view.

**View Identification:**

The syntax for an ID, inside an XML tag is:

- The at-symbol (@) at the beginning of the string indicates that the XML parser should parse and expand the rest of the ID string and identify it as an ID resource
- The plus-symbol (+) means that this is a new resource name that must be created and added to our resources

### Padding versus margin

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
<img src="img/wmfp.jpg" alt="wmfp.jpg" width="400px">
</div>

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

Android LinearLayout organizes elements along a single line. We can specify whether that line is vertical or horizontal using `android:orientation`. The orientation is horizontal by default.

A vertical LinearLayout will only have one child per row (so it is a column of single elements), and a horizontal LinearLayout will only have one single row of elements on the screen.

<div align="center">
<img src="img/lr.png" alt="lr.png" width="400px">
</div>

### Layout Weight

android:layout_weight attribute depicts the importance of the element. Weight defines how much space a view will consume compared to other views within a LinearLayout. An element with larger weight occupies more screen space.

By default, the Layout Weight is set to `0` for each child view in a linear layout. However, this can be changed manually using:

<div align="center">
<img src="img/lw.jpg" alt="lw.jpg" width="600px">
</div>

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">

    <TextView
        android:layout_weight="1"
        android:text="Name" />

    <TextView
        android:layout_weight="1"
        android:text="Age" />

    <TextView
        android:layout_weight="1"
        android:text="Address" />
</LinearLayout>
```

## Relative Layout

### Center relative to Parent View

<div align="center">
<img src="img/r1.jpg" alt="r1.jpg" width="400px">
</div>

- `android:layout_centerHorizontal` : Centers the element horizontally within its parent container
- `android:layout_centerInParent` : Centers the element both horizontally and vertically within its container
- `android:layout_centerVertical` : Centers the element vertically within its parent contain

### Align by the parent view

<div align="center">
<img src="img/r2.jpg" alt="r1.jpg" width="400px">
</div>

- `android:layout_alignParentBottom` : Places the bottom of the element on the bottom of the container
- `android:layout_alignParentLeft` : Places the left of the element on the left side of the container
- `android:layout_alignParentRight` : Places the right of the element on the right side of the container
- `android:layout_alignParentTop` : Places the element at the top of the container

### Place new View relative to existing sibling View

In a RelativeLayout you can keep(position) the new views relative to other existing views. Following attributes can be used for doing so.

Suppose there is one view in the center and its id is given as `android:id="@+id/main"` Therefore, the other new views can be placed relative to this view as following:

<div align="center">
<img src="img/r3.jpg" alt="r1.jpg" width="400px">
</div>

- `android:layout_above` : Places the element above the specified element
- `android:layout_below` : Places the element below the specified element
- `android:layout_toLeftOf` : Places the element to the left of the specified element
- `android:layout_toRightOf` : Places the element to the right of the specified element

`“@id/XXXXX”` is used to reference an element by its `id`. One thing to remember is that referencing an element before it has been declared will produce an error so `@+id/` should be used in such cases.

### Align new View relative to existing sibling View

If you want to align the new view relative to any existing view, then you can use the following attributes.

<div align="center">
<img src="img/r4.jpg" alt="r1.jpg" width="400px">
</div>

- `android:layout_alignBaseline` : Aligns baseline of the new element with the baseline of the specified element
- `android:layout_alignBottom` : Aligns the bottom of new element in with the bottom of the specified element
- `android:layout_alignLeft` : Aligns left edge of the new element with the left edge of the specified element
- `android:layout_alignRight` : Aligns right edge of the new element with the right edge of the specified element
- `android:layout_alignTop` : Places top of the new element in alignment with the top of the specified element

Example:

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Name" />

    <TextView
        android:id="@+id/age"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@id/name"
        android:text="Age" />

    <TextView
        android:id="@+id/address"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/name"
        android:text="Address" />
</RelativeLayout>
```

<div align="center">
<img src="img/relative.gif" alt="relative.gif" width="900px">
</div>

## Constraint Layout

ConstraintLayout is an advanced version of a Relative layout. It is used to remove the child view hierarchies resulting in improved performance. It is the most commonly used layout for UI Developments. ConstraintLayout gives one adaptable and flexible ways to create views for android apps and is now the default layout in Android Studio which provides us with many ways to place objects.

- [https://developer.android.com/reference/android/support/constraint/ConstraintLayout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)

A view inside the ConstraintLayout has handles(or *anchor* points) on each side which are used to assign the constraints. Let’s drag and drop a TextView on the layout and assign the constraints to it.

### Correct Ways of Anchoring

> Anchoring just **one** or **both** **vertical** point: ❌ `Top` ❌ `Top-Bottom`

<div align="center">
<img src="img/c1.gif" alt="c1.gif" width="900px">
</div>

<div align="center">
<img src="img/c1-1.gif" alt="c1-1.gif" width="900px">
</div>

> Anchoring just **one** or **both** **horizontal** point: ❌ `Start` ❌ `Start-End`

<div align="center">
<img src="img/c2.gif" alt="c2.gif" width="900px">
</div>

<div align="center">
<img src="img/c2-1.gif" alt="c2-1.gif" width="900px">
</div>

> Anchoring at least **one** **horizontal** and **one** **vertical** point together: ✔️ `Start-Top` ✔️

<div align="center">
<img src="img/c3.gif" alt="c3.gif" width="900px">
</div>

### layout_width/height properties:

<div align="center">
<img src="img/c5.jpg" alt="c5.jpg" width="900px">
</div>

example:

<div align="center">
<img src="img/c4.gif" alt="c4.gif" width="900px">
</div>