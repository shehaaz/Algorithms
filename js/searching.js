(function () {
  "use strict";

  const ALGO_INFO = {
    linear: {
      title: "Linear Search",
      desc: "Walks the array from left to right comparing each element to the target. Works on unsorted data too, but takes up to n comparisons.",
      complexity: ["Time best: O(1)", "Time avg/worst: O(n)", "Space: O(1)"],
    },
    binary: {
      title: "Binary Search",
      desc: "Requires a sorted array. Each step compares the middle element to the target and discards the half that cannot contain it.",
      complexity: ["Time: O(log n)", "Space: O(1) iterative", "Requires sorted input"],
    },
  };

  const $ = (id) => document.getElementById(id);
  const barsEl = $("bars");
  const labelsEl = $("labels");
  const algoEl = $("algo");
  const sizeEl = $("size");
  const sizeValEl = $("sizeVal");
  const targetEl = $("target");
  const speedEl = $("speed");
  const runBtn = $("run");
  const stopBtn = $("stop");
  const shuffleBtn = $("shuffle");
  const stepsEl = $("steps");
  const rangeEl = $("range");
  const statusEl = $("status");
  const infoTitle = $("info-title");
  const infoDesc = $("info-desc");
  const infoComplexity = $("info-complexity");

  let arr = [];
  let bars = [];
  let running = false;
  let cancelToken = 0;
  let stepCount = 0;

  const sleep = (ms) => new Promise((r) => setTimeout(r, ms));
  const stepDelay = () => Math.max(40, (101 - Number(speedEl.value)) * 8);

  function generate(n) {
    const set = new Set();
    while (set.size < n) set.add(Math.floor(Math.random() * 99) + 1);
    arr = Array.from(set).sort((a, b) => a - b);
    if (!targetEl.value || Number(targetEl.value) < 1) {
      targetEl.value = arr[Math.floor(Math.random() * arr.length)];
    }
    render();
  }

  function render() {
    barsEl.innerHTML = "";
    labelsEl.innerHTML = "";
    bars = arr.map((v) => {
      const b = document.createElement("div");
      b.className = "bar";
      b.style.height = `${v}%`;
      barsEl.appendChild(b);
      const lbl = document.createElement("span");
      lbl.textContent = v;
      labelsEl.appendChild(lbl);
      return b;
    });
  }

  function clearMarks() {
    bars.forEach((b) => b.classList.remove("checked", "found", "cut", "mid"));
  }

  function setRange(lo, hi) {
    if (lo > hi) {
      rangeEl.textContent = "empty";
    } else {
      rangeEl.textContent = `[${lo}, ${hi}]`;
    }
  }

  function pruneOutside(lo, hi) {
    bars.forEach((b, idx) => {
      if (idx < lo || idx > hi) b.classList.add("cut");
    });
  }

  async function linearSearch(target, token) {
    setRange(0, arr.length - 1);
    for (let i = 0; i < arr.length; i++) {
      if (token !== cancelToken) throw new Error("cancel");
      stepCount++;
      stepsEl.textContent = stepCount;
      bars[i].classList.add("checked");
      await sleep(stepDelay());
      if (arr[i] === target) {
        bars[i].classList.remove("checked");
        bars[i].classList.add("found");
        return i;
      }
      bars[i].classList.remove("checked");
      bars[i].classList.add("cut");
    }
    return -1;
  }

  async function binarySearch(target, token) {
    let lo = 0, hi = arr.length - 1;
    while (lo <= hi) {
      if (token !== cancelToken) throw new Error("cancel");
      setRange(lo, hi);
      const mid = (lo + hi) >> 1;
      stepCount++;
      stepsEl.textContent = stepCount;
      bars[mid].classList.add("checked", "mid");
      await sleep(stepDelay());
      if (arr[mid] === target) {
        bars[mid].classList.remove("checked");
        bars[mid].classList.add("found");
        return mid;
      }
      if (arr[mid] < target) {
        for (let k = lo; k <= mid; k++) bars[k].classList.add("cut");
        lo = mid + 1;
      } else {
        for (let k = mid; k <= hi; k++) bars[k].classList.add("cut");
        hi = mid - 1;
      }
      bars[mid].classList.remove("checked", "mid");
      await sleep(stepDelay() / 2);
    }
    setRange(lo, hi);
    return -1;
  }

  async function run() {
    if (running) return;
    const target = Number(targetEl.value);
    if (!Number.isFinite(target)) {
      statusEl.textContent = "enter a target";
      return;
    }
    running = true;
    cancelToken++;
    const token = cancelToken;
    stepCount = 0;
    stepsEl.textContent = 0;
    clearMarks();
    statusEl.textContent = "searching";
    runBtn.disabled = true;
    shuffleBtn.disabled = true;
    algoEl.disabled = true;
    sizeEl.disabled = true;
    targetEl.disabled = true;
    stopBtn.disabled = false;

    try {
      const fn = algoEl.value === "linear" ? linearSearch : binarySearch;
      const idx = await fn(target, token);
      statusEl.textContent = idx >= 0 ? `found at ${idx}` : "not found";
    } catch (_e) {
      statusEl.textContent = "stopped";
    } finally {
      running = false;
      runBtn.disabled = false;
      shuffleBtn.disabled = false;
      algoEl.disabled = false;
      sizeEl.disabled = false;
      targetEl.disabled = false;
      stopBtn.disabled = true;
    }
  }

  function stop() { cancelToken++; }

  function updateInfo() {
    const info = ALGO_INFO[algoEl.value];
    infoTitle.textContent = info.title;
    infoDesc.textContent = info.desc;
    infoComplexity.innerHTML = info.complexity.map((c) => `<span>${c}</span>`).join("");
  }

  sizeEl.addEventListener("input", () => {
    sizeValEl.textContent = sizeEl.value;
    if (!running) generate(Number(sizeEl.value));
  });
  shuffleBtn.addEventListener("click", () => {
    targetEl.value = "";
    generate(Number(sizeEl.value));
  });
  runBtn.addEventListener("click", run);
  stopBtn.addEventListener("click", stop);
  algoEl.addEventListener("change", updateInfo);

  updateInfo();
  generate(Number(sizeEl.value));
})();
