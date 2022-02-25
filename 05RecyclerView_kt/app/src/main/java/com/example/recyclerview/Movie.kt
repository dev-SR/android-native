package com.example.recyclerview

class Movie(
    var movie_name: String,
    var release_date: String,
    var img_src: Int
) {
    companion object {
        fun getMovieList(): Array<Movie> {
            return arrayOf(
                Movie(
                    "Harry Potter and the Sorcerer's Stone",
                    "2006_05_30",
                    R.drawable.harry_potter_and_the_sorcerers_stone_poster
                ),
                Movie("Finding Nemo", "2003_05_30", R.drawable.finding_nemo),
                Movie(
                    "How to Train Your Dragon",
                    "2010_03_26",
                    R.drawable.how_to_train_your_dragon_poster
                ),
                Movie("Homeward Bound: The Incredible Journey", "1995_11_22", R.drawable.toy_story),
                Movie("WALL_E", "2008_06_27", R.drawable.walle_pi)
            )
        }
    }
}