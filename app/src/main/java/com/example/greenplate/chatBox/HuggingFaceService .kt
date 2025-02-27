package com.example.greenplate.chatBox

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

object DeepSeekService {
    private val client = HttpClient()

    @OptIn(InternalAPI::class)
    suspend fun getAIResponse(input: String): String {
        val apiKey = "YOUR_DEEPSEEK_API_KEY" // Replace with your DeepSeek API key
        val url = "https://api.deepseek.com/chat/completions" // DeepSeek API endpoint

        val response: HttpResponse = client.post(url) {
            headers {
                append("Authorization", "Bearer $apiKey")
                append("Content-Type", "application/json")
            }
            body = """
                {
                    "model": "deepseek-chat",
                    "messages": [
                        {"role": "system", "content": "You are a helpful assistant."},
                        {"role": "user", "content": "$input"}
                    ],
                    "stream": false
                }
            """.trimIndent()
        }

        val responseBody = response.bodyAsText()
        println("Response Body: $responseBody")  // Log the response for debugging

        val jsonResponse = Json.parseToJsonElement(responseBody)

        // Check if the response has the 'choices' array and extract the response
        return try {
            // Look for choices and get the content from the message
            if (jsonResponse.jsonObject.containsKey("choices")) {
                val choices = jsonResponse.jsonObject["choices"]?.jsonArray
                val message = choices?.get(0)?.jsonObject?.get("message")?.jsonObject
                message?.get("content")?.jsonPrimitive?.content ?: "No response"
            } else {
                "Error: 'choices' field not found"
            }
        } catch (e: Exception) {
            "Error parsing response: ${e.message}"
        }
    }
}
