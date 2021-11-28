package com.fd.kso.testUtlis

import com.fd.kso.data.model.*

object TestUtil {

    private val coordinates = Coordinate(48.8566,2.3522)
    private val detail = Detail(1234,1233.0,222.0)
    private val departure = Departure("Gare de nord","20210403T140000", coordinates)
    private val arrival = Arrival("Gare de lyon","20210403T143000", coordinates)
    private val myItem = MyItem("aaaa123", departure, arrival, detail)
    val listOfItems = listOf<MyItem>(myItem)
    val errorMessage = "this an error"
}