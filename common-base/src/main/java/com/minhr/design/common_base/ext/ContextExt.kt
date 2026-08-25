package com.minhr.design.common_base.ext

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Build

fun Context.registerAppReceiver(receiver: BroadcastReceiver?, filter: IntentFilter) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
    } else {
        @Suppress("UnspecifiedRegisterReceiverFlag")
        registerReceiver(receiver, filter)
    }
}
