(function () {
  "use strict";

  const ALGO_INFO = {
    bubble: {
      title: "Bubble Sort",
      desc: "Repeatedly walks the list, swapping adjacent items that are out of order. After each pass the largest unsorted value bubbles to the end.",
      complexity: ["Time best: O(n)", "Time avg: O(n²)", "Time worst: O(n²)", "Space: O(1)", "Stable"],
    },
    insertion: {
      title: "Insertion Sort",
      desc: "Builds the sorted region one element at a time by shifting larger neighbours to the right. Fast on nearly-sorted data.",
      complexity: ["Time best: O(n)", "Time avg: O(n²)", "Time worst: O(n²)", "Space: O(1)", "Stable"],
    },
    selection: {
      title: "Selection Sort",
      desc: "Scans the unsorted region for the minimum element, then swaps it into the next sorted slot. Simple but quadratic.",
      complexity: ["Time: O(n²)", "Space: O(1)", "Unstable"],
    },
    merge: {
      title: "Merge Sort",
      desc: "Divide and conquer: splits the array in half, sorts each side recursively, and merges them. Reliable O(n log n) performance.",
      complexity: ["Time: O(n log n)", "Space: O(n)", "Stable"],
    },
    quick: {
      title: "Quick Sort",
      desc: "Picks a pivot, partitions values around it, and recurses on the two halves. Very fast in practice on random data.",
      complexity: ["Time avg: O(n log n)", "Time worst: O(n²)", "Space: O(log n)", "Unstable"],
    },
  };

  const $ = (id) => document.getElementById(id);
  const barsEl = $("bars");
  const algoEl = $("algo");
  const sizeEl = $("size");
  const sizeValEl = $("sizeVal");
  const speedEl = $("speed");
  const runBtn = $("run");
  const stopBtn = $("stop");
  const shuffleBtn = $("shuffle");
  const cmpEl = $("cmp");
  const swpEl = $("swp");
  const statusEl = $("status");
  const infoTitleEl = $("info-title");
  const infoDescEl = $("info-desc");
  const infoComplexityEl = $("info-complexity");

  let arr = [];
  let bars = [];
  let running = false;
  let cancelToken = 0;
  let cmpCount = 0;
  let swpCount = 0;

  function sleep(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }

  function stepDelay() {
    const v = Number(speedEl.value);
    const inv = 101 - v;
    return Math.max(1, inv * 1.2);
  }

  function generate(n) {
    arr = Array.from({ length: n }, () => Math.floor(Math.random() * 95) + 5);
    render();
  }

  function render() {
    barsEl.innerHTML = "";
    bars = arr.map((v) => {
      const b = document.createElement("div");
      b.className = "bar";
      b.style.height = `${v}%`;
      barsEl.appendChild(b);
      return b;
    });
  }

  function setClass(i, cls) {
    if (i < 0 || i >= bars.length) return;
    bars[i].classList.add(cls);
  }
  function clearClass(i, cls) {
    if (i < 0 || i >= bars.length) return;
    bars[i].classList.remove(cls);
  }
  function clearAll(cls) {
    bars.forEach((b) => b.classList.remove(cls));
  }

  function setHeight(i, v) {
    arr[i] = v;
    bars[i].style.height = `${v}%`;
  }

  async function compare(i, j, token) {
    if (token !== cancelToken) throw new Error("cancel");
    cmpCount++;
    cmpEl.textContent = cmpCount;
    setClass(i, "compare");
    setClass(j, "compare");
    await sleep(stepDelay());
    clearClass(i, "compare");
    clearClass(j, "compare");
  }

  async function swap(i, j, token) {
    if (token !== cancelToken) throw new Error("cancel");
    swpCount++;
    swpEl.textContent = swpCount;
    setClass(i, "swap");
    setClass(j, "swap");
    const tmp = arr[i];
    setHeight(i, arr[j]);
    setHeight(j, tmp);
    await sleep(stepDelay());
    clearClass(i, "swap");
    clearClass(j, "swap");
  }

  async function write(i, v, token) {
    if (token !== cancelToken) throw new Error("cancel");
    swpCount++;
    swpEl.textContent = swpCount;
    setClass(i, "swap");
    setHeight(i, v);
    await sleep(stepDelay());
    clearClass(i, "swap");
  }

  // ----- algorithms -----

  async function bubbleSort(token) {
    const n = arr.length;
    for (let end = n - 1; end > 0; end--) {
      let swapped = false;
      for (let i = 0; i < end; i++) {
        await compare(i, i + 1, token);
        if (arr[i] > arr[i + 1]) {
          await swap(i, i + 1, token);
          swapped = true;
        }
      }
      setClass(end, "sorted");
      if (!swapped) {
        for (let k = 0; k < end; k++) setClass(k, "sorted");
        return;
      }
    }
    setClass(0, "sorted");
  }

  async function insertionSort(token) {
    const n = arr.length;
    setClass(0, "sorted");
    for (let i = 1; i < n; i++) {
      let j = i;
      setClass(j, "compare");
      while (j > 0) {
        await compare(j - 1, j, token);
        if (arr[j - 1] > arr[j]) {
          await swap(j - 1, j, token);
          j--;
        } else {
          break;
        }
      }
      setClass(i, "sorted");
    }
  }

  async function selectionSort(token) {
    const n = arr.length;
    for (let i = 0; i < n; i++) {
      let min = i;
      setClass(min, "pivot");
      for (let j = i + 1; j < n; j++) {
        await compare(min, j, token);
        if (arr[j] < arr[min]) {
          clearClass(min, "pivot");
          min = j;
          setClass(min, "pivot");
        }
      }
      clearClass(min, "pivot");
      if (min !== i) await swap(i, min, token);
      setClass(i, "sorted");
    }
  }

  async function mergeSort(token) {
    const n = arr.length;
    const aux = arr.slice();

    async function merge(lo, mid, hi) {
      for (let k = lo; k <= hi; k++) aux[k] = arr[k];
      let i = lo, j = mid + 1;
      for (let k = lo; k <= hi; k++) {
        setClass(k, "compare");
        if (i > mid) {
          await write(k, aux[j++], token);
        } else if (j > hi) {
          await write(k, aux[i++], token);
        } else {
          cmpCount++;
          cmpEl.textContent = cmpCount;
          if (aux[j] < aux[i]) {
            await write(k, aux[j++], token);
          } else {
            await write(k, aux[i++], token);
          }
        }
        clearClass(k, "compare");
      }
    }

    async function sort(lo, hi) {
      if (lo >= hi) return;
      const mid = (lo + hi) >> 1;
      await sort(lo, mid);
      await sort(mid + 1, hi);
      await merge(lo, mid, hi);
    }

    await sort(0, n - 1);
    for (let k = 0; k < n; k++) setClass(k, "sorted");
  }

  async function quickSort(token) {
    async function partition(lo, hi) {
      const pivot = arr[hi];
      setClass(hi, "pivot");
      let i = lo;
      for (let j = lo; j < hi; j++) {
        await compare(j, hi, token);
        if (arr[j] < pivot) {
          if (i !== j) await swap(i, j, token);
          i++;
        }
      }
      if (i !== hi) await swap(i, hi, token);
      clearClass(hi, "pivot");
      return i;
    }
    async function sort(lo, hi) {
      if (lo >= hi) {
        if (lo === hi) setClass(lo, "sorted");
        return;
      }
      const p = await partition(lo, hi);
      setClass(p, "sorted");
      await sort(lo, p - 1);
      await sort(p + 1, hi);
    }
    await sort(0, arr.length - 1);
  }

  const RUNNERS = {
    bubble: bubbleSort,
    insertion: insertionSort,
    selection: selectionSort,
    merge: mergeSort,
    quick: quickSort,
  };

  function updateInfo() {
    const info = ALGO_INFO[algoEl.value];
    infoTitleEl.textContent = info.title;
    infoDescEl.textContent = info.desc;
    infoComplexityEl.innerHTML = info.complexity.map((c) => `<span>${c}</span>`).join("");
  }

  async function run() {
    if (running) return;
    running = true;
    cancelToken++;
    const token = cancelToken;
    cmpCount = 0;
    swpCount = 0;
    cmpEl.textContent = 0;
    swpEl.textContent = 0;
    statusEl.textContent = "running";
    runBtn.disabled = true;
    shuffleBtn.disabled = true;
    algoEl.disabled = true;
    sizeEl.disabled = true;
    stopBtn.disabled = false;

    bars.forEach((b) => {
      b.classList.remove("compare", "swap", "sorted", "pivot");
    });

    try {
      await RUNNERS[algoEl.value](token);
      statusEl.textContent = "done";
    } catch (_e) {
      statusEl.textContent = "stopped";
    } finally {
      running = false;
      runBtn.disabled = false;
      shuffleBtn.disabled = false;
      algoEl.disabled = false;
      sizeEl.disabled = false;
      stopBtn.disabled = true;
    }
  }

  function stop() {
    cancelToken++;
  }

  sizeEl.addEventListener("input", () => {
    sizeValEl.textContent = sizeEl.value;
    if (!running) generate(Number(sizeEl.value));
  });
  shuffleBtn.addEventListener("click", () => generate(Number(sizeEl.value)));
  runBtn.addEventListener("click", run);
  stopBtn.addEventListener("click", stop);
  algoEl.addEventListener("change", updateInfo);

  updateInfo();
  generate(Number(sizeEl.value));
})();
