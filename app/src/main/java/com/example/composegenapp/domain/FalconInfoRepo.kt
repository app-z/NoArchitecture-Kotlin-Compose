package com.example.composegenapp.domain

import com.example.composegenapp.data.FalconInfo
import com.example.composegenapp.data.SpaceX

class FalconInfoRepo {

    suspend fun getFalconInfo() : List<FalconInfo> {
        return SpaceX().getRockets()
    }


}