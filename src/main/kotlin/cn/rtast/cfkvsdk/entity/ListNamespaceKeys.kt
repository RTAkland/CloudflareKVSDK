/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cn.rtast.cfkvsdk.entity

internal data class ListNamespaceKeysIn(
    val result: List<NamespaceKey>
)

data class NamespaceKey(
    val expiration: Long,
    val metadata: Map<String, String>,
    val name: String,
)