package com.zherikhov.shopai.openai.service;

import com.zherikhov.shopai.openai.api.ChatHistory;
import com.zherikhov.shopai.openai.api.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatGptHistory {
    private final Map<Long, ChatHistory> chatHistoryMap = new ConcurrentHashMap<>();

    public Optional<ChatHistory> getUserHistory(Long userId) {
        return Optional.ofNullable(chatHistoryMap.get(userId));
    }

    public void createHistory(Long userId) {
        chatHistoryMap.put(userId, new ChatHistory(new ArrayList<>()));
    }

    public ChatHistory addMessageToHistory(Long userId, Message message) {
        ChatHistory chatHistory = chatHistoryMap.get(userId);
        if (chatHistory == null) {
            throw new IllegalStateException("History not exists for user " + userId);
        }
        chatHistory.messages().add(message);

        return chatHistory;
    }

    public void createHistoryIfNotExist(Long userId) {
        if(!chatHistoryMap.containsKey(userId)) {
            createHistory(userId);
        }
    }
}
