/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */

@file:Suppress("unused")

package cn.rtast.cfkvsdk.util

import cn.rtast.cfkvsdk.gson
import com.google.gson.reflect.TypeToken

internal fun Any.toJson(): String {
    return gson.toJson(this)
}

internal inline fun <reified T> String.fromJson(): T {
    return gson.fromJson(this, T::class.java)
}

internal inline fun <reified T> String.fromArrayJson(): T {
    return gson.fromJson(this, object : TypeToken<T>() {}.type)
}