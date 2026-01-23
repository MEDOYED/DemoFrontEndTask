document.addEventListener("DOMContentLoaded", () => {
  const aiChatContent = document.getElementById(
    "ai-chat-content-fragment",
  );
  const searchBackdropBlur = document.getElementById(
    "search-backdrop-blur",
  );
  const chatMessageForm = document.getElementById(
    "chat-message-form",
  );

  if (!aiChatContent || !searchBackdropBlur || !chatMessageForm) {
    return;
  }

  /**
   * Оновлює ширину та позицію search bar і backdrop blur
   * на основі розмірів контейнера ai-chat-content
   */
  const updateSearchWidth = () => {
    const widthAiChatContent = aiChatContent.offsetWidth;

    const rect = aiChatContent.getBoundingClientRect();

    searchBackdropBlur.style.width = `${widthAiChatContent}px`;

    searchBackdropBlur.style.left = `${rect.left}px`;

    chatMessageForm.style.width = `${widthAiChatContent}px`;
  };

  updateSearchWidth();

  window.addEventListener("resize", () => {
    updateSearchWidth();
  });
});
