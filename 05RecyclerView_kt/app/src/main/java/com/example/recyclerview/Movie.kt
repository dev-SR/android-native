package com.example.recyclerview

import kotlin.random.Random

class Movie(
    var movie_name: String,
    var release_date: String,
    var img_src: Int
) {
    companion object {
        private val MOVIES = mapOf<Int, Movie>(
            0 to Movie(
                "Harry Potter and the Sorcerer's Stone",
                "2006_05_30",
                R.drawable.harry_potter_and_the_sorcerers_stone_poster
            ),
            1 to Movie("Finding Nemo", "2003_05_30", R.drawable.finding_nemo),
            2 to Movie(
                "How to Train Your Dragon",
                "2010_03_26",
                R.drawable.how_to_train_your_dragon_poster
            ),
            3 to Movie(
                "Homeward Bound: The Incredible Journey",
                "1995_11_22",
                R.drawable.toy_story
            ),
            4 to Movie("WALL_E", "2008_06_27", R.drawable.walle_pi),
            5 to Movie("Moana", "2019_06_27", R.drawable.moana_poster00),
            6 to Movie("The Incredibles", "2004_10_21", R.drawable.the_incredibles),
            7 to Movie("The Toy Story", "2003_10_30", R.drawable.toy_story)
        )

        fun getMovieList(n:Int): ArrayList<Movie> {
            val movieList = ArrayList<Movie>(n)
            for (i in 1..n){
               movieList.add(MOVIES[Random.nextInt(8)]!!)
            }

            return movieList
        }
    }
}