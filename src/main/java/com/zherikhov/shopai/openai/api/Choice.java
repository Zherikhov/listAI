package com.zherikhov.shopai.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(@JsonProperty("message") Message message) {

}
