package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import core.FileInfo;
import core.SyncOperation;

public class DiffEngine {
     
    public static List<SyncOperation> compare(Map<String, FileInfo> source, Map<String, FileInfo> target) {
        List<SyncOperation> operations = new ArrayList<>();

        for (Map.Entry<String, FileInfo> entry : source.entrySet()) {
            String path = entry.getKey();
            FileInfo sourceFile = entry.getValue();
            FileInfo targetFile = target.get(path);

            if (targetFile == null) {
                // Not in target, must copy
                operations.add(new SyncOperation(SyncAction.COPY, path, sourceFile, null));
                System.out.println("File " + path + " needs to be copied.");
            } else {
                // Exists in both, check for difference
                boolean isDifferent = sourceFile.getSize() != targetFile.getSize()
                        || sourceFile.getLastModified() != targetFile.getLastModified()
                        || !sourceFile.getHash().equals(targetFile.getHash());

                if (isDifferent) {
                    operations.add(new SyncOperation(SyncAction.UPDATE, path, sourceFile, targetFile));
                    System.out.println("File " + path + " needs to be updated.");
                    continue;
                }
                System.out.println("File " + path + " is the same, no action needed.");
            }
        }

        // Check for files that need to be deleted
        for (Map.Entry<String, FileInfo> entry : target.entrySet()) {
            String path = entry.getKey();
            if (!source.containsKey(path)) {
                operations.add(new SyncOperation(SyncAction.DELETE, path, null, entry.getValue()));
                System.out.println("File " + path + " needs to be deleted from target.");
            }
        }

        return operations;
    }
}
