package com.nsicyber.travelandshare.models



data class TravelDataModel(

    var id: String = "",

    var title: String = "",

    var description: String = "",

    var images: List<String> = listOf(),

    var gps: String = ""

)