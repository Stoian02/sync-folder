package core;

import util.SyncAction;

public class SyncOperation {
    private SyncAction action;
    private String relativePath;
    private FileInfo sourceFile;
    private FileInfo targetFile;

    /**
     * Constructor for SyncOperation.
     *
     * @param action the action to perform (COPY, DELETE, UPDATE, SKIP)
     * @param relativePath the relative path of the file
     * @param sourceFile the source file information (if applicable)
     * @param targetFile the target file information (if applicable)
     */
    public SyncOperation(SyncAction action, String relativePath, FileInfo sourceFile, FileInfo targetFile) {
        this.action = action;
        this.relativePath = relativePath;
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
    }

    /**
     * @return SyncAction return the action
     */
    public SyncAction getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(SyncAction action) {
        this.action = action;
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
     * @return FileInfo return the sourceFile
     */
    public FileInfo getSourceFile() {
        return sourceFile;
    }

    /**
     * @param sourceFile the sourceFile to set
     */
    public void setSourceFile(FileInfo sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * @return FileInfo return the targetFile
     */
    public FileInfo getTargetFile() {
        return targetFile;
    }

    /**
     * @param targetFile the targetFile to set
     */
    public void setTargetFile(FileInfo targetFile) {
        this.targetFile = targetFile;
    }

}
