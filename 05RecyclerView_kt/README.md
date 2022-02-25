# RecyclerView

- [RecyclerView](#recyclerview)
	- [Downside of ListView](#downside-of-listview)
		- [RecyclerView](#recyclerview-1)

## Downside of ListView

Imagine you’re creating a `ListView` with complicated custom items.

You create a row layout for the items and use that layout inside your adapter. You inflate your item layout in `getView()`, referencing each view with the unique ID you provided in XML to customize and add view logic. You pass that to the `ListView`, and it’s ready to be drawn on the screen. Or is it?

`ListViews` and `GridViews` only do half the job of achieving true memory efficiency. They recycle the item layout, but don’t keep references to the layout children, forcing you to call `findViewById()` for every child of your item layout every time you call `getView()`.

All this calling around can become processor-intensive, especially for complicated layouts. Furthermore, the situation can cause your `ListView` scrolling to become jerky or nonresponsive as it tries to grab view references.

<div align="center">
<img src="img/ListView.png" alt="ListView.png" width="600px">
</div>

Android initially provided a solution to this problem on the Android Developers site with smooth scrolling via the power of the `View Holder` pattern.

With this pattern, a class becomes an in-memory reference to all the views needed to fill your layout. You set the references once and reuse them, working around the performance hit that comes with repeatedly calling `findViewById().`

<div align="center">
<img src="img/viewholder_new_larger.png" alt="viewholder_new_larger.png" width="600px">
</div>

Take note: This is an optional pattern for a ListView or GridView. If you’re unaware of this detail, then you may wonder why your ListViews and GridViews are so slow.

### RecyclerView

The arrival of the `RecyclerView` changed everything. It still uses an `Adapter` to act as a data source; however, you have to create `ViewHolders` to keep references in memory.

To provide a new view, `RecyclerView` either creates a new `ViewHolder` object to inflate the layout and hold those references, or it recycles one from the existing stack.

Now you know why it’s called a `RecyclerView`!

Another perk of using `RecyclerViews` is that they come with default animations that you don’t have to create or add yourself.

Because it requires a `ViewHolder`, the `RecyclerView` knows which animation to apply to which item and adds them as required. You can also create your own animations and apply them as needed.

The last and most interesting component of a `RecyclerView` is its `LayoutManager`. This object positions the RecyclerView’s items and tells it when to recycle items that have transitioned off-screen. The `ListView` used to do this work alone. The RecyclerView has broken out this functionality to allow for different kinds of layouts: `Vertical`, `horizontal`, `grid`, `staggered` or your own!

`LayoutManager` offer three choices by default:

- `LinearLayoutManager` positions items to look like a standard ListView
- `GridLayoutManager` positions items in a grid format similar to a GridView
- `StaggeredGridLayoutManager` positions tems in a staggered grid format.

Create your own `LayoutManagers` to use with a `RecyclerView` if you want extra customization.
