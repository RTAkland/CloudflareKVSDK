/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cfkvsdk

import cn.rtast.cfkvsdk.CloudflareKVSDK
import cn.rtast.cfkvsdk.entity.KeyValue
import cn.rtast.cfkvsdk.entity.Metadata

fun main() {
    val sdk = CloudflareKVSDK("_w5w-1", "1")
    sdk.writeMultipleKV("1", listOf(KeyValue("a", "b", metadata = Metadata("s", "b"))))
    println(sdk.readKV("1", "a"))
}