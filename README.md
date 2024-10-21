# CF KV SDK

> 用于快速在JVM平台上和Cloudflare KV交互, 使用了okhttp和gson

# 使用

```kotlin
fun main() {
    val sdk = CloudflareKVSDK("114514", "xxx")
    sdk.writeMultipleKV("xxx", listOf(KeyValue("a", "b", metadata = Metadata("s", "b"))))
    println(sdk.readKV("xxx", "a"))
}
```

> 把114514替换成你的cf token xxx替换成你的kv id