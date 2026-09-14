# How to Add Notes to This Folder

## Step 1 — Name your topic

Decide on a topic name, e.g. `Collections`.

## Step 2 — Choose a folder

**Add to an existing folder** if the topic fits under one already here:

```
1_JavaBasics/
2_Java_Interfaces/
3_Annotations_Reflection/
4_Exceptions_Operators_ControlFlow/
Regex/
Threads/
```

**Create a new folder** if it's a standalone topic. Follow the naming convention:

- Look at the highest existing numbered prefix (currently `4_`).
- Increment by 1: next folder is `5_<TopicName>`.
- Use `PascalCase` for the topic part, e.g. `5_Collections`.

```
mkdir Java/5_Collections
```

## Step 3 — Create the notes file

Inside the folder, create a Markdown file named after the topic:

```
<TopicName>.md   →   e.g. Collections.md
```

Start the file with a heading and a Notes link using the link provided by the user. Copy the link as-is — do not access or summarise the content:

```markdown
# Collections

## Notes - [Click](https://drive.google.com/...)
```

## Example

```
Java/
└── 5_Collections/
    └── Collections.md
```

Add code samples alongside the notes file in the same folder.
