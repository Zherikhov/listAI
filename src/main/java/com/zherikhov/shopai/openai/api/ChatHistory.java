package com.zherikhov.shopai.openai.api;

import lombok.Builder;

import java.util.List;

@Builder
public record ChatHistory(List<Message> messages) {
}
