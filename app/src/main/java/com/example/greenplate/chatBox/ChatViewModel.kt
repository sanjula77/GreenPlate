package com.example.greenplate.chatBox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> get() = _messages

    fun sendMessage(userInput: String) {
        // Add user's message to the list
        _messages.value = _messages.value + "You: $userInput"
        fetchAIResponse(userInput)
    }

    private fun fetchAIResponse(input: String) {
        viewModelScope.launch {
            try {
                val response = DeepSeekService.getAIResponse(input)  // Call DeepSeekService here
                // Add AI's response to the list
                _messages.value = _messages.value + "AI: $response"
            } catch (e: Exception) {
                // In case of an error, display the error message
                _messages.value = _messages.value + "Error: ${e.message}"
            }
        }
    }
}
