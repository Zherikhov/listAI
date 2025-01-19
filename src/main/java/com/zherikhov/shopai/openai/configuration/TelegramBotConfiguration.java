package com.zherikhov.shopai.openai.configuration;

import com.zherikhov.shopai.TelegramBot;
import com.zherikhov.shopai.openai.service.ChatGPTService;
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
    public TelegramBot telegramBot(@Value("${telegtam.token}") String botToken, TelegramBotsApi telegramBotsApi, ChatGPTService chatGPTService) {
        DefaultBotOptions botOptions = new DefaultBotOptions();
        TelegramBot telegramBot = new TelegramBot(botOptions, botToken, chatGPTService);
        telegramBotsApi.registerBot(telegramBot);

        return telegramBot;
    }

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}
