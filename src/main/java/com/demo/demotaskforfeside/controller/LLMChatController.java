package com.demo.demotaskforfeside.controller;

import com.demo.demotaskforfeside.dto.LLMChatQuestionRequest;
import com.demo.demotaskforfeside.dto.LLMChatResponse;
import com.demo.demotaskforfeside.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class LLMChatController {
    private static final String MOCKED_SYSTEM_MESSAGE = "Опишіть товар який вам потрібний або набір " +
            "товарів який потрібно порівняти";

    // API used to render the initial LLM chat page
    @GetMapping("/llm/chat/index")
    public String aiChatPageNew(Model model) {
        model.addAttribute("systemMessage", MOCKED_SYSTEM_MESSAGE);

        return "/pages/llm-product-search-page/llm-product-search-page";
    }

    // API used to ask LLM questions and get a response
    @ResponseBody
    @PostMapping(value = "/llm/chat/ask")
    public LLMChatResponse chat(@RequestBody LLMChatQuestionRequest request) {

        return createDummyLLMChatResponse();
    }
    
    // API для отримання HTML фрагменту з відповіддю AI
     @PostMapping(value = "/llm/chat/ask-html")
     public String chatHtml(@RequestBody LLMChatQuestionRequest request, Model model) {
         log.info("Отримано запит на /llm/chat/ask-html: {}", request.question());

         // Отримуємо відповідь (зараз мок, потім буде AI)
         LLMChatResponse response = createDummyLLMChatResponse();

         // Додаємо дані в модель для Thymeleaf
         model.addAttribute("knowledgeSource", response.knowledgeSource());
         model.addAttribute("answers", response.answers());
         model.addAttribute("products", response.products());

         // Повертаємо тільки HTML фрагмент (не всю сторінку!)
         return "fragments/llm-chat/llm-response-fragment :: aiResponse";
     }

    private LLMChatResponse createDummyLLMChatResponse() {
        List<String> knowledgeSource = List.of(
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
                        "https://plus.unsplash.com/premium_photo-1711036292383-dd2c17c6fcd6?fm=jpg&q=80&w=800",
                        "Алюмінієва приставна драбина. Висота 1,95 м, вага 2,9 кг, максимальне навантаження 150 кг.",
                        "552000"
                ),
                new ProductDto(
                        "Драбина алюмінієва DETEX шарнірна 2х10",
                        "https://images.unsplash.com/photo-1658669742598-2f70354c3326?fm=jpg&q=80&w=800",
                        "Багатофункціональна шарнірна драбина з 8 варіантами трансформації. Максимальне навантаження 150 кг.",
                        "DLH-2s10"
                ),
                new ProductDto(
                        "Драбина розкладна 3-секційна 3x12",
                        "https://images.pexels.com/photos/10623056/pexels-photo-10623056.jpeg?auto=compress&cs=tinysrgb&w=800",
                        "3-секційна драбина довжиною до 8,5 м, вага 16,2 кг. Підходить для професійних робіт.",
                        "05-01-0071"
                ),
                new ProductDto(
                        "Драбина металева MAX 4-х сходинкова",
                        "https://images.pexels.com/photos/10211688/pexels-photo-10211688.jpeg?auto=compress&cs=tinysrgb&w=800",
                        "Побутова металева драбина з широкими антиковзкими сходинками. Висота 127 см.",
                        "2104"
                ),
                new ProductDto(
                        "Драбина трансформер 4x4 (4,31 м)",
                        "https://images.pexels.com/photos/10211688/pexels-photo-10211688.jpeg?auto=compress&cs=tinysrgb&w=800",
                        "Алюмінієва драбина-трансформер для будівельних, монтажних та побутових робіт.",
                        "EN131"
                )
        );

        return new LLMChatResponse(
                knowledgeSource,
                answers,
                products
        );
    }
}
