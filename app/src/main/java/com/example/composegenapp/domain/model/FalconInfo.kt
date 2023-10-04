package com.example.composegenapp.domain.model


data class FalconInfo(

    var id                 : String             = "0",

//      var fairings           : Fairings?           = Fairings(),
    var links              : Links?              = Links(),

    var staticFireDateUtc  : String?             = null,
    var staticFireDateUnix : Int?                = null,
    var net                : Boolean?            = null,
    var window             : Int?                = null,
    var rocket             : String             = "Unknown",
    var success            : Boolean?            = null,
//    var failures           : ArrayList<Failures> = arrayListOf(),
    var details            : String?             = null,
//  var crew               : ArrayList<String>   = arrayListOf(),
//    var ships              : ArrayList<String>   = arrayListOf(),
//    var capsules           : ArrayList<String>   = arrayListOf(),
//    var payloads           : ArrayList<String>   = arrayListOf(),
    var launchpad          : String?             = null,
    var flightNumber       : Int?                = null,
    var name               : String             = "Unknown",
    var dateUtc            : String?             = null,
    var dateUnix           : Int?                = null,
    var dateLocal          : String?             = null,
    var datePrecision      : String?             = null,
    var upcoming           : Boolean?            = null,
//   var cores              : ArrayList<Cores>    = arrayListOf(),
    var autoUpdate         : Boolean?            = null,
    var tbd                : Boolean?            = null,
    var launchLibraryId    : String?             = null
    )


data class Patch (
    var pathId: Long? = null,
    var small : String? = null,
    var large : String? = null
)

data class Links (
    var linkId: Long? = null,
    var patch     : Patch?  = Patch(),
    var presskit  : String? = null,
    var webcast   : String? = null,
    var youtubeId : String? = null,
    var article   : String? = null,
    var wikipedia : String? = null
)
