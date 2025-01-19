package com.zherikhov.shopai.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(@JsonProperty("role") String role, @JsonProperty("content") String content) {

}
