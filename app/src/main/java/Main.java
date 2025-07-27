import core.DirectoryScanner;
import core.FileInfo;
import util.DiffEngine;

import java.nio.file.Path;
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
        // Map<String, FileInfo> result = DirectoryScanner.scan(path);

        // System.out.println("Scanned files:");
        // result.values().forEach(System.out::println);

        Map<String, FileInfo> source = DirectoryScanner.scan(path_1);
        Map<String, FileInfo> target = DirectoryScanner.scan(path_2);

        DiffEngine.compare(source, target);
    }
}
