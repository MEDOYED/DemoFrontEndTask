document.addEventListener("DOMContentLoaded", () => {
  console.log("llm-chat.js loaded!");

  const chatMessageForm = document.getElementById(
    "chat-message-form",
  );
  const messageInput = document.getElementById("message-input");
  const chatSection = document.getElementById("chat-section");

  if (!chatMessageForm) {
    console.log("Форма для відправки повідомлень не знайдена");
    return;
  }

  if (!messageInput) {
    console.log(
      "Input для вписання нового повідомлення не знайдений",
    );
    return;
  }

  if (!chatSection) {
    console.log("Секція чату не знайдена");
  }

  console.log("всі елементи знайдено успішно");

  const userMessageTemplate = document.getElementById(
    "user-message-template",
  );

  if (!userMessageTemplate) {
    console.log("Tempalte для повідомлення користувача не знайдено");
    return;
  }

  // Функція для додавання повідомлення користувача в чат
  function addUserMessage(messageText) {
    // Клонуємо template
    const cloneTemplate = userMessageTemplate.content.cloneNode(true);

    // Знаходження параграфу і вставляєто текст що вводить користувач в messageInput
    const textElement = cloneTemplate.getElementById(
      "user-message-paragraph",
    );

    textElement.textContent = messageText;

    // додаємо в чат
    chatSection.appendChild(cloneTemplate);

    console.log("Повідомлення користувача додано: ", messageText);
  }

  //  Обробник відправки форми
  chatMessageForm.addEventListener("submit", event => {
    event.preventDefault();

    console.log("Форму відправлено");

    // Отримуємо текст з input
    const userMessage = messageInput.value.trim();

    if (!userMessage) {
      console.log("Повідомлення пусте, нічого не відправляємо");
      return;
    }

    console.log("Текст повідомлення: ", userMessage);

    // Додаємо повідомлення користувача в чат
    addUserMessage(userMessage);

    // Очищаємо input
    messageInput.value = "";
  });
});
