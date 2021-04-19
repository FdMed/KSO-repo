package com.fd.kso.testUtlis

import com.fd.kso.data.model.*

class TestUtil {

    companion object {
        val coordinates = Coordinate(48.8566,2.3522)
        val coordinates2 = Coordinate(48.84456281031005,2.373823416475962)
        val detail = Detail(1234,1233.0,222.0)
        val departure = Departure("Gare de nord","20210403T140000", coordinates)
        val arrival = Arrival("Gare de lyon","20210403T143000", coordinates)
        val myItem = MyItem("aaaa123", departure, arrival, detail)

        val listOfItemsEmpty = listOf<MyItem>()
        val listOfItems = listOf<MyItem>(myItem)

        val errorMessage = "this an error"

    }
}