# Mini Quartz — Pure Java Job Scheduling Framework

## 1. Description

Mini Quartz is a pure Java implementation of a job scheduling and execution framework, inspired by the core concepts of Quartz Scheduler.

The framework allows an application to:

- Define Java jobs
- Register jobs with a scheduler
- Define when jobs should execute through triggers
- Schedule and execute jobs
- Manage job execution using Java concurrency mechanisms
- Track the state of job executions
- Handle job execution failures

The primary purpose is to build a simplified scheduler from the ground up while gaining practical understanding of Core Java, OOP, collections, exceptions, concurrency, and scheduling.

The project is **not** a Quartz wrapper or a copy of Quartz APIs — it is an independent implementation using Java's standard library only.

---

## 2. Requirements & Scope

### Functional Requirements

**Job**

```java
public interface Job {
    void execute(JobExecutionContext context);
}
```

```java
public class BackupJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        // Job logic
    }
}
```

**Job Definition** — maintains information required to identify and manage a job; each job has a unique identity within the scheduler.

**Trigger** — represents when a job should execute. Initial scope:
- One-time execution
- Repeated execution at a fixed interval

**Scheduler** must:
- Start and stop
- Register jobs and triggers
- Determine when jobs are ready for execution
- Submit ready jobs for execution
- Prevent execution after shutdown

**Job Execution** — jobs execute asynchronously; multiple jobs can run concurrently.

**Execution State** — at minimum:

| State | Meaning |
|---|---|
| `SCHEDULED` | Waiting to run |
| `RUNNING` | Currently executing |
| `COMPLETED` | Finished successfully |
| `FAILED` | Threw an exception |

**Exception Handling** — if a job throws an exception: capture the failure, update state to `FAILED`, and do not terminate the scheduler.

### Technical Requirements

Allowed:
- `java.time`, `java.util`, `java.util.concurrent`, `java.io`, `java.lang.reflect`

Not in scope (V1):
- Spring, Hibernate, Database, Redis, Kafka, Quartz itself

### Core Java Concepts to Practice

Interfaces, abstract classes, encapsulation, inheritance, polymorphism, generics, collections, `equals()`/`hashCode()`, `Comparable`/`Comparator`, custom exceptions, lambdas, streams, `Optional`, Date/Time API, threads, `Runnable`/`Callable`, `ExecutorService`, `ScheduledExecutorService`, concurrent collections, synchronization, locks, atomic classes.

---

## 3. User Stories

| Story | Description |
|---|---|
| Define & Register | Define a Java job and register it with the scheduler for trigger-based execution |
| Scheduling | Associate a trigger with a job to control execution time/interval |
| Concurrent Execution | Multiple jobs execute concurrently — one long-running job does not block others |
| Failure Handling | Failed executions are captured and marked `FAILED`; scheduler keeps running |
| Lifecycle | Start and stop the scheduler to control when jobs are allowed to execute |

---

## 4. High-Level Architecture

```
Application
 │  Defines Job, Creates Trigger
 ▼
Scheduler
 │  Register Job / Trigger, Manage Lifecycle
 ▼
JobStore
 │  Jobs, Job Definitions, Triggers
 │
 │  Trigger fires
 ▼
Execution Queue
 ▼
Worker Pool  (Worker 1 | Worker 2 | Worker 3)
 ▼
Job.execute()
 │
 ├── SUCCESS → COMPLETED
 └── FAILURE → FAILED
```

### Core Relationships

```
Job          "What should execute?"
 ▼
Trigger      "When should it execute?"
 ▼
Scheduler    "Coordinate execution"
 ▼
Worker Pool  "Who executes it?"
```

---

## 5. Out of Scope (V1)

Distributed scheduling, cluster coordination, persistent DB-backed jobs, Web UI, REST APIs, authentication, external notifications, cron expressions, calendars/time-zone rules, job dependencies, distributed locking.
