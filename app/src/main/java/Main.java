import picocli.CommandLine;

import cli.SyncFolder;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Directory Scanner Application");

        if (args.length < 2) {
            System.out.println("Incorrect usage, do: java Main <source-path> <target-path>");
            return;
        }

        int exitCode = new CommandLine(new SyncFolder()).execute(args);
        System.exit(exitCode);
    }
}
