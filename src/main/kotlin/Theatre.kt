import Enums.City

class Theatre {
    var theatreId: Int = 0
    var address: String = ""
    var city: City = City.Hyderabad
    var screen: List<Screen> = mutableListOf()
    var shows: List<Show> = mutableListOf()
}