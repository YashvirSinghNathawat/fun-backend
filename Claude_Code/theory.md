# Claude Code
![alt text](image.png)

## Problem with Vibe Coding
- Hidden bugs and edge cases
- Inconsistent architecture

## Claude Code Slash Commands
![alt text](image-1.png)


### Understanding new code

1. What does this project do?
2. Which tech stack does this project use?
3. Explain the project structure to me.
4. Write clear prompt and use @{filepath}

## Context Window Management
![alt text](image-3.png)


## Why `CLAUDE.md`?

LLM-based tools like Claude do not remember previous sessions.  
Repeating instructions every session is:
- Time consuming
- Error-prone
- Leads to inconsistent code generation

`CLAUDE.md` acts as persistent project context for Claude.

It typically contains:
- Project structure
- Coding conventions
- Run/build/test commands
- Preferred tools/frameworks

---

# Creating `CLAUDE.md`

Two ways:
1. Manually
2. Using:

```bash
/init
```

---

# Why `/init` Helps

Useful when:
- Working on an existing codebase
- Large repositories
- New to `CLAUDE.md`
- Building quick prototypes

`/init`:
- Scans config files (`package.json`, `README.md`, etc.)
- Reads folder structure
- Infers tech stack and conventions

Usually:
- ~30% useful directly
- Remaining should be customized manually

---

# What `CLAUDE.md` Should Have

## Project Context
Short description of the project.

## Architecture
Where routes, services, schemas, repositories live.

Example:
- Routes live in `routers/` — define endpoints, handle HTTP in/out
- Business logic lives in `services/` — core app logic, orchestration
- Schemas live in `schemas/` — request/response validation and serialization
- Persistence logic lives in `repository/` — all database queries

## Code Style

Example:
- Use type hints
- Keep functions small
- Prefer existing patterns

## Commands
Lists exact commands for running, testing, and
maintaining the project
- Install dependencies: `pip install -r requirements.txt`
- Run dev server: `uvicorn main:app --reload`
- Run tests: `pytest`

## Preferred Libraries

Specify allowed frameworks/tools like
- Use FastAPI for APIs
- Use Pydantic for validation
- Use SQLAlchemy for ORM
- Do not introduce new dependencies unless necessary

## Critical Rules

Important warnings and constraints like 
- Do not modify "database.py' unless absolutely
necessary.
- Patient IDs are provided by the client; do not
auto-generate UUIDs.

---

# `.claude` Folder

Stores Claude configuration:
- Skills
- Custom commands
- Sub-agents
- Project workflows

Types:
- `CLAUDE.md` → shared project config
- `CLAUDE.local.md` → personal gitignored config
- `~/.claude/CLAUDE.md` → global preferences
- Subdirectory `CLAUDE.md` → folder-specific rules

---

# Good Practices

- Start with `/init`, then prune
- Keep under ~200 lines
- Only add rules that prevent mistakes
- Commit to git
- Use emphasis IMPORTANT only if needed
- Treat it as a living document
- Only put universal things applicable to full project.
- Audit periodically

Rule:
> If removing a rule changes nothing, delete it.

---

# Large Repositories

Split rules(Loaded Lazily) into:

```bash
.claude/rules/
```

Examples:
- `code-style.md`
- `testing.md`
- `security.md`

Reference docs using:

```md
See @docs/api-guidelines.md
```

---

# Auto Memory

Claude can store persistent project learnings. Only first 200 likes of memory.md is loaded so maintain properly.

Example:
> “Project uses INR instead of USD”

Location:

```bash
~/.claude/projects/<project>/memory/
```

Useful command:

```bash
/memory
```

# Spec Drive Development
![image-4.jpg](image-4.jpg)
![image-5.jpg](image-5.jpg)

# Best Practices: Plan Mode

- Create a Design Document before implementation.
- Use Plan Mode and store in `.claude/plans/`.
- Opus = Planning , Sonnet = Implementation.
- Enable Extended Thinking during planning.
- Effort - Medium or High
