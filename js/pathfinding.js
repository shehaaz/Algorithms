(function () {
  "use strict";

  const ALGO_INFO = {
    bfs: {
      title: "Breadth-First Search",
      desc: "Explores neighbours layer by layer using a FIFO queue. On an unweighted graph it always finds the shortest path in terms of edges.",
      complexity: ["Time: O(V + E)", "Space: O(V)", "Optimal on unweighted graphs"],
    },
    dfs: {
      title: "Depth-First Search",
      desc: "Goes as deep as possible before backtracking, using a stack (or recursion). Not optimal for shortest paths but useful for connectivity and cycle detection.",
      complexity: ["Time: O(V + E)", "Space: O(V)", "Not shortest-path"],
    },
    dijkstra: {
      title: "Dijkstra's Algorithm",
      desc: "Generalizes BFS to weighted graphs by repeatedly expanding the unvisited node with the smallest known distance. Here every edge has weight 1, so it behaves like BFS but using a priority queue.",
      complexity: ["Time: O((V + E) log V)", "Space: O(V)", "Optimal with non-negative weights"],
    },
  };

  const $ = (id) => document.getElementById(id);
  const board = $("board");
  const algoEl = $("algo");
  const colsEl = $("cols");
  const rowsEl = $("rows");
  const colsValEl = $("colsVal");
  const rowsValEl = $("rowsVal");
  const speedEl = $("speed");
  const runBtn = $("run");
  const stopBtn = $("stop");
  const clearBtn = $("clear");
  const randomBtn = $("random");
  const visitedEl = $("visited");
  const pathEl = $("path");
  const statusEl = $("status");
  const infoTitle = $("info-title");
  const infoDesc = $("info-desc");
  const infoComplexity = $("info-complexity");

  const EMPTY = 0, WALL = 1, START = 2, END = 3;

  let cols = Number(colsEl.value);
  let rows = Number(rowsEl.value);
  let grid = [];
  let cellEls = [];
  let start = { r: 0, c: 0 };
  let end = { r: 0, c: 0 };
  let mouseDown = false;
  let dragMode = null; // 'wall' | 'erase' | 'start' | 'end'
  let running = false;
  let cancelToken = 0;

  const sleep = (ms) => new Promise((r) => setTimeout(r, ms));
  const stepDelay = () => Math.max(2, (101 - Number(speedEl.value)) * 0.6);

  function buildBoard() {
    cols = Number(colsEl.value);
    rows = Number(rowsEl.value);
    colsValEl.textContent = cols;
    rowsValEl.textContent = rows;

    grid = Array.from({ length: rows }, () => Array(cols).fill(EMPTY));
    start = { r: Math.floor(rows / 2), c: Math.max(1, Math.floor(cols / 6)) };
    end = { r: Math.floor(rows / 2), c: Math.min(cols - 2, cols - Math.floor(cols / 6)) };

    board.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;
    board.innerHTML = "";
    cellEls = [];
    for (let r = 0; r < rows; r++) {
      const row = [];
      for (let c = 0; c < cols; c++) {
        const el = document.createElement("div");
        el.className = "cell";
        el.dataset.r = r;
        el.dataset.c = c;
        attachCellEvents(el, r, c);
        board.appendChild(el);
        row.push(el);
      }
      cellEls.push(row);
    }
    paintAll();
  }

  function attachCellEvents(el, r, c) {
    el.addEventListener("mousedown", (e) => {
      if (running) return;
      e.preventDefault();
      mouseDown = true;
      if (r === start.r && c === start.c) {
        dragMode = "start";
      } else if (r === end.r && c === end.c) {
        dragMode = "end";
      } else if (grid[r][c] === WALL) {
        dragMode = "erase";
        grid[r][c] = EMPTY;
        paintCell(r, c);
      } else {
        dragMode = "wall";
        grid[r][c] = WALL;
        paintCell(r, c);
      }
    });
    el.addEventListener("mouseenter", () => {
      if (!mouseDown || running) return;
      if (dragMode === "wall" && !(r === start.r && c === start.c) && !(r === end.r && c === end.c)) {
        grid[r][c] = WALL;
        paintCell(r, c);
      } else if (dragMode === "erase" && !(r === start.r && c === start.c) && !(r === end.r && c === end.c)) {
        grid[r][c] = EMPTY;
        paintCell(r, c);
      } else if (dragMode === "start" && !(r === end.r && c === end.c) && grid[r][c] !== WALL) {
        const prev = start;
        start = { r, c };
        paintCell(prev.r, prev.c);
        paintCell(r, c);
      } else if (dragMode === "end" && !(r === start.r && c === start.c) && grid[r][c] !== WALL) {
        const prev = end;
        end = { r, c };
        paintCell(prev.r, prev.c);
        paintCell(r, c);
      }
    });
  }

  document.addEventListener("mouseup", () => { mouseDown = false; dragMode = null; });

  function paintCell(r, c) {
    const el = cellEls[r][c];
    el.className = "cell";
    if (r === start.r && c === start.c) el.classList.add("start");
    else if (r === end.r && c === end.c) el.classList.add("end");
    else if (grid[r][c] === WALL) el.classList.add("wall");
  }

  function paintAll() {
    for (let r = 0; r < rows; r++) for (let c = 0; c < cols; c++) paintCell(r, c);
  }

  function clearTraversal() {
    for (let r = 0; r < rows; r++) {
      for (let c = 0; c < cols; c++) {
        cellEls[r][c].classList.remove("visited", "frontier", "path");
      }
    }
    visitedEl.textContent = 0;
    pathEl.textContent = "—";
  }

  function neighbours(r, c) {
    const result = [];
    if (r > 0) result.push([r - 1, c]);
    if (r < rows - 1) result.push([r + 1, c]);
    if (c > 0) result.push([r, c - 1]);
    if (c < cols - 1) result.push([r, c + 1]);
    return result;
  }

  function reconstructPath(prev) {
    const path = [];
    let cur = `${end.r},${end.c}`;
    const startKey = `${start.r},${start.c}`;
    if (!prev.has(cur)) return path;
    while (cur && cur !== startKey) {
      const [r, c] = cur.split(",").map(Number);
      path.push([r, c]);
      cur = prev.get(cur);
    }
    path.reverse();
    return path;
  }

  async function animatePath(path, token) {
    for (const [r, c] of path) {
      if (token !== cancelToken) throw new Error("cancel");
      if ((r === end.r && c === end.c) || (r === start.r && c === start.c)) continue;
      cellEls[r][c].classList.remove("visited", "frontier");
      cellEls[r][c].classList.add("path");
      await sleep(stepDelay() * 1.5);
    }
  }

  async function bfs(token) {
    const queue = [[start.r, start.c]];
    const visited = new Set([`${start.r},${start.c}`]);
    const prev = new Map();
    let count = 0;
    while (queue.length) {
      if (token !== cancelToken) throw new Error("cancel");
      const [r, c] = queue.shift();
      if (!(r === start.r && c === start.c) && !(r === end.r && c === end.c)) {
        cellEls[r][c].classList.remove("frontier");
        cellEls[r][c].classList.add("visited");
      }
      count++;
      visitedEl.textContent = count;
      if (r === end.r && c === end.c) return prev;
      for (const [nr, nc] of neighbours(r, c)) {
        const k = `${nr},${nc}`;
        if (visited.has(k) || grid[nr][nc] === WALL) continue;
        visited.add(k);
        prev.set(k, `${r},${c}`);
        queue.push([nr, nc]);
        if (!(nr === end.r && nc === end.c)) cellEls[nr][nc].classList.add("frontier");
      }
      await sleep(stepDelay());
    }
    return prev;
  }

  async function dfs(token) {
    const stack = [[start.r, start.c]];
    const visited = new Set();
    const prev = new Map();
    let count = 0;
    while (stack.length) {
      if (token !== cancelToken) throw new Error("cancel");
      const [r, c] = stack.pop();
      const k = `${r},${c}`;
      if (visited.has(k)) continue;
      visited.add(k);
      if (!(r === start.r && c === start.c) && !(r === end.r && c === end.c)) {
        cellEls[r][c].classList.remove("frontier");
        cellEls[r][c].classList.add("visited");
      }
      count++;
      visitedEl.textContent = count;
      if (r === end.r && c === end.c) return prev;
      for (const [nr, nc] of neighbours(r, c)) {
        const nk = `${nr},${nc}`;
        if (visited.has(nk) || grid[nr][nc] === WALL) continue;
        if (!prev.has(nk)) prev.set(nk, k);
        stack.push([nr, nc]);
        if (!(nr === end.r && nc === end.c)) cellEls[nr][nc].classList.add("frontier");
      }
      await sleep(stepDelay());
    }
    return prev;
  }

  async function dijkstra(token) {
    // Min-heap keyed by distance. Edge weights are all 1 here.
    const dist = new Map();
    const prev = new Map();
    const startKey = `${start.r},${start.c}`;
    dist.set(startKey, 0);
    const heap = [[0, start.r, start.c]];
    const inHeap = new Set([startKey]);
    let count = 0;

    while (heap.length) {
      if (token !== cancelToken) throw new Error("cancel");
      // simple linear extraction — fine for our grid sizes
      let minIdx = 0;
      for (let i = 1; i < heap.length; i++) if (heap[i][0] < heap[minIdx][0]) minIdx = i;
      const [d, r, c] = heap.splice(minIdx, 1)[0];
      const k = `${r},${c}`;
      inHeap.delete(k);
      if (d > (dist.get(k) ?? Infinity)) continue;
      if (!(r === start.r && c === start.c) && !(r === end.r && c === end.c)) {
        cellEls[r][c].classList.remove("frontier");
        cellEls[r][c].classList.add("visited");
      }
      count++;
      visitedEl.textContent = count;
      if (r === end.r && c === end.c) return prev;
      for (const [nr, nc] of neighbours(r, c)) {
        if (grid[nr][nc] === WALL) continue;
        const nk = `${nr},${nc}`;
        const nd = d + 1;
        if (nd < (dist.get(nk) ?? Infinity)) {
          dist.set(nk, nd);
          prev.set(nk, k);
          heap.push([nd, nr, nc]);
          if (!(nr === end.r && nc === end.c)) cellEls[nr][nc].classList.add("frontier");
        }
      }
      await sleep(stepDelay());
    }
    return prev;
  }

  const RUNNERS = { bfs, dfs, dijkstra };

  async function run() {
    if (running) return;
    running = true;
    cancelToken++;
    const token = cancelToken;
    clearTraversal();
    statusEl.textContent = "running";
    runBtn.disabled = true;
    stopBtn.disabled = false;
    clearBtn.disabled = true;
    randomBtn.disabled = true;
    algoEl.disabled = true;

    try {
      const prev = await RUNNERS[algoEl.value](token);
      const path = reconstructPath(prev);
      if (path.length === 0) {
        statusEl.textContent = "no path";
        pathEl.textContent = "—";
      } else {
        await animatePath(path, token);
        statusEl.textContent = "done";
        pathEl.textContent = path.length;
      }
    } catch (_e) {
      statusEl.textContent = "stopped";
    } finally {
      running = false;
      runBtn.disabled = false;
      stopBtn.disabled = true;
      clearBtn.disabled = false;
      randomBtn.disabled = false;
      algoEl.disabled = false;
    }
  }

  function stop() { cancelToken++; }

  function clearWalls() {
    if (running) return;
    for (let r = 0; r < rows; r++) for (let c = 0; c < cols; c++) {
      if (grid[r][c] === WALL) grid[r][c] = EMPTY;
    }
    paintAll();
    clearTraversal();
    statusEl.textContent = "idle";
  }

  function randomMaze() {
    if (running) return;
    for (let r = 0; r < rows; r++) {
      for (let c = 0; c < cols; c++) {
        if (r === start.r && c === start.c) { grid[r][c] = EMPTY; continue; }
        if (r === end.r && c === end.c) { grid[r][c] = EMPTY; continue; }
        grid[r][c] = Math.random() < 0.28 ? WALL : EMPTY;
      }
    }
    paintAll();
    clearTraversal();
    statusEl.textContent = "idle";
  }

  function updateInfo() {
    const info = ALGO_INFO[algoEl.value];
    infoTitle.textContent = info.title;
    infoDesc.textContent = info.desc;
    infoComplexity.innerHTML = info.complexity.map((c) => `<span>${c}</span>`).join("");
  }

  colsEl.addEventListener("input", () => { if (!running) buildBoard(); });
  rowsEl.addEventListener("input", () => { if (!running) buildBoard(); });
  runBtn.addEventListener("click", run);
  stopBtn.addEventListener("click", stop);
  clearBtn.addEventListener("click", clearWalls);
  randomBtn.addEventListener("click", randomMaze);
  algoEl.addEventListener("change", updateInfo);
  board.addEventListener("dragstart", (e) => e.preventDefault());

  updateInfo();
  buildBoard();
})();
