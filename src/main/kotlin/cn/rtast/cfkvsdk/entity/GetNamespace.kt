/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cn.rtast.cfkvsdk.entity

import com.google.gson.annotations.SerializedName

internal data class GetNamespaceIn(val result: Result)

data class GetNamespace(
    val id: String,
    @SerializedName("supports_url_encoding")
    val supportsUrlEncoding: String,
    val title: String,
)