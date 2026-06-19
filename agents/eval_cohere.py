#!/usr/bin/env python3
import os
import sys

from langchain_cohere import ChatCohere
from openevals.llm import create_llm_as_judge
from openevals.prompts import CORRECTNESS_PROMPT

from cohere_chat import build_client, get_response

EVAL_CASES = [
    {
        "inputs": "What is the time complexity of binary search on a sorted array of n elements?",
        "reference_outputs": "O(log n)",
    },
    {
        "inputs": "Which traversal order does breadth-first search (BFS) use to visit nodes in a graph?",
        "reference_outputs": (
            "BFS visits nodes level by level, exploring all neighbors at the current depth "
            "before moving to nodes at the next depth, typically using a queue."
        ),
    },
    {
        "inputs": "What is the worst-case time complexity of quicksort?",
        "reference_outputs": "O(n^2)",
    },
]

PASS_THRESHOLD = 0.5


def main() -> None:
    api_key = os.environ.get("COHERE_API_KEY")
    if not api_key:
        sys.exit("Error: COHERE_API_KEY environment variable is not set.")
    model = os.environ.get("COHERE_MODEL", "command-r-plus")

    client = build_client()
    judge = ChatCohere(cohere_api_key=api_key, model=model)
    correctness_evaluator = create_llm_as_judge(
        prompt=CORRECTNESS_PROMPT,
        feedback_key="correctness",
        judge=judge,
    )

    all_passed = True
    for case in EVAL_CASES:
        outputs = get_response(client, model, case["inputs"])
        result = correctness_evaluator(
            inputs=case["inputs"],
            outputs=outputs,
            reference_outputs=case["reference_outputs"],
        )
        score = result["score"]
        passed = bool(score) if isinstance(score, bool) else score >= PASS_THRESHOLD
        all_passed = all_passed and passed

        print(f"Q: {case['inputs']}")
        print(f"A: {outputs}")
        print(f"score={score} comment={result.get('comment')}")
        print(f"{'PASS' if passed else 'FAIL'}\n")

    if not all_passed:
        sys.exit("One or more eval cases failed.")
    print("All eval cases passed.")


if __name__ == "__main__":
    main()
