# 📁 Sync-Folder

**Sync-Folder** is a lightweight Java CLI tool for syncing two local directories. It performs intelligent file comparisons and applies updates, deletions, or copies based on differences. Great for backups, deployments, and folder mirroring.

---

## 🔧 Usage
You first need to build the fat .jar file 
```bash
./gradlew shadowJar
```
And then you can run the installation script
```bash
sudo ./install_syncfolder.sh
```
Lastly, run the cli with:
```bash
syncfolder ./source ./target --dryrun
```