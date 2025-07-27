package util;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

/**
 * HashUtil class provides utility methods for computing file hashes.
 */
public class HashUtil {
    /**
     * Computes the SHA-256 hash of a file.
     *
     * @param path the path to the file
     * 
     * @return the SHA-256 hash as a hexadecimal string
     * @throws Exception if an error occurs while reading the file
     */
    public static String computeSHA256(Path path) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream is = Files.newInputStream(path)) {
            byte[] buffer = new byte[8162];
            int read;
            while((read = is.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        }

        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();

        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
