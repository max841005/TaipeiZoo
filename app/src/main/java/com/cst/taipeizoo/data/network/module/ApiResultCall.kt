package com.cst.taipeizoo.data.network.module

import com.cst.taipeizoo.data.network.module.ApiResult.ApiError
import com.cst.taipeizoo.data.network.module.ApiResult.ApiException
import com.cst.taipeizoo.data.network.module.ApiResult.ApiSuccess
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResultCall<T>(proxy: Call<T>) : CallDelegate<T, ApiResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<ApiResult<T>>) = proxy.enqueue(object: Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {

            val code = response.code()
            val message = response.message()

            val apiResult = if (code in 200 until 300) {
                val body = response.body()
                ApiSuccess(body)
            }
            else {
                ApiError(code = code, message = message)
            }

            callback.onResponse(this@ApiResultCall, Response.success(apiResult))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {

            val apiResult = ApiException(t)

            callback.onResponse(this@ApiResultCall, Response.success(apiResult))
        }
    })

    override fun cloneImpl() = ApiResultCall(proxy.clone())
}