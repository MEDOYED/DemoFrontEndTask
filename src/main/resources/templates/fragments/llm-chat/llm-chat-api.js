// llm-chat-api.js - Логіка взаємодії з бекендом

/**
 * Відправляє запит користувача на бекенд і отримує HTML відповідь LLM
 * @param {string} question - Текст запитання користувача
 * @returns {Promise<string>} - HTML фрагмент з відповіддю LLM
 */
export async function fetchAIResponse(question) {
  console.log("📤 Відправка запиту на бекенд:", question);

  try {
    const response = await fetch("/llm/chat/ask-html", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ question: question }),
    });

    if (!response.ok) {
      throw new Error(`HTTP помилка! Статус: ${response.status}`);
    }

    const html = await response.text();
    console.log("✅ Відповідь отримано від бекенду");

    return html;
  } catch (error) {
    console.error("❌ Помилка при отриманні відповіді:", error);

    // Повертаємо HTML з повідомленням про помилку
    return `
          <div class="card ai-chat__assistant-message">
            <div class="ai-chat__label">LLM консультант</div>
            <p class="ai-chat__text-message" style="color: red;">
              ⚠️ Вибачте, сталася помилка. Спробуйте ще раз.
            </p>
          </div>
        `;
  }
}

/**
 * Додає HTML відповідь AI в секцію чату
 * @param {HTMLElement} chatSection - Секція чату куди додаємо відповідь
 * @param {string} html - HTML код для вставки
 */
export function addAIResponse(chatSection, html) {
  // Вставляємо HTML в кінець секції чату
  chatSection.insertAdjacentHTML("beforeend", html);

  console.log("✅ Відповідь AI додано в чат");
}
