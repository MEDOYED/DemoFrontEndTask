document.addEventListener("DOMContentLoaded", () => {
  const aiChatContent = document.getElementById(
    "ai-chat-content-fragment",
  );
  const searchBackdropBlur = document.getElementById(
    "search-backdrop-blur",
  );
  const searchInput = document.getElementById("message-input");

  if (!aiChatContent || !searchBackdropBlur || !searchInput) {
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

    searchInput.style.width = `${widthAiChatContent}px`;
  };

  updateSearchWidth();

  window.addEventListener("resize", () => {
    updateSearchWidth();
  });

  window.addEventListener("beforeunload", () => {
    resizeObserver.disconnect();
  });
});
