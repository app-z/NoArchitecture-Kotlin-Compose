package com.example.composegenapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import com.example.composegenapp.FalconInfoListView
import com.example.composegenapp.common.DataSourceException
import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onError
import com.example.composegenapp.common.onSuccess
import com.example.composegenapp.domain.domain.model.FalconInfo
import com.example.composegenapp.domain.domain.usecase.GetFalconInfoUseCase

@Composable
fun FalconInfoItemsScreen(getFalconInfoUseCase: GetFalconInfoUseCase) {
    val resLoad =
        produceState<ResponseResult<List<FalconInfo>>>(initialValue = ResponseResult.Loading) {
            try {
                getFalconInfoUseCase.getFalconInfo().collect {
                    value = it
                }
            } catch (e: DataSourceException) {
                value = ResponseResult.Error(e)
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
                ShowStatusScreen(status = error.localizedMessage)
            }

        }
    }
}
