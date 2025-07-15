# 📁 Sync-Folder

**Sync-Folder** is a lightweight Java CLI tool for syncing two local directories. It performs intelligent file comparisons and applies updates, deletions, or copies based on differences. Great for backups, deployments, and folder mirroring.

---

## 🚀 Features

- 🗂️ One-way or two-way sync modes
- 🔍 Compares files using size, timestamp, and SHA-256 hash
- 🧪 Dry-run mode to preview changes without applying them
- 📣 Verbose output with status messages
- 🔁 Optional file watcher to sync on change
- 📦 No frameworks — just plain Java + Gradle + Picocli

---

## 🔧 Usage

```bash
java -jar build/libs/sync-folder.jar sync <source> <target> [options]
