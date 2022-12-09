import Enums.City

class TheatreController {
    var cityVsTheatre: MutableMap<City,MutableList<Theatre>>
    var allTheatres: MutableList<Theatre>

    init {
        cityVsTheatre = mutableMapOf()
        allTheatres = mutableListOf()
    }

    fun addTheatre(theatre: Theatre,city: City){
        allTheatres.add(theatre)
        var theatres: MutableList<Theatre> = cityVsTheatre.getOrDefault(city, mutableListOf())
        theatres.add(theatre)
        //cityVsTheatre.put(city,theatres)
        cityVsTheatre[city] = theatres
    }

    fun getAllShow(movie: Movie,city: City): Map<Theatre,List<Show>>{
        val theatreVsShows = mutableMapOf<Theatre,List<Show>>()
        val theatres = cityVsTheatre[city]
        if(theatres!=null){
            for(theatre in theatres){
                val givenMovieShows = mutableListOf<Show>()
                val shows = theatre.shows
                for(show in shows){
                    if(show.movie.movieId == movie.movieId){
                        givenMovieShows.add(show)
                    }
                }
                if(givenMovieShows.isNotEmpty()){
                    theatreVsShows[theatre] = givenMovieShows
                }
            }
        }
        return theatreVsShows
    }
}