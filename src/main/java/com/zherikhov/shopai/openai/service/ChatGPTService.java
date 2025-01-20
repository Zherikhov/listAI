package com.zherikhov.shopai.openai.service;

import com.zherikhov.shopai.openai.api.ChatCompletionRequest;
import com.zherikhov.shopai.openai.api.ChatCompletionResponse;
import com.zherikhov.shopai.openai.api.ChatHistory;
import com.zherikhov.shopai.openai.api.Message;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {
    private final OpenAIClientService openAIClientService;
    private final ChatGptHistory chatGptHistory;

    public ChatGPTService(OpenAIClientService openAIClientService1, ChatGptHistory chatGptHistory) {
        this.openAIClientService = openAIClientService1;
        this.chatGptHistory = chatGptHistory;
    }

    @Nonnull
    public String getResponseForUser(Long userId, String userTextInput) {
        chatGptHistory.createHistoryIfNotExist(userId);
        ChatHistory chatHistory = chatGptHistory.addMessageToHistory(userId,
                Message.builder()
                        .content(userTextInput)
                        .role("user")
                        .build());

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4o")
                .messages(chatHistory.messages())
                .build();
        ChatCompletionResponse response = openAIClientService.createChatCompletion(request);

        Message messageFromGpt = response.choices().get(0).message();
        chatGptHistory.addMessageToHistory(userId, messageFromGpt);

        return messageFromGpt.content();
    }

}
