package com.zherikhov.shopai.openai.service;

import com.zherikhov.shopai.openai.api.ChatCompletionResponse;
import com.zherikhov.shopai.openai.api.ChatCompletionRequest;
import com.zherikhov.shopai.openai.api.Message;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGPTService {
    private final OpenAIClientService openAIClientService;

    public ChatGPTService(OpenAIClientService openAIClientService1) {
        this.openAIClientService = openAIClientService1;
    }

    @Nonnull
    public String getResponseForUser(Long userId, String userTextInput) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4o")
                .messages(List.of(Message.builder()
                        .content(userTextInput)
                        .role("user")
                        .build()))
                .build();
        ChatCompletionResponse response = openAIClientService.createChatCompletion(request);
        return response.choices().get(0).message().content();
    }

}
