# 📁 Sync-Folder

**Sync-Folder** is a lightweight Java CLI tool for syncing two local directories. It performs intelligent file comparisons and applies updates, deletions, or copies based on differences. Great for backups, deployments, and folder mirroring.

---

## 🔧 Usage
You first need to build the fat .jar file 
```bash
java -jar build/libs/sync-folder.jar sync <source> <target> [options]
```
And then you can run the installation script
```bash
sudo ./install_syncfolder.sh
```