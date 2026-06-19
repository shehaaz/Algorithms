# Cohere Local Chat Agent

A minimal local CLI for chatting with Cohere models.

## Setup

```bash
cd agents
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

Set your API key, either via the environment or a local `.env` file copied from
`.env.example`:

```bash
export COHERE_API_KEY=your-key-here
```

## Usage

```bash
python cohere_chat.py
```

Use `--model` or the `COHERE_MODEL` env var to try a different Cohere model (default:
`command-r-plus`). Type `exit` or `quit` to leave the REPL.
