# Claude Code
![alt text](image.png)

---

## Table of Contents

### 1. AI & Coding Philosophy
- [AI-Assisted Coding: Augmentation, Not Replacement](#ai-assisted-coding-augmentation-not-replacement)
- [Risks](#risks)
- [Effective Human in the Loop](#effective-human-in-the-loop)
- [Problem with Vibe Coding](#problem-with-vibe-coding)

### 2. Getting Started
- [Project Lifecycle](#project-lifecycle)
- [Slash Commands](#slash-commands)
- [Context Window Management](#context-window-management)
- [CLAUDE.md](#claudemd)
- [.claude Folder](#claude-folder)
- [Good Practices](#good-practices)
- [Large Repositories](#large-repositories)

### 3. Memory & Context
- [Auto Memory](#auto-memory)

### 4. Workflows & Planning
- [Spec Driven Development](#spec-driven-development)
- [Enterprise Workflow](#enterprise-workflow)
- [Plan Mode & Extended Thinking](#plan-mode--extended-thinking)

### 5. Extensibility
- [Custom Slash Commands](#custom-slash-commands)
- [Skills](#skills)
- [Subagents](#subagents)
- [Git Worktrees](#git-worktrees)
- [Playwright MCP](#playwright-mcp)

### 6. Cost Efficiency
- [Output Hygiene](#output-hygiene--5-tax)
- [Fewer Turns](#fewer-turns--stop-re-sending-context)

---

# 1. AI & Coding Philosophy

## AI-Assisted Coding: Augmentation, Not Replacement

AI writes code. Humans own the thinking.

Humans must own:
- **Business requirements** — what to build and why
- **Architecture** — structure, boundaries, tradeoffs
- **Judgment** — when a correct solution is still the wrong one

AI amplifies good engineering. It does not replace it.

> Engineers who understand the problem get 10× from AI. Those who don't just ship bugs faster.

## Risks

- **Hallucinations** — AI confidently generates wrong code. Always verify output.
- **Technical debt** — AI favors quick, working solutions over clean ones. It duplicates code instead of reusing, skips abstractions, and ignores existing patterns. Each shortcut is small; accumulated across a codebase, they create systems that are hard to change and expensive to maintain.
- **Security vulnerabilities** — hardcoded credentials, insecure auth, outdated dependencies with known CVEs.

> Never merge AI code you haven't read.

## Effective Human in the Loop

- **Understand the code** — Read what AI generates. If you can't explain it, don't ship it.
- **Skills to spot problems** — Engineering fundamentals matter more, not less. You need to catch what AI misses.
- **Test everything** — AI-generated tests often test the wrong thing. Write tests that verify intent, not just output.
- **Maintain context** — AI has no memory of past decisions. You carry the architecture, constraints, and history.
- **Stay accountable** — The code is yours. AI is a tool; you own the consequences.

## Problem with Vibe Coding

Accepting AI output without understanding it.

- Hidden bugs and edge cases
- Inconsistent architecture
- Nobody can maintain the result

---

# 2. Getting Started

## Project Lifecycle

Claude Code assists at every stage:

```
┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐
│   Discover  │→ │   Design    │→ │    Build    │→ │   Deploy    │→ │ Support & Scale │
├─────────────┤  ├─────────────┤  ├─────────────┤  ├─────────────┤  ├─────────────────┤
│ Explore     │  │ Plan        │  │ Implement   │  │ Automate    │  │ Debug errors    │
│ codebase    │  │ project     │  │ code        │  │ CI/CD       │  │                 │
│             │  │             │  │             │  │             │  │ Monitor usage   │
│ Search docs │  │ Define      │  │ Write &     │  │ Configure   │  │ & performance   │
│             │  │ architecture│  │ run tests   │  │ environments│  │                 │
│ Onboard &   │  │             │  │             │  │             │  │                 │
│ setup       │  │ Tech specs  │  │ Commits,    │  │ Manage      │  │                 │
│             │  │             │  │ PRs, refactor│  │ deployments │  │                 │
└─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────────┘
```

## Slash Commands
![alt text](image-1.png)

### `/ide`
Connects Claude Code to your IDE (VS Code, JetBrains). Enables Claude to see your open file, cursor position, and diagnostics — so it has the same context you do without needing to paste code.

### Understanding New Code
1. What does this project do?
2. Which tech stack does this project use?
3. Explain the project structure to me.
4. Write clear prompt and use `@{filepath}`

## Context Window Management
![alt text](image-3.png)

## CLAUDE.md

LLM-based tools do not remember previous sessions. `CLAUDE.md` acts as persistent project context.

### Why?
- Avoids repeating instructions every session
- Ensures consistent code generation

### Creating CLAUDE.md

Two ways:
1. Manually
2. Using `/init` — scans config files, reads folder structure, infers tech stack. ~30% useful directly; rest should be customized.

### What to Include

| Section | Purpose |
|---|---|
| **Project Context** | Short description of the project |
| **Architecture** | Where routes, services, schemas, repositories live |
| **Code Style** | Type hints, function size, existing patterns |
| **Commands** | Run, test, install commands |
| **Preferred Libraries** | Allowed frameworks; no new deps without reason |
| **Critical Rules** | Hard constraints — files not to touch, ID policies |

### Good Practices
- Start with `/init`, then prune
- Keep under ~200 lines
- Only add rules that prevent mistakes
- Commit to git; treat as a living document
- Audit periodically

> If removing a rule changes nothing, delete it.

### Large Repositories

Split rules (loaded lazily) into `.claude/rules/`:
- `code-style.md`
- `testing.md`
- `security.md`

Reference docs using:
```md
See @docs/api-guidelines.md
```

## `.claude` Folder

Stores all Claude configuration:

| File/Path | Purpose |
|---|---|
| `CLAUDE.md` | Shared project config |
| `CLAUDE.local.md` | Personal, gitignored config |
| `~/.claude/CLAUDE.md` | Global preferences |
| Subdirectory `CLAUDE.md` | Folder-specific rules |
| `.claude/commands/` | Custom slash commands |
| `.claude/agents/` | Custom subagents |
| `.claude/rules/` | Lazily loaded rule files |
| `.claude/skills/` | Project-scoped skills |
| `.claude/plans/` | Implementation plans |

---

# 3. Memory & Context

## Auto Memory

Claude stores persistent project learnings across sessions. Only the first 200 lines of `memory.md` are loaded — keep it concise.

Location:
```bash
~/.claude/projects/<project>/memory/
```

Useful command:
```bash
/memory
```

### `#` — Quick Memory Shortcut

Type `#` followed by a note to instantly add context to memory. Useful when Claude repeats an error or needs project-specific knowledge.

```
#use uv to run python files or add any dependencies
```

```
#The vector database has two collections:
- course_catalog: course titles, instructor, course_link, lesson_count, lessons_json
- course_content: text chunks for semantic search with course_title, lesson_number, chunk_index
```

---

# 4. Workflows & Planning

## Spec Driven Development
![image-4.jpg](image-4.jpg)
![image-5.jpg](image-5.jpg)

## Enterprise Workflow

```
Epic Design
    ↓
Task Extraction
    ↓
Task Implementation Plan
    ↓
Code
    ↓
Validation
```

## Plan Mode & Extended Thinking

### Plan Mode
- Create a design document before implementation
- Store plans in `.claude/plans/`
- Opus = Planning, Sonnet = Implementation
- Enable Extended Thinking during planning — Effort: Medium or High

### Extended Thinking Mode

Trigger deeper reasoning with keywords:

```
think  <  think hard  <  think harder  <  ultrathink
```

Each level allocates more thinking budget to Claude.

**When to use:**
- Complex architectural changes
- Debugging complicated issues
- Tradeoff analysis across multiple options

---

# 5. Extensibility

## Custom Slash Commands

Reusable prompts stored as Markdown files, invoked via `/command`.

| Scope | Location |
|---|---|
| Project | `.claude/commands/` |
| Personal | `~/.claude/commands/` |

Examples: `/review`, `/commit`, `/test`, `/security-scan`

## Skills

**Purpose:** Convert Claude from a generalist into a specialist.

**Problem with prompts:** Retype every time, burns context window, hard to share/version/improve, prompts don't compose.

**Solution:** Skills are reusable, file-based resources providing domain-specific expertise — loaded on demand.

### Progressive Disclosure
Don't load information until the moment it's needed.

- **L1:** Description (trigger)
- **L2:** `SKILL.md` (instructions)
- **L3:** Resources (examples, templates)

![alt text](image-2.png)

### Scope
- `~/.claude/skills` → Personal
- `.claude/skills` → Project

### Creation
**Methods:** Manual | `skill-creator`

**Flow:** Need → `SKILL.md` → Resources → Test → Refine

Read: https://medium.com/@universe3523/spec-driven-development-with-claude-code-206bf56955d0

## Subagents

**Why:** LLMs are stateless, context windows overflow, and the "lost in the middle" effect makes long contexts unreliable.

**What:** Specialized AI assistants running in isolated contexts — perform focused tasks, return only relevant results.

### Advantages
- Context isolation
- Specialization (Research, Coding, Review)
- Parallelism
- Modularity: Analyze → Plan → Implement → Review → Test

### Built-in Subagents

| Agent | Purpose |
|---|---|
| **Explore** | Understand the codebase |
| **Plan** | Create implementation plans |
| **General Purpose** | Read and write code |

### Custom Subagents

Configure with: Tools, Prompt, Model, Permissions, Hooks, Skills

**Storage:** `.claude/agents/` (project) or `~/.claude/agents/` (personal)

**Triggering:** Automatic or Explicit

**Example workflows:**
- `/test-feature` → Test Writer → Test Runner
- `/self-code-review` → Security Review → Code Quality Review

## Git Worktrees

Check out multiple branches simultaneously in separate directories — all sharing the same Git history.

```
my-project/
├── main/       → main branch
├── feature-a/  → feature-a branch
└── feature-b/  → feature-b branch
```

**Why:** Run multiple AI agents on separate features in parallel — no branch switching, no file conflicts.

### Commands

```bash
# Add worktrees (branches must exist)
git worktree add ../feature-a feature-a

# Create branch + worktree together
git worktree add -b feature-a ../feature-a main
```

**Example — two agents in parallel:**
```
java-project/
├── main/          → main
├── feature-user/  → Agent 1: User API
└── feature-order/ → Agent 2: Order API
```

### Merge & Cleanup

```bash
cd main
git merge feature/user-api
git merge feature/order-api

git worktree remove ../feature-user
git worktree remove ../feature-order
```

> Worktrees = parallel isolated workspaces. Merging is still normal Git.

## Playwright MCP

An MCP server that lets Claude Code control and inspect a real browser.

| Use Case | Description |
|---|---|
| **UI Testing** | Execute web application workflows |
| **DOM Inspection** | Verify elements, attributes, and page state |
| **Failure Debugging** | Investigate failed UI tests and missing selectors |
| **E2E Validation** | Verify complete user journeys |
| **Regression Testing** | Check existing functionality after code changes |
| **UI Verification** | Validate forms, buttons, navigation, and messages |

---

# 6. Cost Efficiency

## Output Hygiene — 5× Tax

Output tokens cost ~5× more than input tokens.

| Avoid | Do instead |
|---|---|
| Reprinting entire files | Edit files in place |
| Echoing long logs/diffs | Ask for concise diffs |
| Verbose step-by-step narration | Summarize changes briefly |
| Regenerating boilerplate | Let hooks/formatters handle it |

**In CLAUDE.md:** Be concise. Edit in place; don't reprint files. Prefer diffs/summaries. Don't echo logs or narrate routine steps.

## Fewer Turns — Stop Re-sending Context

Every turn resends the full context. More turns = more cost.

**Cut turn count:**
- Plan first, then execute in one turn
- Give complete instructions upfront
- Use `/clear` for stale context
- Press `Esc` when Claude goes down the wrong path

| Expensive | Cheap |
|---|---|
| Add field → validate → migrate → test (4 turns) | Plan all steps → approve → execute (1 turn) |

> Think before it types. Fewer, better turns = lower cost.
