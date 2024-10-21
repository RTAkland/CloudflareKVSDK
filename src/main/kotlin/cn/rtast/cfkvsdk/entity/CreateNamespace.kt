/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cn.rtast.cfkvsdk.entity

internal data class CreateNamespaceOut(val title: String)

internal data class CreateNamespaceIn(val success: Boolean, val result: NamespaceKey)

data class CreateNamespace(val success: Boolean, val result: NamespaceKey)