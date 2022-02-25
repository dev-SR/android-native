package com.example.a04listview_kt

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MovieAdapter(
    private val movieList: ArrayList<Movie>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        var convertView = convertView

        if (convertView == null) {
            // If convert view is null then inflate a custom view and use it as convert view
            convertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.movie_list_item, parent, false)

            // Create a new view holder instance using convert view
            viewHolder = ViewHolder(convertView)

            // Set the view holder as convert view tag
            convertView.tag = viewHolder
        } else {
            /*
                If convert view is not null then
                initialize the view holder using convert view tag.
            */
            viewHolder = convertView.tag as ViewHolder
        }

        // Display the current color name and value on view holder
        viewHolder.tvMovieName.text = movieList[position].movie_name
        viewHolder.tvReleaseDate.text = movieList[position].release_date
        viewHolder.imgvPoster.setImageResource(movieList[position].img_src)

//        // Set a click listener for card view
        viewHolder.lv_item_container.setOnClickListener {
            // Show selected color in a toast message
            Toast.makeText(
                parent?.context,
                "Clicked : ${movieList[position].movie_name}", Toast.LENGTH_SHORT
            ).show()

            for (i in 0 until parent!!.childCount) {
                if (i == position) {
                    parent.getChildAt(i).setBackgroundColor(Color.rgb(157, 222, 252))
                } else {
                    parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
                }

            }
//              Change Color of Selected Items
//            viewHolder.lv_item_container.setBackgroundColor(
//                ContextCompat.getColor(parent!!.context, R.color.teal_200)
//            )

// Get reference of Parent View
// Get the activity reference from parent
//        val activity = parent?.context as Activity
//
//        // Get the activity root view
//        val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content)
//            .getChildAt(0)
//
//        // Change the root layout background color
//        viewGroup.setBackgroundColor(
//            Color.WHITE
//        )
        }


        // Finally, return the convert view
        return convertView!!
    }

    private class ViewHolder(view: View) {
        var tvMovieName: TextView = view.findViewById(R.id.tvMovieName)
        var tvReleaseDate: TextView = view.findViewById(R.id.tvRelease)
        var imgvPoster: ImageView = view.findViewById(R.id.imgvPoster)
        var lv_item_container: ConstraintLayout = view.findViewById(R.id.lv_item_container)
    }
}