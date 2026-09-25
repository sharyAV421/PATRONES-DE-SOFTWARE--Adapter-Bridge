const device = document.getElementById("device");
const controller = document.getElementById("controller");
const status = document.getElementById("status");
const log = document.getElementById("log");
const deviceName = document.getElementById("device-name");
const commandCount = document.getElementById("command-count");
const clock = document.getElementById("clock");
const pulse = document.getElementById("pulse");

let commands = 0;

function updateClock() {
    const now = new Date();

    clock.textContent = now.toLocaleTimeString("en-US", {
        hour12: false
    });
}

setInterval(updateClock, 1000);
updateClock();

async function sendCommand(action) {

    try {

        const url =
            `/api?action=${action}&device=${device.value}&controller=${controller.value}`;

        const response = await fetch(url);
        const result = await response.text();

        status.textContent = result;

        deviceName.textContent =
            device.options[device.selectedIndex].text;

        commands++;
        commandCount.textContent = commands;

        addLog(action, result);
        animateCommand();

    } catch (error) {

        addLog("ERROR", "Connection failed");

    }
}

function addLog(action, result) {

    const time = new Date().toLocaleTimeString("en-US", {
        hour12: false
    });

    const entry = document.createElement("div");

    entry.className = "log-entry";

    entry.innerHTML =
        `<span>${time}</span> ${action.toUpperCase()} → ${result}`;

    log.prepend(entry);

    while (log.children.length > 10) {
        log.removeChild(log.lastChild);
    }
}

function animateCommand() {

    pulse.classList.remove("pulse-animation");

    void pulse.offsetWidth;

    pulse.classList.add("pulse-animation");
}