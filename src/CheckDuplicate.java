import java.io.File;

public class CheckDuplicate {
    private static final Log log = Log.getInstance();
    private final String path;
    private final String outFile;
    private final LoadMode loadMode;

    public CheckDuplicate(String path, String outFile, String mode) throws Exception {
        String nPath = path.trim();
        if (nPath != null) {
            this.path = nPath;
            log.info(String.format("The path is: [%s]", this.path));
        } else {
            throw new Exception("The path is blank!");
        }

        String nOutFile = outFile.trim();
        if (nOutFile != null) {
            this.outFile = nOutFile;
            log.info(String.format("The outFile is: [%s]", this.outFile));
        } else {
            throw new Exception("The outFile is blank!");
        }

        this.loadMode = LoadMode.modeOf(mode.toUpperCase());
        if (this.loadMode == null) {
            throw new Exception(String.format("Unknown load mode: [%s]", mode));
        }
        log.info(String.format("The load mode is: [%s]", this.loadMode.getMode()));
    }

    public void doCheck() throws Exception {
        log.notice("Check: >>>>> BEGIN:");

        CheckSumFile checkSum = new CheckSumFile(outFile, loadMode, DigestType.SHA256);
        checkSum.loadFile();

        scanPath(checkSum);

        checkSum.writeFile();

        log.notice("Check: >>>>> END!");
    }

    private void scanPath(CheckSumFile checkSum) throws Exception {
        log.info("Scanning path: "+path);

        File pathFile = new File(path);
        if (!pathFile.isDirectory()) {
            throw new Exception("The path is NOT directory: "+path);
        }
        log.notice(String.format("The absolute path is: [%s]", pathFile.getAbsolutePath()));

        scanSubPathFile(checkSum, pathFile);
    }

    private void scanSubPathFile(CheckSumFile checkSum, File file) throws Exception {
        if (file.isDirectory()) {
            log.info(String.format("{DIR} : [%s]", file.getAbsolutePath()));

            File[] fileList = file.listFiles();
            if (fileList.length > 0) {
                for (File subFile : fileList) {
                    scanSubPathFile(checkSum, subFile);
                }
            } else {
                log.warn("The directory is EMPTY!");
            }
        } else {
            log.info(String.format("<FILE> : [%s]", file.getAbsolutePath()));
            checkSum.checkSum(file);
        }
    }
}
