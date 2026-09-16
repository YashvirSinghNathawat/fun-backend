---
tags:
  - genai
  - context-engineering
  - llm
  - memory
  - agents
---

# Context Engineering

## The Problem — Why Prompt Engineering Broke Down

**Business scenario:** At Globomantics, designer Brian builds an AI copilot that assists fellow designers like Evelyn with brand-accurate creative suggestions. He starts with a simple prompt. It works sometimes, fails other times.

He iterates. Adds adjectives. Writes paragraphs with more instructions. Adds role, tone, length, structure, sources. The prompt grows into a wall of text — fragile, hard to maintain, and still inconsistent.

**The Pilot/Copilot model:** Think of the designer as the **Pilot** (in command, makes decisions) and the AI as the **Copilot** (assists, executes). The goal is *brand-accurate creative suggestions*. Prompt tweaks alone can't fix it — the Copilot is flying blind without the right context loaded.

**Why it still fails:** the copilot needed context it never received:
- Company's design language
- Tone guidelines
- Color rules
- Asset history

Without these, the model guesses. Prompt engineering was treating the symptom — the cure requires giving the model the *right information*, not just better instructions.

> Prompt engineering is a **dark art**: tweak the input, wave a magic wand, hope the output improves. It's unreliable and not repeatable at scale.

The prompt-complexity problem is non-linear. A simple task has a simple prompt. As requirements grow, complexity explodes exponentially — Simple → Convoluted → Very Hard — like navigating an increasingly dense maze.

This is the **evolution of prompting**:

| Stage | Approach | Result |
|---|---|---|
| 1 | Simple sentences | Very general responses |
| 2 | Sentences with more adjectives | Slightly better |
| 3 | Targeted paragraphs with instructions | Improved |
| 4 | Structured approaches (role, tone, length, format, sources) | Consistent — but brittle |

Stage 4 is what prompt engineering courses teach. But the real problem is deeper: **the LLM was guessing context it didn't have**.

**Prompt engineering treats the symptom. Context engineering treats the cause.**

---

## The Formal Definition

> **Context Engineering** — the art and science of designing systems that provide a language model with:
> - The **right information**
> - In the **right format**
> - At **exactly the right time**
>
> It's about filling the model's context window *delicately and precisely* to support its next action.

### Key Terms

- **Context** — all information provided to the model for a specific task
- **Context Window** — the model's maximum token capacity for one request
- **Prompt** — the *content* you write · **Context Window** — the *space* that holds it

---

## Prompt Engineering vs Context Engineering

> Prompt engineering ⊂ Context engineering

| | Prompt Engineering | Context Engineering |
|---|---|---|
| **Focus** | The instruction / prompt | The entire context window |
| **Question** | "What should I tell the model?" | "What should the model know at this moment?" |
| **Nature** | Mostly static | Dynamic — changes as task progresses |
| **Best for** | Simple tasks | Complex / agentic systems |

**Simple analogy:**
- Prompt engineering = writing good instructions
- Context engineering = right instructions + right information + right tools + right state, *at the right time*

---

## What Can Be Part of Context?

Three buckets of context:

**Instructions** — system prompt, task prompt, rules

**User Data** — chat history, user profile, task state

**External Info** — documents, database results, API / tool results

The agent assembles all three into the input that reaches the LLM. Sources can be: vector DB (embeddings), relational DB, APIs, filesystems, and more.

---

## The Key Decisions

**What to include?** Only what the model needs for *this specific task*. Relevance over volume.

**What to exclude?**
- Irrelevant noise degrades performance
- Stale or contradictory info misleads
- Too much context → *lost in the middle* problem (model loses focus on the middle of a long input)

**How to structure it?**
- Put critical info at the **start or end** (primacy / recency bias)
- Use clear delimiters: `---`, XML tags, markdown headers
- Explicit roles: `system` / `user` / `assistant`

**What data formats work?** JSON, XML, CSV — structured formats help the model parse and use information reliably.

---

## System-Level Tradeoffs

Every context decision has costs:

| Tradeoff | More context | Less context |
|---|---|---|
| **Latency** | Slower (more tokens to process) | Faster |
| **Cost** | Higher (tokens = money) | Lower |
| **Signal-to-noise** | Risk of distraction | Cleaner focus |

This is why context engineering is a *design discipline* — not just "dump everything in."

---

## Levels of AI Systems

The more capable the system, the more it depends on well-engineered context:

| Level | System | Context Used | Capability |
|---|---|---|---|
| **1** | Chatbot | Customer status, product docs | Rigid if/then rules |
| **2** | Assistant | Calendars, emails, history | Understands intent, personalises |
| **3** | AI Agent | Tools, memory, task state, plans | Makes decisions, runs workflows autonomously |

---

## Core Techniques

| Technique | What it does |
|---|---|
| **RAG** | Retrieve relevant chunks at query time, inject into context |
| **Memory** | Short-term (in-context history) or long-term (vector DB / key-value store) |
| **Summarization** | Compress long histories to reduce token usage |
| **Few-Shot Examples** | Input → Output pairs, task-relevant only |
| **Tool Integration** | Inject tool call results (search, code exec, APIs) |

**Context management patterns:**
- **Writing** — crafting the right instructions/prompts
- **Selecting** — choosing what information to include
- **Compressing** — summarizing to save space
- **Isolating** — giving each subagent only the slice of context it needs (not the full shared context)

**Calling subagents** is a distinct but related pattern: the orchestrating agent delegates work to specialised subagents, each receiving only the context relevant to their subtask. This avoids token bloat and prevents irrelevant information from degrading performance across the pipeline.

**Agent to Multiple Specialized LLMs:** An agent can package a tailored **Context Payload** and route it to different specialized LLMs in parallel (e.g., a Campaigns LLM, a Research LLM). Each LLM receives only the slice of context relevant to its specialty — this is context isolation in practice. The agent acts as the orchestrator deciding what each model needs to know.

---

## Memory in Agents

> **LLMs don't have memory — agents do.**
> An LLM's context is temporary (one inference call). Memory must be *stored outside the model* and retrieved when useful.

### Types of Memory

| Memory | Stores | Example |
|---|---|---|
| **Episodic** | Past experiences / interactions | What the user tried yesterday |
| **Semantic** | Facts and knowledge | Manuals, tool descriptions, FAQs |
| **Procedural** | Rules, workflows, procedures | Troubleshooting sequence, escalation policy |

### How It Maps to Agent Architecture

```
Human                              Agent

Long-Term Memory                   Persistent / Long-Term Memory
├── Episodic  (experiences)        ├── Episodic logs
├── Semantic  (facts/concepts)     ├── Semantic knowledge base / RAG
└── Procedural (skills/routines)   └── Procedural rules / workflows
        ↓                                      ↓
  Short-Term Memory                         Context
  (Working Memory)                             ↓
                                      LLM Context Window
```

### How Memory Reaches the LLM

Long-term memory is **not** automatically in context. The agent pipeline manages retrieval:

```
Store  →  Retrieve relevant memory  →  Add to context  →  Call LLM
```

*Example:* User previously got stuck on color-level adjustment → retrieve that episodic memory → include it in the current context.

---

## Takeaway

**Context engineering = Input Control** — deciding precisely what goes into the model's context window, when, and in what format. It's the core discipline behind reliable AI systems.

As systems grow from simple chatbots to autonomous agents, good context engineering is what separates reliable results from unpredictable ones.

**Where it applies:**
- **QA workflows** — inject relevant test cases and bug history
- **Copilot workflows** — inject brand guidelines, asset history, user preferences
- **Summarization pipelines** — compress and select what the model sees

> Fewer practitioners master it as complexity grows — which makes it a high-value skill.

---

## Related Notes

- [[prompt_engineering]]
- [[1_Langchain/notes]]
- [[2_LangGraph/notes]]
