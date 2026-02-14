document.addEventListener("DOMContentLoaded", () => {
    const root = document.createElement("div");
    root.className = "wrap";
    document.body.appendChild(root);

    root.innerHTML = `
    <div class="card">
      <div class="badge">✨ My Mini Server ✨</div>
      <h1>Linda’s Mini Java Server</h1>
      <p class="sub">
        Raw sockets → HTTP → Router → Response 
      </p>

      <button id="pingBtn">Test connection</button>
      <p id="status" class="status">Waiting…</p>

      <p class="tiny">Path: <span class="mono">${location.pathname}</span></p>
    </div>
  `;

    const status = document.getElementById("status");
    const btn = document.getElementById("pingBtn");

    btn.addEventListener("click", async () => {
        status.textContent = "Pinging…";

        const start = performance.now();
        try {
            const res = await fetch("/");
            const ms = Math.round(performance.now() - start);

            status.textContent = `✅ Connected! (${res.status}) • ${ms} ms`;
        } catch (e) {
            status.textContent = "❌ Connection failed";
        }
    });
});
