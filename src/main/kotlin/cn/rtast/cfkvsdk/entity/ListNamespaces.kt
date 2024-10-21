/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cn.rtast.cfkvsdk.entity

import com.google.gson.annotations.SerializedName

internal data class ListNamespacesIn(
    @SerializedName("result_info")
    val resultInfo: ResultInfo,
    val result: List<NamespaceKey>
) {
    data class ResultInfo(
        val count: Int
    )
}

data class Result(
    val id: String,
    @SerializedName("supports_url_encoding")
    val supportsUrlEncoding: String,
    val title: String,
)

data class ListNamespaces(
    val count: Int,
    val results: List<NamespaceKey>
)