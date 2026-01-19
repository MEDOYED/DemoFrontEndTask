package com.demo.demotaskforfeside.dto;

import java.util.List;

public record LLMChatResponse(
        List<String> knowledgeSource,
        List<String> answers,
        List<ProductDto> products
) {
}