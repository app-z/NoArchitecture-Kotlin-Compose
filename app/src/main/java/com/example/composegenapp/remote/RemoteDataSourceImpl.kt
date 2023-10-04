package com.example.composegenapp.remote

import com.example.composegenapp.R
import com.example.composegenapp.common.DataSourceException
import com.example.composegenapp.common.ResponseResult
import com.example.composegenapp.data.FalconInfoResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val api: ApiService)
    : RemoteDataSource {

    override suspend fun getFalconInfo(): ResponseResult<List<FalconInfoResult>> {
        return try {
            val falconInfoResult = api.getFalconInfo()
            ResponseResult.Success(falconInfoResult)
        } catch (ex: RedirectResponseException) {
            ResponseResult.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
        } catch (ex: ServerResponseException) {
            ResponseResult.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
        } catch (ex: ClientRequestException) {
            ResponseResult.Error(DataSourceException.Client(R.string.error_client_unexpected_message ))
        } catch (ex: ResponseException) {
            ResponseResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        } catch (ex: UnknownHostException) {
            ResponseResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }
}
