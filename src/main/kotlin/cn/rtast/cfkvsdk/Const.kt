/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */


package cn.rtast.cfkvsdk

import com.google.gson.Gson
import com.google.gson.GsonBuilder

internal val gson: Gson = GsonBuilder()
    .setPrettyPrinting()
    .disableHtmlEscaping()
    .create()