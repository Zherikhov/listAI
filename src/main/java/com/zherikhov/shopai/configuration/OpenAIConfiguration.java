package com.zherikhov.shopai.configuration;

import com.zherikhov.shopai.service.OpenAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

    @Bean
    public OpenAIService openAIService(@Value("${openai.token}") String botToken, RestTemplateBuilder restTemplate) {
        return new OpenAIService(botToken, restTemplate.build());
    }
}
