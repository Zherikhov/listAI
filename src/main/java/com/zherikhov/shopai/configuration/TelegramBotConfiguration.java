package com.zherikhov.shopai.configuration;

import com.zherikhov.shopai.TelegramBot;
import com.zherikhov.shopai.service.OpenAIService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {

    @SneakyThrows
    @Bean
    public TelegramBot telegramBot(@Value("${telegtam.token}") String botToken, TelegramBotsApi telegramBotsApi, OpenAIService openAIService) {
        DefaultBotOptions botOptions = new DefaultBotOptions();
        TelegramBot telegramBot = new TelegramBot(botOptions, botToken, openAIService);
        telegramBotsApi.registerBot(telegramBot);

        return telegramBot;
    }

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}
