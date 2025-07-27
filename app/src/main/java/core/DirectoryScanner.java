package core;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import util.HashUtil;

/**
 * DirectoryScanner class provides functionality to scan a directory and gather information about its files.
 * It collects the relative path, size, last modified time, and SHA-256 hash of each file.
 */
public class DirectoryScanner {
    
    /**
     * Scans the specified directory and returns a map of file information.
     * 
     * @param root The root directory to scan
     * @return Map<String, FileInfo> A map where the key is the relative path and the value is a FileInfo object containing file details
     * @throws IOException if an error occurs while reading the directory
     */
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
