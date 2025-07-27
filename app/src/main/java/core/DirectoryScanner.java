package core;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import util.HashUtil;

public class DirectoryScanner {
    
    public static Map<String, FileInfo> scan(Path root) throws IOException {
        Map<String, FileInfo> files = new HashMap<>();

        Files.walkFileTree(root, new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                try {
                    String relativePath = root.relativize(file).toString();
                    long size = attrs.size();
                    long modified = attrs.lastModifiedTime().toMillis();
                    String hash = HashUtil.computeSHA256(file);

                    files.put(relativePath, new FileInfo(relativePath, size, modified, hash));
                } catch (Exception e) {
                    System.err.println("Failed to process file: " + file + " → " + e.getMessage());
                }
                return FileVisitResult.CONTINUE;
            }
        } );

        return files;
    }
}
