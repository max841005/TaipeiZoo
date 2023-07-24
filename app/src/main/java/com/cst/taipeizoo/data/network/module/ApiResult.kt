package com.cst.taipeizoo.data.network.module

sealed class ApiResult<out T> {
    class ApiSuccess<T>(val data: T?) : ApiResult<T>()
    class ApiError(val code: Int, val message: String?) : ApiResult<Nothing>()
    class ApiException(val t: Throwable) : ApiResult<Nothing>()
}

suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T?) -> Unit
): ApiResult<T> = apply {

    if (this is ApiResult.ApiSuccess) {
        executable(data)
    }
}

suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): ApiResult<T> = apply {

    if (this is ApiResult.ApiError) {
        executable(code, message)
    }
}

suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (t: Throwable) -> Unit
): ApiResult<T> = apply {

    if (this is ApiResult.ApiException) {
        executable(t)
    }
}