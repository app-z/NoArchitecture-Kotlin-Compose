package com.example.composegenapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.composegenapp.FalconInfoListView
import com.example.composegenapp.R
import com.example.composegenapp.common.DataSourceException
import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onError
import com.example.composegenapp.common.onSuccess
import com.example.composegenapp.domain.model.FalconInfo
import com.example.composegenapp.domain.usecase.GetFalconInfoUseCase
import com.example.composegenapp.utils.getError

@Composable
fun FalconInfoItemsScreen(getFalconInfoUseCase: GetFalconInfoUseCase) {

        produceState<ResponseResult<List<FalconInfo>>>(initialValue = ResponseResult.Loading, getFalconInfoUseCase) {
            try {
                getFalconInfoUseCase.getFalconInfo().collect {
                    value = it
                }
            } catch (e: DataSourceException) {
                value = ResponseResult.Error(e)
            }
        }.let {
            when (it.value) {
            is ResponseResult.Loading -> ShowStatusScreen(stringResource(R.string.loading))

            is ResponseResult.Success -> {
                it.value.onSuccess { rockets ->
                    FalconInfoListView(rockets)
                }
            }

            is ResponseResult.Error -> it.value.onError { error ->
                ShowStatusScreen(error.getError(LocalContext.current))
            }

        }
    }
}
