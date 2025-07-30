package cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import util.DiffEngine;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import core.DirectoryScanner;
import core.FileInfo;
import core.SyncExecutor;
import core.SyncOperation;

@Command(
    name = "syncfolder",
    mixinStandardHelpOptions = true,
    version = "syncfolder 1.0",
    description = "Synchronizes two directories (source, target) by comparing their contents and applying necessary operations."
)
public class SyncFolder implements Callable<Integer> {
    
    @Parameters(index = "0", description = "Source directory")
    private Path sourceDir;

    @Parameters(index = "1", description = "Target directory")
    private Path targetDir;

    @Option(names = {"-dr", "--dryrun"}, description = "Run without making changes", defaultValue = "false")
    private boolean dryRun;

    @Override
    public Integer call() throws Exception {
        
        Map<String, FileInfo> source = DirectoryScanner.scan(sourceDir);
        Map<String, FileInfo> target = DirectoryScanner.scan(targetDir);

        List<SyncOperation> operations = DiffEngine.compare(source, target);

        SyncExecutor.execute(operations, sourceDir, targetDir, dryRun);
 
        return 0;
    }
}
