package com.example.composegenapp.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FalconInfo")
data class FalconInfoEntity(

    @PrimaryKey @ColumnInfo(name = "id") var id                 : String             = "0",

//    @ColumnInfo(name = "fairings"              ) var fairings           : Fairings?           = Fairings(),
    @Embedded("links"                 ) var linksEntry              : LinksEntry?              = LinksEntry(),

    @ColumnInfo(name = "static_fire_date_utc"  ) var staticFireDateUtc  : String?             = null,
    @ColumnInfo(name = "static_fire_date_unix" ) var staticFireDateUnix : Int?                = null,
    @ColumnInfo(name = "net"                   ) var net                : Boolean?            = null,
    @ColumnInfo(name = "window"                ) var window             : Int?                = null,
    @ColumnInfo(name = "rocket"                ) var rocket             : String             = "Unknown",
    @ColumnInfo(name = "success"               ) var success            : Boolean?            = null,
//    @ColumnInfo(name = "failures"              ) var failures           : ArrayList<Failures> = arrayListOf(),
    @ColumnInfo(name = "details"               ) var details            : String?             = null,
//    @ColumnInfo(name = "crew"                  ) var crew               : ArrayList<String>   = arrayListOf(),
//    @ColumnInfo(name = "ships"                 ) var ships              : ArrayList<String>   = arrayListOf(),
//    @ColumnInfo(name = "capsules"              ) var capsules           : ArrayList<String>   = arrayListOf(),
//    @ColumnInfo(name = "payloads"              ) var payloads           : ArrayList<String>   = arrayListOf(),
    @ColumnInfo(name = "launchpad"             ) var launchpad          : String?             = null,
    @ColumnInfo(name = "flight_number"         ) var flightNumber       : Int?                = null,
    @ColumnInfo(name = "name"                  ) var name               : String             = "Unknown",
    @ColumnInfo(name = "date_utc"              ) var dateUtc            : String?             = null,
    @ColumnInfo(name = "date_unix"             ) var dateUnix           : Int?                = null,
    @ColumnInfo(name = "date_local"            ) var dateLocal          : String?             = null,
    @ColumnInfo(name = "date_precision"        ) var datePrecision      : String?             = null,
    @ColumnInfo(name = "upcoming"              ) var upcoming           : Boolean?            = null,
//    @ColumnInfo(name = "cores"                 ) var cores              : ArrayList<Cores>    = arrayListOf(),
    @ColumnInfo(name = "auto_update"           ) var autoUpdate         : Boolean?            = null,
    @ColumnInfo(name = "tbd"                   ) var tbd                : Boolean?            = null,
    @ColumnInfo(name = "launch_library_id"     ) var launchLibraryId    : String?             = null,

    )

data class FairingsEntry (

    @ColumnInfo(name = "reused"           ) var reused          : Boolean?          = null,
    @ColumnInfo(name = "recovery_attempt" ) var recoveryAttempt : Boolean?          = null,
    @ColumnInfo(name = "recovered"        ) var recovered       : Boolean?          = null,
    @ColumnInfo(name = "ships"            ) var ships           : ArrayList<String> = arrayListOf()

)

@Entity
data class PatchEntry (
    @PrimaryKey
    var pathId: Long? = null,
    @ColumnInfo(name = "small" ) var small : String? = null,
    @ColumnInfo(name = "large" ) var large : String? = null
)

@Entity
data class LinksEntry (
    @PrimaryKey
    var linkId: Long? = null,
    @Embedded( "patch"      ) var patchEntry     : PatchEntry?  = PatchEntry(),
//    @ColumnInfo(name = "reddit"     ) var reddit    : Reddit? = Reddit(),
//    @ColumnInfo(name = "flickr"     ) var flickr    : Flickr? = Flickr(),
    @ColumnInfo(name = "presskit"   ) var presskit  : String? = null,
    @ColumnInfo(name = "webcast"    ) var webcast   : String? = null,
    @ColumnInfo(name = "youtube_id" ) var youtubeId : String? = null,
    @ColumnInfo(name = "article"    ) var article   : String? = null,
    @ColumnInfo(name = "wikipedia"  ) var wikipedia : String? = null

)
