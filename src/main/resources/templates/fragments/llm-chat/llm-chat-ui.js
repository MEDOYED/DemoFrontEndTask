document.addEventListener("DOMContentLoaded", () => {
  const aiChatContent = document.getElementById(
    "ai-chat-content-fragment",
  );
  const searchBackdropBlur = aiChatContent?.querySelector(
    "[data-js-search-backdrop-blur]",
  );
  const searchInput = aiChatContent?.querySelector(
    "[data-js-search-input]",
  );

  if (!aiChatContent || !searchBackdropBlur || !searchInput) {
    return;
  }

  /**
   * Оновлює ширину та позицію search bar і backdrop blur
   * на основі розмірів контейнера ai-chat-content
   */
  const updateSearchWidth = () => {
    const widthAiChatContent = aiChatContent.offsetWidth;
    console.log("widthAiChatContent: " + widthAiChatContent);
    const rect = aiChatContent.getBoundingClientRect();

    searchBackdropBlur.style.width = `${widthAiChatContent}px`;

    if (window.innerWidth < 1200) {
      searchBackdropBlur.style.left = `${rect.left}px`;
    } else {
      searchBackdropBlur.style.left = `400px`; // distance from left border screen to searchBackdropBlur.
    }

    searchInput.style.width = `${widthAiChatContent}px`;
  };

  updateSearchWidth();

  const resizeObserver = new ResizeObserver(() => {
    updateSearchWidth();
  });

  resizeObserver.observe(aiChatContent);

  window.addEventListener("beforeunload", () => {
    resizeObserver.disconnect();
  });
});
