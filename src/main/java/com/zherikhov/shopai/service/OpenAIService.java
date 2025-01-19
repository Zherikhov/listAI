package com.zherikhov.shopai.service;

import com.zherikhov.shopai.vo.ChatCompletionObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class OpenAIService {

    private final String token;
    private final RestTemplate restTemplate;

    public OpenAIService(String token, RestTemplate restTemplate) {
        this.token = token;
        this.restTemplate = restTemplate;
    }

    public ChatCompletionObject createChatCompletion(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Content-type", "application/json");

        String request = """
                {
                    "model": "gpt-4o",
                    "messages": [
                      {
                        "role": "assistant",
                        "content": "You are a helpful assistant."
                      },
                      {
                        "role": "user",
                        "content": "%s"
                      }
                    ]
                  }""".formatted(message);

        HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, ChatCompletionObject.class).getBody();
    }
}
