document.addEventListener("DOMContentLoaded", () => {
    const tabs = document.querySelectorAll("#post-tabs a");
    const container = document.getElementById("posts-container");

    if (!container) {
        console.error("posts-container not found");
        return;
    }

    tabs.forEach(tab => {
        tab.addEventListener("click", e => {
            e.preventDefault();
            const sort = tab.getAttribute("data-sort");

            tabs.forEach(t => t.classList.remove("border-b-2", "border-black", "pb-1"));
            tabs.forEach(t => t.classList.add("text-gray-500", "font-semibold"));
            tab.classList.add("border-b-2", "border-black", "pb-1");
            tab.classList.remove("text-gray-500", "font-semibold");

            fetch(`/posts?sort=${sort}`, {
                headers: {
                    "X-Requested-With": "XMLHttpRequest"
                }
            })
                .then(response => {
                    if (!response.ok) throw new Error("Network error");
                    return response.text();
                })
                .then(html => {
                    container.innerHTML = html;
                    history.pushState(null, "", `/posts?sort=${sort}`);
                })
                .catch(err => {
                    alert("Lỗi tải bài viết: " + err.message);
                });
        });
    });

    // Xử lý back/forward browser để load đúng tab
    window.addEventListener("popstate", () => {
        const params = new URLSearchParams(window.location.search);
        const sort = params.get("sort") || "new";

        // Tự trigger sự kiện click trên tab tương ứng
        const activeTab = Array.from(tabs).find(t => t.getAttribute("data-sort") === sort);
        if (activeTab) activeTab.click();
    });
});
