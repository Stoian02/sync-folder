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
            } else {
                // TODO: Maybe remove the check for time, because it is not very reliable. 
                boolean isDifferent = sourceFile.getSize() != targetFile.getSize()
                        || sourceFile.getLastModified() != targetFile.getLastModified()
                        || !sourceFile.getHash().equals(targetFile.getHash());

                if (isDifferent) {
                    operations.add(new SyncOperation(SyncAction.UPDATE, path, sourceFile, targetFile));
                    continue;
                }
            }
        }

        // Check for files that need to be deleted
        for (Map.Entry<String, FileInfo> entry : target.entrySet()) {
            String path = entry.getKey();
            if (!source.containsKey(path)) {
                operations.add(new SyncOperation(SyncAction.DELETE, path, null, entry.getValue()));
            }
        }

        return operations;
    }
}
