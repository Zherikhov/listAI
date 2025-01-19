package com.zherikhov.shopai;

import com.zherikhov.shopai.service.OpenAIService;
import com.zherikhov.shopai.util.ResourcesUtil;
import com.zherikhov.shopai.vo.ChatCompletionObject;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
    private final OpenAIService openAIService;

    public TelegramBot(DefaultBotOptions options, String botToken, OpenAIService openAIService) {
        super(options, botToken);
        this.openAIService = openAIService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            ChatCompletionObject chatCompletion = openAIService.createChatCompletion(text);
            String content = chatCompletion.choices().get(0).message().content();

            SendMessage sendMessage = new SendMessage(chatId.toString(), content);
            sendApiMethod(sendMessage);
        }
    }

    @Override
    public String getBotUsername() {
        return ResourcesUtil.getProperties("application.secure.properties", "telegram.name");
    }
}
