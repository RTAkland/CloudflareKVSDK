/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cn.rtast.cfkvsdk.entity

import com.google.gson.annotations.SerializedName
import java.time.Instant

data class KeyValue(
    val key: String,
    val value: String,
    val metadata: Metadata? = null,
    val base64: Boolean = false,
    val expiration: Long = Instant.now().epochSecond,
    @SerializedName("expiration_ttl")
    val expirationTtl: Int = 300,
)

data class Metadata(
    val key: String,
    val value: String,
)

internal data class WriteKeyValueIn(val success: Boolean)