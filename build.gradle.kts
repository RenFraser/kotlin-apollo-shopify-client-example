plugins {
    kotlin("jvm") version "1.9.21"
    id("com.apollographql.apollo3") version "4.0.0-beta.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:4.0.0-beta.4")
}

apollo {
    service("service") {
        packageName.set("org.example")

        introspection {
            headers.set(
                mapOf(
                    "X-Shopify-Access-Token" to "your-access-token-here",
                    "Content-Type" to "application/json"
                )
            )
            endpointUrl.set("https://your-myshopify-domain-here/admin/api/unstable/graphql.json")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }
    }

}

kotlin {
    jvmToolchain(17)
}