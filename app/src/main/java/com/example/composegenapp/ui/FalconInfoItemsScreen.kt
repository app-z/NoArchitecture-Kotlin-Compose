package com.example.composegenapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.example.composegenapp.FalconInfoListView
import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onError
import com.example.composegenapp.common.onSuccess
import com.example.composegenapp.data.FalconInfo
import com.example.composegenapp.domain.FalconInfoRepo

@Composable
fun FalconInfoItemsScreen(
    repo : FalconInfoRepo = FalconInfoRepo()
) {
    val resLoad =
        produceState<ResponseResult<List<FalconInfo>>>(initialValue = ResponseResult.Loading, repo) {
            value = try {
                ResponseResult.Success(repo.getFalconInfo())
            } catch (e: Exception) {
                ResponseResult.Error(e.localizedMessage ?: "error")
            }
        }

    resLoad.let {
        when (it.value) {
            is ResponseResult.Loading -> ShowStatusScreen("Loading...")
            is ResponseResult.Success -> {
                it.value.onSuccess { rockets ->
                    FalconInfoListView(rockets)
                }
            }

            is ResponseResult.Error -> it.value.onError { error ->
                ShowStatusScreen(status = error)
            }

        }
    }
}
