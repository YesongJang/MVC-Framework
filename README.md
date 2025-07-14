# MVC Framework

A lightweight, reusable Model-View-Controller (MVC) framework implemented in Java.  
This framework provides a flexible base for building interactive, event-driven GUI applications with modular design and separation of concerns.

---

## 🎯 Features

- **MVC Architecture**: Separates application state (Model), presentation (View), and logic (Controller/AppPanel)
- **Command Pattern**: Supports undoable commands via a command registry
- **Event Notification System**: Custom Publisher/Subscriber interfaces to allow model-view communication
- **Menu Bar Support**: Easily attach Save, Load, About, and Exit menus via `SafeFrame`
- **Utility Integration**: File I/O, command execution, history, and UI enhancements

---

## 📁 Core Components

| File              | Purpose |
|-------------------|---------|
| `Model.java`      | Abstract base for all models (state + behavior) |
| `View.java`       | Subscribes to model changes and updates UI |
| `AppFactory.java` | Defines available commands, model creation, views |
| `AppPanel.java`   | Central controller tying together model, view, commands |
| `Command.java`    | Represents an executable (and possibly undoable) action |
| `Utilities.java`  | Helper methods for file I/O, menus, dialogs, etc |
| `SafeFrame.java`  | Main window frame with menu bar & title |
| `Publisher.java`  | Interface for publish-subscribe pattern |
| `Subscriber.java` | Interface to receive model update notifications |
| `Heading.java`    | (Optional) Enum or helper for navigation or direction |

---

## 🔄 Architecture Diagram (Textual)

              +---------------------+
              |      AppFactory     | <== Defines model, commands, views
              +---------------------+
                        |
                        v
                  +-----------+
                  |  AppPanel | <== Ties together Model & View
                  +-----------+
                   /          \
     +-------------+           +-----------+
     |    Model    |  <=====>  |    View   |  <==> User Interface
     +-------------+   Notify  +-----------+
           ↑
     +-------------------------+
     |  Publisher / Subscriber |
     +-------------------------+


---

## ▶️ How to Use

> ⚠️ This repository is **not intended to be run as a standalone application**.  
> It serves as the **foundational MVC framework** used across other Java GUI projects.

You can **clone or import** this framework into your own projects to build applications based on the MVC (Model-View-Controller) architecture.

This framework has been reused in multiple projects, including:

- [Echo Server](https://github.com/YesongJang/Echo-Server)
- [SimStation](#) 
- [SmartBox](#)

---

### 🔧 Integration Example

In your own project:
1. Copy or import all `.java` files from this repository
2. Create your own `AppFactory` subclass (e.g., `MyAppFactory.java`)
3. Launch the application using:

```java
new SafeFrame("My App", new MyAppFactory());
```
4. Implement custom ```Model```, ```View```, and ```Command``` classes as needed

---

### 🛠 Recommended Use Case

- Small to mid-sized desktop Java applications
- Educational use (CS courses)
- GUI projects with pluggable commands (e.g., drawing apps, simulations, editors)

---

### ✅ Why I Built This

This framework was developed as part of my CS151 course at San Jose State University
to provide a clean and extensible architecture for Java-based GUI applications,
and is reused across projects like **EchoServer**, **SimStation**, and **SmartBox**.


