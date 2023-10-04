package com.example.composegenapp.data

import com.example.composegenapp.db.FalconInfoEntity
import com.example.composegenapp.db.LinksEntry
import com.example.composegenapp.db.PatchEntry
import com.example.composegenapp.domain.model.FalconInfo
import com.example.composegenapp.domain.model.Links
import com.example.composegenapp.domain.model.Patch

object DataMapper {

    private const val rocketsSize = 10;
    private val rocketsSampleResult: List<FalconInfo> =
        buildList<FalconInfo>(rocketsSize) {
            for (i in 0 until rocketsSize) {
                add(
                    FalconInfo(
                        name = "Name $i",
                        rocket = "Rocket $i",
                        details = "Detail can be long, Detail can be long, Detail can be long, Detail can be long, Detail can be long, Detail can be long, "
                    )
                )
            }
        }

    fun getRockets(): List<FalconInfo> {
        return rocketsSampleResult
    }

    fun responseToEntry(falconInfoResult: List<FalconInfoResult>): List<FalconInfoEntity> {
        return falconInfoResult.map {
            FalconInfoEntity(
                id = it.id,
                name = it.name,
                linksEntry = LinksEntry(patchEntry = PatchEntry(small = it.linksResult?.patchResult?.small)),
                staticFireDateUtc = it.staticFireDateUtc,
                rocket = it.rocket,
                details = it.details
            )
        }
    }

    fun responseToDomain(falconInfoResult: List<FalconInfoResult>): List<FalconInfo> {
        return falconInfoResult.map {
            FalconInfo(id = it.id,
                name = it.name,
                links = Links(
                    patch =
                    Patch(small = it.linksResult?.patchResult?.small)
                ),
                staticFireDateUtc = it.staticFireDateUtc,
                rocket = it.rocket,
                details = it.details
            )
        }
    }

    fun entryToResponse(falconInfoEntity: List<FalconInfoEntity>): List<FalconInfoResult> {
        return falconInfoEntity.map {
            FalconInfoResult(
                id = it.id,
                name = it.name,
                linksResult = LinksResult(
                    patchResult =
                    PatchResult(small = it.linksEntry?.patchEntry?.small)
                ),
                staticFireDateUtc = it.staticFireDateUtc,
                rocket = it.rocket,
                details = it.details
            )
        }
    }

    fun entryToDomain(falconInfoEntity: List<FalconInfoEntity>) : List<FalconInfo> {
        return falconInfoEntity.map {
            FalconInfo(id = it.id,
                name = it.name,
                links = Links(
                    patch =
                    Patch(small = it.linksEntry?.patchEntry?.small)
                ),
                staticFireDateUtc = it.staticFireDateUtc,
                rocket = it.rocket,
                details = it.details
                )
        }
    }
}

