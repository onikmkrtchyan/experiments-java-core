# AGENTS.md: Guide for AI Coding Assistants

## Project Overview
**ExperimentsCoreJAVA** is a learning-focused Java project containing examples and experiments with core Java concepts, design patterns, multithreading, and functional programming. It's structured as a collection of standalone educational modules rather than a production system.

**Key Insight:** Each subdirectory and file is typically a self-contained example. Modifications should preserve this independence while maintaining educational clarity.

---

## Architecture & Major Components

### 1. **Design Patterns** (`src/DesignPatterns/`)
Demonstrates Gang of Four and other patterns with practical implementations:
- **Builder Pattern** (`BuilderPatternExample.java`): Fluent API approach using private constructors and chainable setters
- **Factory Pattern** (`factory/FactoryPattern.java`): String-based factory returning interface types (Shape, etc.)
- **Strategy Pattern** (`strategy/StrategyPattern.java`): Algorithm abstraction using interfaces
- **Prototype Pattern** (`PrototypePatternExample.java`): Object cloning and deep copy examples

**Pattern:** Each pattern is isolated in its own file/package. Don't mix examples.

### 2. **Multithreading & Concurrency** (`src/threads/`)
The largest component (~30 files) covering:
- **Basic Threading:** `ThreadVsRunnable.java`, `ThreadNew.java`, `MyThread.java`
- **Synchronization Issues:** `RaceCondition.java`, `Visibility.java`, `DeadlockExample.java`
- **Lock Mechanisms:** `ReentrantLockExample.java`, `ReentrantReadWriteLockExample.java`, `DifferentLocks.java`
- **Concurrency Utilities:** `CountDownLatchExample.java`, `SemaphoreExample.java`, `AtomicIntegerExample.java`
- **Executor/Pool:** `MainPool.java`, `ForkJoinExample.java`, `CustomForkJoinPoolExample.java`
- **Memory Leaks:** `MemoryLeakStaticField.java`, `MemoryLeakNotOverriding.java`

**Key Pattern:** Examples often intentionally demonstrate *problematic* code (prefixed `Incorrect` or `Example` showing what NOT to do). Read comments to understand intent.

### 3. **Functional Programming** (`src/streams/`)
- **Streams API:** `Streams.java` - filter, map, flatMap chains
- **FlatMap Examples:** `FlatMapExample.java` - nested stream flattening
- **Parallel Streams:** `ParallelStreamExample.java` - parallel processing with streams

**Convention:** Uses `Stream::of`, method references (`System.out::println`), and chained operations. Prefer functional approach in new examples.

### 4. **OOP Fundamentals**
- **Interfaces** (`src/Interface/`): Multiple implementations of same interface, default methods
- **Abstract Classes** (`src/AbstractClass/`): Inheritance hierarchy
- **Polymorphism & Covariance** (`src/Main.java`): Shows contravariance issues with commented examples
- **Static vs Instance** (`src/statics/`, `StaticAndNotStaticDiff.java`): Static field behavior across instances
- **String Handling:** `StringPool.java`, `StringVsStringBuilder.java` - memory/performance implications

### 5. **Data Structures & Utilities**
- **Custom Map Implementation** (`MySimpleMap.java`): Hash-based with collision handling (linked list)
- **Hashing & Security** (`TestHashing.java`): PBKDF2 password hashing with salt
- **Collections:** Standard Java collections used throughout

---

## Critical Developer Workflows

### Build & Run
```bash
# Compile (IntelliJ IML project)
javac src/**/*.java -d out/

# Run specific class (must have main method)
java -cp out/ ClassName
java -cp out/ package.ClassName

# Run from IDE: Right-click file → Run
```

### Testing Patterns
- **No formal test framework** - examples use `System.out.println()` for verification
- **Comments show expected output:** Read code comments to understand what's correct
- **Intentional failures documented:** Files like `RaceCondition.java` are meant to show problems

**Approach for new examples:** Include System.out output that demonstrates correctness, add comments explaining expected vs. problematic behavior.

### Project Configuration
- **IDE:** IntelliJ IDEA (`.idea/` folder, `ExperimentsCoreJAVA.iml`)
- **No external dependencies** - pure JDK only
- **Java version:** Inferred as Java 8+ (uses lambdas, streams, default methods)
- **Compilation:** Auto-handled by IDE or `javac` to `out/` directory

---

## Project-Specific Conventions

### 1. **File Organization**
- **Root `src/`:** One-off experiments and core Java concepts
- **Subdirectories:** Grouped by topic/pattern (threads/, DesignPatterns/, etc.)
- **Package naming:** Matches directory structure (e.g., `threads/Main.java` → `package threads;`)
- **No package in root src/:** Files like `Main.java`, `MySimpleMap.java` have no package declaration

**Action:** When adding examples, place in existing package directory or create new if starting a new topic. Include package declaration in subdirectory files.

### 2. **Naming Conventions**
- **Main entry points:** `Main.java` in each package (multiple Main classes exist)
- **Example files:** `*Example.java` or `*ExperimentalCode.java` (e.g., `BuilderPatternExample.java`)
- **Problem demonstrations:** `Problem*`, `Incorrect*`, `FailFast*` prefixes indicate anti-patterns
- **Concept pairs:** `Before*`/`After*` or comparison names (e.g., `ThreadVsRunnable.java`)

### 3. **Code Style Patterns**
- **Inner classes:** Used for pattern examples (e.g., `House` inside `BuilderPatternExample.java`)
- **Static factories:** `public static T create()` or `public static T builder()` for fluent APIs
- **Functional interfaces:** Implemented inline with lambdas in stream examples
- **Comments above sections:** Explain "why" and "what this demonstrates"
- **Commented-out code:** Often shows alternative implementations or problematic variations to explore

**Guideline:** Preserve commented examples - they're teaching tools showing what was considered and rejected.

### 4. **Threading-Specific Patterns**
- **Runnable over Thread:** Prefer `implements Runnable` with separate `Thread` object (shown in `ThreadVsRunnable.java`)
- **Lock usage:** Use `ReentrantLock` for modern code; synchronized blocks for fundamentals
- **Volatile keyword:** Demonstrated in `Visibility.java` for memory ordering
- **Executor framework:** Favor over manual thread creation (see `MainPool.java`)
- **Wait/Notify patterns:** Shown in `StartupWaitAndNotify.java` for coordination

---

## Integration Points & Dependencies

### 1. **Java Standard Library Dependencies**
- **Concurrency:** `java.util.concurrent.*` (locks, executors, atomic types)
- **Streams:** `java.util.stream.*` + `java.util.Collectors`
- **Date/Time:** `java.time.*` (LocalDate, LocalDateTime - seen in `DatesAndTimes/`)
- **Security:** `javax.crypto.*`, `java.security.*` for hashing
- **Collections:** Standard `java.util.*` (List, Map, Stream)

### 2. **Cross-Module Communication**
- **No inter-file dependencies:** Each example is standalone
- **Shared patterns:** Similar concepts repeated across files (e.g., multiple `Main.java` files serve as independent entry points)
- **Library-like utilities:** `ThreadUtils.java` could be referenced, but generally avoided

**Best Practice:** Keep examples self-contained. If creating reusable utility, place in root `src/` directory (like `MySimpleMap.java`, `ThreadUtils.java`).

### 3. **Entry Points**
Multiple `Main.java` files exist - understand which one you're analyzing:
- `src/Main.java` - Covariance/contravariance polymorphism example
- `src/DesignPatterns/Main.java` - Design pattern runner
- `src/Interface/Main.java` - Interface implementation example
- `src/statics/Main.java` - Static behavior example
- `src/threads/Main.java` - Basic thread runnable example

**Action:** Always use full class name or check package when referencing Main classes.

---

## Common Tasks & Patterns

### Adding a New Core Concept Example
1. Create file in appropriate subdirectory or root `src/`
2. If in subdirectory, add `package subdirectory;` declaration
3. Include educational comments explaining the concept
4. Add `public static void main(String[] args)` for runnable code
5. Use `System.out.println()` to show results
6. Reference related files in comments (e.g., "See also: ThreadVsRunnable.java")

### Adding a New Design Pattern
1. Create in `src/DesignPatterns/` subdirectory or new pattern subfolder
2. Include interfaces/abstract base, concrete implementations, and usage in `main()`
3. Show both correct usage and potential pitfalls in comments
4. Keep pattern isolated - don't cross-reference other patterns

### Extending Threading Examples
1. Place in `src/threads/`
2. Follow naming convention (ProblemName.java, ProblemNameFixed.java, or ProblemNameExample.java)
3. Use standard locks (ReentrantLock, ReentrantReadWriteLock, Semaphore)
4. Include Javadoc or comments explaining concurrency behavior
5. Show both broken and fixed versions when demonstrating problems

### Modifying Existing Code
- **Educational intent first:** Preserve comments explaining *why* code is written a certain way
- **Maintain isolation:** Don't create new dependencies between examples
- **Add, don't replace:** If showing an improvement, keep old code commented if it illustrates an anti-pattern
- **Test in IDE:** Use IntelliJ's Run feature to verify correctness

---

## Key Files to Reference

| File | Purpose | Key Pattern |
|------|---------|-------------|
| `src/MySimpleMap.java` | Custom hash map implementation | Generic types, collision handling with linked lists |
| `src/DesignPatterns/factory/FactoryPattern.java` | Factory pattern | String-based factory returning interface types |
| `src/threads/ReentrantLockExample.java` | Modern locking | ReentrantLock over synchronized; tryLock() for timeout |
| `src/streams/Streams.java` | Functional programming | map(), filter(), flatMap() chains |
| `src/DesignPatterns/BuilderPatternExample.java` | Fluent API | Private constructor, chainable setters, static factory |

---

## Debugging Tips

### For Concurrency Issues
- Check `src/threads/RaceCondition.java` and `Visibility.java` for examples of what to look for
- Use atomic types (`AtomicInteger`) or locks; avoid relying on timing
- Reference `MemoryLeakStaticField.java` - static fields can cause thread-related memory issues

### For Design Patterns
- Each pattern file is self-contained; run individually to verify behavior
- Factory pattern examples use String matching - watch for case sensitivity

### For Streams
- FlatMap is the key operation - study `FlatMapExample.java` for nested structure handling
- Terminal operations (collect, forEach) must be called to execute the pipeline


