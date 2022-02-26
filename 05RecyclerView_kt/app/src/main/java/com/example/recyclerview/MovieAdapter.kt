package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class MovieAdapter(val movieList: ArrayList<Movie>, private val listenerCallBack: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var index = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val inflatedView =
//          LayoutInflater.from(context)
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        holder.tvMovieName.text = movieList[position].movie_name
//        holder.tvReleaseDate.text = movieList[position].release_date
//        holder.imgvPoster.setImageResource(movieList[position].img_src)
//        if (index == position) {
//            holder.container.setBackgroundColor(Color.rgb(157, 222, 252))
//        } else {
//            holder.container.setBackgroundColor(Color.TRANSPARENT)
//        }
//
//        with(holder) {
//            tvMovieName.text = movieList[position].movie_name
//            tvReleaseDate.text = movieList[position].release_date
//            imgvPoster.setImageResource(movieList[position].img_src)
//            if (index == position) {
//                container.setBackgroundColor(Color.rgb(157, 222, 252))
//            } else {
//                container.setBackgroundColor(Color.TRANSPARENT)
//            }
//        }
        holder.bind(movie = movieList[position], position)
    }


    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvMovieName: TextView = itemView.findViewById(R.id.tvMovieName)
        var tvReleaseDate: TextView = itemView.findViewById(R.id.tvRelease)
        var imgvPoster: ImageView = itemView.findViewById(R.id.imgvPoster)
        var container: ConstraintLayout = itemView.findViewById(R.id.rv_item_container)

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                index = pos
                notifyDataSetChanged()
                listenerCallBack(movieList[pos])
            }
        }

        fun bind(movie: Movie, position: Int) {
            with(movie) {
                tvMovieName.text = movie_name
                tvReleaseDate.text = release_date
                imgvPoster.setImageResource(img_src)
            }
            if (index == position) {
                container.setBackgroundColor(Color.rgb(157, 222, 252))
            } else {
                container.setBackgroundColor(Color.TRANSPARENT)
            }
        }

//        override fun onClick(v: View?) {
//            var pos: Int = adapterPosition//getAdapterPosition()
//            val movie: Movie = movieList[pos]
            // or
            // val movieText = tvMovieName.text
            // val releaseText = tvReleaseDate.text

//            Toast.makeText(v?.context, "Pos: $pos Name:${movie.movie_name}", Toast.LENGTH_SHORT)
//                .show()
//            index = pos
//            notifyDataSetChanged()//invoke onBindViewHolder

//            val intent = Intent(v?.context!!, ListDetails::class.java).apply {
//                putExtra("name", movie.movie_name)
//                putExtra("img", movie.img_src)
//            }
//            v?.context!!.startActivity(intent)
////            v?.let {
//                val context: Context = it.context
//                val intent = Intent(context, ListDetails::class.java).apply {
//                    putExtra("name", movie.movie_name)
//                    putExtra("img", movie.img_src)
//                }
//                context.startActivity(intent)
//            }
//        }
    }
}