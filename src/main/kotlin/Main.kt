package org.example

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.http.HttpHeader
import kotlinx.coroutines.runBlocking

fun main() {

    val accessTokenHeader = HttpHeader("X-Shopify-Access-Token", "your-access-token-here")
    val contentTypeHeader = HttpHeader("Content-Type", "application/json")
    val headers = listOf(accessTokenHeader, contentTypeHeader)

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://your-myshopify-domain-here/admin/api/unstable/graphql.json")
        .httpHeaders(headers)
        .build()

    runBlocking {
        val shopQuery = GetShopQuery()
        val result: ApolloResponse<GetShopQuery.Data> = apolloClient.query(shopQuery).execute()

        val errors = result.errors ?: listOf()

        // The exception field stores failed HTTP responses.
        check(result.exception == null)

        if (errors.isNotEmpty()) {
            println(errors)
        }

        val shop = checkNotNull(result.data?.shop)

        println(shop.id)
        println(shop.name)
    }

    apolloClient.close()
}