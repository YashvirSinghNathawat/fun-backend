## 1. Chain in LangChain

A **Chain** in LangChain connects multiple components (like prompt, model, parser)  
so the output of one step becomes the input of the next step.

It helps automate multi-step workflows.

Example:
User Input → Prompt → LLM → Output


---

## 2. Without Chain and With Chain

### Without Chain
- You manually call each step.
- You pass input and output yourself.
- More code, less automation.

Example:
Input → Prompt → Model → Print Output (handled manually)


### With Chain
- Steps are connected automatically.
- Output of one step goes to next step.
- Cleaner and reusable code.

Example:
Input → Chain → Final Output


---

## 3. Types of Chain

### 1. Sequential Chain
- Steps run one after another.
- Output of Step 1 → Input of Step 2 → Step 3

Example:
Generate text → Summarize → Translate


### 2. Parallel Chain
- Multiple steps run at the same time.
- Same input goes to different chains.
- Outputs are combined at the end.

Example:
Input → (Summarize + Sentiment Analysis) → Combined Output


### 3. Conditional Chain
- Flow depends on a condition.
- Different chains run based on logic.

Example:
If sentiment is positive → Generate thank you reply  
If sentiment is negative → Generate apology reply