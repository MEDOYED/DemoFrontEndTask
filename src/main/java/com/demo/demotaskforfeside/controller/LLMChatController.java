package com.demo.demotaskforfeside.controller;

import com.demo.demotaskforfeside.dto.LLMChatQuestionRequest;
import com.demo.demotaskforfeside.dto.LLMChatResponse;
import com.demo.demotaskforfeside.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LLMChatController {
    private static final String MOCKED_SYSTEM_MESSAGE = "Опишіть товари який вам потрібний або набір " +
            "товарів який потрібно порівняти";

    // API used to render the initial LLM chat page
    @GetMapping("/llm/chat/index")
    public String aiChatPageNew(Model model) {
        model.addAttribute("systemMessage", MOCKED_SYSTEM_MESSAGE);

        return "/pages/ai-chat-page/ai-chat-page";
    }

    // API used to ask LLM questions and get a response
    @ResponseBody
    @PostMapping(value = "/llm/chat/ask")
    public LLMChatResponse chat(@RequestBody LLMChatQuestionRequest request) {

        return createDummyLLMChatResponse();
    }

    private LLMChatResponse createDummyLLMChatResponse() {
        List<String> knowlageSource = List.of(
                "mocked_llm_response",
                "product_catalog",
                "internal_demo_data"
        );

        List<String> answers = List.of(
                "Пропоную підбірку драбин різних типів і брендів для побутових та професійних робіт.",
                "Є приставні, шарнірні, 3-секційні та трансформери. Скажіть потрібну висоту або спосіб використання — підберу оптимальний варіант."
        );

        List<ProductDto> products = List.of(
                new ProductDto(
                        "Драбина APRO алюмінієва приставна 7 сходинок",
                        "https://example.com/images/apro-7.png",
                        "Алюмінієва приставна драбина. Висота 1,95 м, вага 2,9 кг, максимальне навантаження 150 кг.",
                        "552000"
                ),
                new ProductDto(
                        "Драбина алюмінієва DETEX шарнірна 2х10",
                        "https://example.com/images/detex-2x10.png",
                        "Багатофункціональна шарнірна драбина з 8 варіантами трансформації. Максимальне навантаження 150 кг.",
                        "DLH-2s10"
                ),
                new ProductDto(
                        "Драбина розкладна 3-секційна 3x12",
                        "https://example.com/images/3x12.png",
                        "3-секційна драбина довжиною до 8,5 м, вага 16,2 кг. Підходить для професійних робіт.",
                        "05-01-0071"
                ),
                new ProductDto(
                        "Драбина металева MAX 4-х сходинкова",
                        "https://example.com/images/max-4.png",
                        "Побутова металева драбина з широкими антиковзкими сходинками. Висота 127 см.",
                        "2104"
                ),
                new ProductDto(
                        "Драбина трансформер 4x4 (4,31 м)",
                        "https://example.com/images/transformer-4x4.png",
                        "Алюмінієва драбина-трансформер для будівельних, монтажних та побутових робіт.",
                        "EN131"
                )
        );

        return new LLMChatResponse(
                knowlageSource,
                answers,
                products
        );
    }
}
