import Enums.City

class MovieController {

    var cityVsMovies: MutableMap<City,List<Movie>> = mutableMapOf()
    var allMovies: MutableList<Movie> = mutableListOf()

    fun addMovie(movie: Movie,city: City){
        allMovies.add(movie)
        var movies: MutableList<Movie> = cityVsMovies.getOrDefault(city, mutableListOf()) as MutableList<Movie>
        movies.add(movie)
        cityVsMovies[city] = movies
    }

    fun getMovieByName(movieName: String): Movie? {
        for(movie in allMovies){
            if(movie.movieName == movieName){
                return movie
            }
        }
        return null
    }

    fun getMoviesByCity(city: City): List<Movie>?{
        return cityVsMovies[city]
    }

    fun removeMovie(movie: Movie, city: City){
        allMovies.remove(movie)
        val movies = cityVsMovies[city] as MutableList<Movie>
        if(movies!=null){
            movies.remove(movie)
            cityVsMovies[city] = movies
        }
    }

    fun updateMovie(movie: Movie, city: City){
        val index = allMovies.indexOf(movie)
        if(index!=-1){
            allMovies[index] = movie
            val movies = cityVsMovies[city] as MutableList<Movie>
            if(movies!=null){
                val movieIndex = movies.indexOf(movie)
                if(movieIndex!=-1){
                    movies[movieIndex] = movie
                    cityVsMovies[city] = movies
                }
            }
        }
    }

    fun crudOperation(movieId: Int,operation: String){
        for(movie in allMovies){
            if(movie.movieId == movieId){
                when(operation){
                        "CREATE" -> {
                            //TODO: add code to create a new movie
                        }
                        "READ" -> {
                            //TODO: add code to read a movie
                        }
                        "UPDTAE" -> {
                            //TODO: add code to update a movie
                        }
                        "DELETE" -> {
                            //TODO: add code to delete a movie
                        }
                }
            }
        }
    }
}