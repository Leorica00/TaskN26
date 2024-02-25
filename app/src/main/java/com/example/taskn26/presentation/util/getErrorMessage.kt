package com.example.taskn26.presentation.util

import com.example.taskn21.data.remote.common.HandleErrorStates
import com.example.taskn26.R

fun getErrorMessage(error: HandleErrorStates): Int {
    return when (error.errorCode) {
        HandleErrorStates.ErrorCode.CLIENT_ERROR -> R.string.error_client
        HandleErrorStates.ErrorCode.SERVER_ERROR -> R.string.error_server
        HandleErrorStates.ErrorCode.HTTP_ERROR -> R.string.error_http
        HandleErrorStates.ErrorCode.NETWORK_ERROR -> R.string.no_internet
        HandleErrorStates.ErrorCode.TIMEOUT_ERROR -> R.string.error_timeout
        HandleErrorStates.ErrorCode.UNKNOWN_ERROR -> R.string.unexpected_error
    }
}