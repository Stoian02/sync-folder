import core.DirectoryScanner;
import core.FileInfo;
import core.SyncExecutor;
import core.SyncOperation;
import util.DiffEngine;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Directory Scanner Application");
        
        String[] args_2 = {"./test", "./target"};
        
        if (args_2.length < 2) {
            System.out.println("Incorrect usage, do: java Main <source-path> <target-path>");
            return;
        }

        Path path_1 = Path.of(args_2[0]);
        Path path_2 = Path.of(args_2[1]);

        Map<String, FileInfo> source = DirectoryScanner.scan(path_1);
        Map<String, FileInfo> target = DirectoryScanner.scan(path_2);

        List<SyncOperation> operations = DiffEngine.compare(source, target);

        SyncExecutor.execute(operations, path_1, path_2, true);
    }
}
