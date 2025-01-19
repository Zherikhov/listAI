package com.zherikhov.shopai.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zherikhov.shopai.vo.Message;

public record Choice(@JsonProperty("message") Message message) {

}
