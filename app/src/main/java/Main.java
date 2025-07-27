import core.DirectoryScanner;
import core.FileInfo;

import java.nio.file.Path;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Directory Scanner Application");
        
        if (args.length < 1) {
            System.out.println("Incorrect usage, do: java Main <directory-path>");
            return;
        }

        Path path = Path.of(args[0]);
        Map<String, FileInfo> result = DirectoryScanner.scan(path);

        System.out.println("Scanned files:");
        result.values().forEach(System.out::println);
    }
}
