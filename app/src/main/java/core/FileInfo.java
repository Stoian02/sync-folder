package core;

public class FileInfo {
    private String relativePath;
    private long size;
    private long lastModified;
    private String hash;
    
    // Constructor
    public FileInfo(String relativePath, long size, long lastModified, String hash) {
        this.relativePath = relativePath;
        this.size = size;
        this.lastModified = lastModified;
        this.hash = hash;
    }

    /**
     * @return String return the relativePath
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * @param relativePath the relativePath to set
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    /**
     * @return long return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * @return long return the lastModified
     */
    public long getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return String return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /*
     * Overrides the default toString function.
     * 
     * @return String return the object formatted
     */
    @Override
    public String toString() {
        return String.format("%s [size=%d, modified=%d, hash=%s]", relativePath, size, lastModified, hash);
    }
}
