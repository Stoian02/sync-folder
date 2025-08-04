#!/usr/bin/env bash

# Check if script is run with sudo
if [ "$EUID" -ne 0 ]; then
    echo -e "\e[41;97mError: This script must be run with sudo privileges.\e[0m"
    echo -e "\e[93mPlease run as: sudo $0\e[0m"
    exit 1
fi

# Define install paths
JAR_NAME="syncfolder-all.jar"
JAR_SOURCE="app/build/libs/$JAR_NAME"
INSTALL_DIR="/usr/local/lib/syncfolder"
BIN_PATH="/usr/local/bin/syncfolder"

echo -e "\e[93mInstalling SyncFolder...\e[0m"

# 1. Check if the JAR exists
if [ ! -f "$JAR_SOURCE" ]; then
    echo -e "\e[41;97mError: JAR not found at $JAR_SOURCE. Run './gradlew shadowJar' first.\e[0m"

    exit 1
fi

# 2. Create install dir
mkdir -p "$INSTALL_DIR"

# 3. Copy JAR to install dir
echo -e "\e[93mCopying $JAR_SOURCE to $INSTALL_DIR\e[0m"
cp "$JAR_SOURCE" "$INSTALL_DIR"

# 4. Create launcher script
echo -e "\e[93mCreating launcher at $BIN_PATH\e[0m"
tee "$BIN_PATH" > /dev/null <<EOF
#!/usr/bin/env bash
java -jar "$INSTALL_DIR/$JAR_NAME" "\$@"
EOF

# 5. Make it executable
chmod +x "$BIN_PATH"

echo -e "\e[92mSyncFolder installed successfully!\e[0m"
echo -e "\e[92mYou can now run it using: syncfolder ./source ./target --dryrun\e[0m"
