const device = document.getElementById("device");
const controller = document.getElementById("controller");
const status = document.getElementById("status");
const log = document.getElementById("log");

async function sendCommand(action) {

    const url =
        `/api?action=${action}&device=${device.value}&controller=${controller.value}`;

    const response = await fetch(url);
    const result = await response.text();

    status.textContent = result;

    const entry = document.createElement("div");
    entry.className = "log-entry";
    entry.textContent = `> ${action.toUpperCase()} - ${result}`;

    log.prepend(entry);
}