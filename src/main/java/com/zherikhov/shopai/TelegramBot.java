package com.zherikhov.shopai;

import com.zherikhov.shopai.openai.service.ChatGPTService;
import com.zherikhov.shopai.util.ResourcesUtil;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
    private final ChatGPTService chatGPTService;

    public TelegramBot(DefaultBotOptions options, String botToken, ChatGPTService chatGPTService) {
        super(options, botToken);
        this.chatGPTService = chatGPTService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            String gptGenerateText = chatGPTService.getResponseForUser(chatId, text);

            SendMessage sendMessage = new SendMessage(chatId.toString(), gptGenerateText);
            sendApiMethod(sendMessage);
        }
    }

    @Override
    public String getBotUsername() {
        return ResourcesUtil.getProperties("application.secure.properties", "telegram.name");
    }
}
