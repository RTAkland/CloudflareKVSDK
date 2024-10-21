/*
 * Copyright © 2024 RTAkland
 * Author: RTAkland
 * Date: 2024/10/21
 */

@file:Suppress("unused")

package cn.rtast.cfkvsdk

import cn.rtast.cfkvsdk.entity.CreateNamespace
import cn.rtast.cfkvsdk.entity.CreateNamespaceIn
import cn.rtast.cfkvsdk.entity.CreateNamespaceOut
import cn.rtast.cfkvsdk.entity.DeleteKeyValue
import cn.rtast.cfkvsdk.entity.DeleteNamespace
import cn.rtast.cfkvsdk.entity.GetNamespace
import cn.rtast.cfkvsdk.entity.GetNamespaceIn
import cn.rtast.cfkvsdk.entity.KeyValue
import cn.rtast.cfkvsdk.entity.ListNamespaceKeysIn
import cn.rtast.cfkvsdk.entity.ListNamespaces
import cn.rtast.cfkvsdk.entity.ListNamespacesIn
import cn.rtast.cfkvsdk.entity.NamespaceKey
import cn.rtast.cfkvsdk.entity.ReadMetadataForKey
import cn.rtast.cfkvsdk.entity.RenameNamespace
import cn.rtast.cfkvsdk.entity.WriteKeyValueIn
import cn.rtast.cfkvsdk.util.Http
import cn.rtast.cfkvsdk.util.toJson

class CloudflareKVSDK(
    private val accessToken: String,
    private val accountId: String,
) {
    companion object {
        private const val CF_API_URL = "https://api.cloudflare.com/client/v4/accounts"
    }

    fun listNamespaces(): ListNamespaces {
        val response = Http.get<ListNamespacesIn>(
            "$CF_API_URL/$accountId/storage/kv/namespaces",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return ListNamespaces(response.resultInfo.count, response.result)
    }

    fun createNamespace(title: String): CreateNamespace {
        val payload = CreateNamespaceOut(title).toJson()
        val response = Http.post<CreateNamespaceIn>(
            "$CF_API_URL/$accountId/storage/kv/namespaces",
            payload, headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return CreateNamespace(response.success, response.result)
    }

    fun deleteNamespace(namespaceId: String): Boolean {
        val response = Http.delete<DeleteNamespace>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return response.success
    }

    fun getNamespace(namespaceId: String): GetNamespace {
        val response = Http.get<GetNamespaceIn>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return GetNamespace(response.result.id, response.result.supportsUrlEncoding, response.result.title)
    }

    fun renameNamespace(namespaceId: String, title: String): Boolean {
        val payload = CreateNamespaceOut(title).toJson()
        val response = Http.put<RenameNamespace>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId",
            headers = mapOf("Authorization" to "Bearer $accessToken"),
            jsonBody = payload
        )
        return response.success
    }

    fun writeMultipleKV(namespaceId: String, kv: List<KeyValue>): Boolean {
        val payload = kv.toJson()
        val response = Http.put<WriteKeyValueIn>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId/bulk",
            headers = mapOf("Authorization" to "Bearer $accessToken"),
            jsonBody = payload
        )
        return response.success
    }

    fun deleteMultipleKV(namespaceId: String, keys: List<String>): Boolean {
        val payload = keys.toJson()
        val response = Http.post<DeleteKeyValue>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId/bulk/delete",
            headers = mapOf("Authorization" to "Bearer $accessToken"),
            jsonBody = payload
        )
        return response.success
    }

    fun listNamespaceKeys(namespaceId: String): List<NamespaceKey> {
        val response = Http.get<ListNamespaceKeysIn>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId/keys",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return response.result
    }

    fun readMetadataForKey(namespaceId: String, key: String): Map<String, String> {
        val response = Http.get<ReadMetadataForKey>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId/metadata/$key",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return response.result
    }

    fun deleteKV(namespaceId: String, key: String): Boolean {
        val response = Http.delete<DeleteKeyValue>(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId/values/$key",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return response.success
    }

    fun readKV(namespaceId: String, key: String): String {
        val response = Http.get(
            "$CF_API_URL/$accountId/storage/kv/namespaces/$namespaceId/values/$key",
            headers = mapOf("Authorization" to "Bearer $accessToken")
        )
        return response
    }
}