import Enums.City
import Enums.SeatCategory

class BookMyShow {
    var movieController: MovieController
    var theatreController: TheatreController

    init {
        movieController = MovieController()
        theatreController = TheatreController()
    }

    fun createBooking(userCity: City,movieName: String){

        //1. search movie by my location
        val movies = movieController.getMoviesByCity(city = userCity)

        //2. select the movie which you want to see. i want to see avatar2
        var interestedMovie: Movie? = null
        if (movies != null) {
            for(movie in movies){
                if(movie.movieName == movieName){
                    interestedMovie = movie
                    break
                }
            }
        }
        if(interestedMovie==null) return


        //3. get all show of this movie in Hyderabad Location
        val showsTheatreWise = theatreController.getAllShow(interestedMovie,userCity)

        //4. select the particular show user is interested in
        val entry = showsTheatreWise.entries.iterator().next()
        val runningShows = entry.value

        val interestedShow = runningShows[0]

        //5. select the seat
        val seatNumber = 30
        val bookedSeats = interestedShow.bookedSeatIds
        if(seatNumber !in bookedSeats){
            bookedSeats.add(seatNumber)
            //startPayment
            val booking = Booking()
            val myBookedSeats = mutableListOf<Seat>()
            val screen = interestedShow.screen
            for(screenSeat in screen.seats){
                if(screenSeat.seatId == seatNumber){
                    myBookedSeats.add(screenSeat)
                }
            }
            booking.bookedSeats = myBookedSeats
            booking.show = interestedShow
        }else{
            //throw exception
            println("seat already booked, try again")
            return
        }
        println("BOOKING SUCCESSFUL")
    }

    fun initialize(){
        //create movies
        createMovies()

        //create theater with screens, seats and shows
        createTheatre()
    }

    //creating 2 theatre
    private fun createTheatre(){
        val avatarMovie = movieController.getMovieByName("AVATAR2")
        val topGun = movieController.getMovieByName("TOPGUN")

        val inoxTheatre = Theatre()
        inoxTheatre.theatreId = 1
        inoxTheatre.screen = createScreen()
        inoxTheatre.city = City.Hyderabad
        val inoxShows = mutableListOf<Show>()
        val inoxMorningShow = avatarMovie?.let {
            createShows(1,
            inoxTheatre.screen[0], it,8)
        }
        val inoxEveningShow = topGun?.let {
            createShows(2,
            inoxTheatre.screen[0], it,16)
        }
        if (inoxMorningShow != null) {
            inoxShows.add(inoxMorningShow)
        }
        if (inoxEveningShow != null) {
            inoxShows.add(inoxEveningShow)
        }
        inoxTheatre.shows = inoxShows

        val pvrTheatre = Theatre()
        pvrTheatre.theatreId = 2
        pvrTheatre.screen = createScreen()
        pvrTheatre.city = City.Bangalore
        val pvrShows = mutableListOf<Show>()
        val pvrMorningShow = avatarMovie?.let {
            createShows(3,
            pvrTheatre.screen[0], it,13)
        }
        val pvrEveningShow = topGun?.let {
            createShows(4,
            pvrTheatre.screen[0], it,20)
        }
        if (pvrMorningShow != null) {
            pvrShows.add(pvrMorningShow)
        }
        if (pvrEveningShow != null) {
            pvrShows.add(pvrEveningShow)
        }
        pvrTheatre.shows = pvrShows

        theatreController.addTheatre(inoxTheatre,City.Hyderabad)
        theatreController.addTheatre(pvrTheatre,City.Bangalore)

    }

    private fun createScreen(): List<Screen>{
        val screens = ArrayList<Screen>()
        val screen1 = Screen()
        screen1.screenId = 1
        screen1.seats = createSeats()
        screens.add(screen1)

        return screens
    }

    private fun createShows(showId: Int, screen: Screen,movie: Movie,showStartTime: Int):Show{
        val show = Show()
        show.showId = showId
        show.screen = screen
        show.movie = movie
        show.showStartTime = showStartTime //24 hrs time
        return show
    }

    //Creating 100 seats
    private fun createSeats(): List<Seat>{
        //Creating 100 seats for testing purpose, this can be generalised
        val seats = mutableListOf<Seat>()

        //1 to 40: SILVER
        for(i in 0 until 40){
            val seat = Seat()
            seat.seatId = i
            seat.seatCategory = SeatCategory.SILVER
            seats.add(seat)
        }

        //41 to 70 : SILVER
        for(i in 40 until 70){
            val seat = Seat()
            seat.seatId = i
            seat.seatCategory = SeatCategory.GOLD
            seats.add(seat)
        }

        //1 to 40: SILVER
        for(i in 70 until 100){
            val seat = Seat()
            seat.seatId = i
            seat.seatCategory = SeatCategory.PLATINUM
            seats.add(seat)
        }
        return seats
    }

    private fun createMovies(){
        //create Movies1
        val avatar = Movie()
        avatar.movieId = 1
        avatar.movieName = "AVATAR2"
        avatar.movieDurationInMinutes = 128

        //create Movies2
        val topGun = Movie()
        topGun.movieId = 2
        topGun.movieName = "TOPGUN"
        topGun.movieDurationInMinutes = 180

        //add movies against the cities
        movieController.addMovie(avatar,City.Bangalore)
        movieController.addMovie(avatar,City.Hyderabad)
        movieController.addMovie(topGun,City.Hyderabad)
        movieController.addMovie(topGun,City.Bangalore)
    }
}