import Enums.City

fun main(args: Array<String>) {
    val bookMyShow: BookMyShow = BookMyShow()
    bookMyShow.initialize()

    //user1
    bookMyShow.createBooking(City.Bangalore,"AVATAR2")

    //user2
    bookMyShow.createBooking(City.Bangalore,"AVATAR2")
}