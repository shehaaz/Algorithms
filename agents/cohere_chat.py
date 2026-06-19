#!/usr/bin/env python3
import argparse
import os
import sys

import cohere


def build_client() -> cohere.ClientV2:
    api_key = os.environ.get("COHERE_API_KEY")
    if not api_key:
        sys.exit("Error: COHERE_API_KEY environment variable is not set.")
    return cohere.ClientV2(api_key=api_key)


def get_response(client: cohere.ClientV2, model: str, message: str, history=None) -> str:
    history = history if history is not None else []
    history.append({"role": "user", "content": message})
    response = client.chat(model=model, messages=history)
    reply = response.message.content[0].text
    history.append({"role": "assistant", "content": reply})
    return reply


def main() -> None:
    parser = argparse.ArgumentParser(description="Local chat REPL backed by Cohere.")
    parser.add_argument(
        "--model",
        default=os.environ.get("COHERE_MODEL", "command-r-plus"),
        help="Cohere model to use (default: %(default)s)",
    )
    args = parser.parse_args()

    client = build_client()
    history = []

    print(f"Cohere local agent (model: {args.model}). Type 'exit' or 'quit' to leave.")
    while True:
        try:
            user_input = input("you> ").strip()
        except (EOFError, KeyboardInterrupt):
            print()
            break

        if not user_input:
            continue
        if user_input.lower() in ("exit", "quit"):
            break

        reply = get_response(client, args.model, user_input, history)
        print(f"agent> {reply}")


if __name__ == "__main__":
    main()
