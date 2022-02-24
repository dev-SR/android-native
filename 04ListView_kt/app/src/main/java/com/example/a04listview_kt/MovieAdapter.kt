package com.example.a04listview_kt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter(
    private val context: Context,
    private val movieList: Array<Movie>
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
        // create View from XML
        val rowItemView =
            LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)
        // get reference of the subviews of `movie_list_item.xml` layout
        val tvMovieName = rowItemView.findViewById<TextView>(R.id.tvMovieName)
        val tvReleaseDate = rowItemView.findViewById<TextView>(R.id.tvRelease)
        val imgvPoster = rowItemView.findViewById<ImageView>(R.id.imgvPoster)
        // set content
        tvMovieName.text = movieList[position].movie_name
        tvReleaseDate.text = movieList[position].release_date
        imgvPoster.setImageResource(movieList[position].img_src)
        return rowItemView
    }
}