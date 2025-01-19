package com.zherikhov.shopai.openai.configuration;

import com.zherikhov.shopai.openai.service.OpenAIClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

    @Bean
    public OpenAIClientService openAIService(@Value("${openai.token}") String botToken, RestTemplateBuilder restTemplate) {
        return new OpenAIClientService(botToken, restTemplate.build());
    }
}
