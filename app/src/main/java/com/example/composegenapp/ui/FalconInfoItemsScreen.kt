package com.example.composegenapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composegenapp.R
import com.example.composegenapp.common.DataSourceException
import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.common.onError
import com.example.composegenapp.common.onSuccess
import com.example.composegenapp.data.DataMapper
import com.example.composegenapp.domain.model.FalconInfo
import com.example.composegenapp.domain.usecase.GetFalconInfoUseCase
import com.example.composegenapp.ui.theme.ComposeGenAppTheme
import com.example.composegenapp.utils.getError
import timber.log.Timber

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
                    Timber.d("size = ${rockets.size}")
                }
            }

            is ResponseResult.Error -> it.value.onError { error ->
                ShowStatusScreen(error.getError(LocalContext.current))
            }

        }
    }
}


//-----------------------------------------------------------------------------------------------

@Composable
fun FalconInfoListView(falconInfoEntries: List<FalconInfo>) {
    Column(modifier = Modifier
        .fillMaxHeight()) {

        LazyVerticalGrid(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(4.dp),
            columns = GridCells.Fixed(2), content = {
                items(falconInfoEntries) { falconInfo ->
                    FalconInfoCard(falconInfo)
                }
            })
    }
}


@Preview
@Composable
fun PreviewConversation() {
    ComposeGenAppTheme {
        FalconInfoListView(DataMapper.getMockRockets())
    }
}
