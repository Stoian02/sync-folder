package core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class SyncExecutor {
    
    public static void execute(List<SyncOperation> operations, Path sourceRoot, Path targetRoot, boolean dryRun) {
        Path sourcePath;
        Path targetPath;

        for (SyncOperation operation : operations) {
            switch (operation.getAction()) {
                case COPY:
                    sourcePath = sourceRoot.resolve(operation.getRelativePath());
                    targetPath = targetRoot.resolve(operation.getRelativePath());

                    if (dryRun) {
                        System.out.println("[DRY RUN] COPY: " + sourcePath + " > " + targetPath);
                        break;
                    }

                    try {
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                        System.out.println("[COPIED] " + sourcePath + " > " + targetPath);
                    } catch (Exception e) {
                        System.err.println("Failed to copy file: " + sourcePath + " > " + targetPath + ", error: " + e.getMessage());
                    }
                    
                    break;
                case DELETE:
                    targetPath = targetRoot.resolve(operation.getRelativePath());

                    if (dryRun) {
                        System.out.println("[DRY RUN] DELETE: " + targetPath);
                        break;
                    }

                    try {
                        Files.deleteIfExists(targetPath);
                        System.out.println("[DELETED] " + targetPath);
                    } catch (Exception e) {
                        System.err.println("Failed to delete file: " + targetPath + ", error: " + e.getMessage());
                    }

                    break;
                case UPDATE:
                    sourcePath = sourceRoot.resolve(operation.getRelativePath());
                    targetPath = targetRoot.resolve(operation.getRelativePath());

                    if (dryRun) {
                        System.out.println("[DRY RUN] UPDATE: " + sourcePath + " > " + targetPath);
                        break;
                    }

                    try {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                        System.out.println("[UPDATED] " + sourcePath + " > " + targetPath);
                    } catch (Exception e) {
                        System.err.println("Failed to copy file: " + sourcePath + " > " + targetPath + ", error: " + e.getMessage());
                    }
                    
                    break;
                case SKIP:
                    if (dryRun) {
                        System.out.println("[DRY RUN] SKIP: " + operation.getRelativePath());
                        break;
                    }

                    System.out.println("[SKIPPED] " + operation.getRelativePath());

                    break;
                default:
                    System.err.println("Unknown SyncAction: " + operation.getAction());
                    break;
            }
        }
    }
}
