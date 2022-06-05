import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HexFormat;

public class CheckDuplicate {
    private static final Log log = Log.getInstance();
    private static final String SHA256 = "SHA-256";
    private static final int BLOCK_SIZE = 4096;
    private final String path;
    private final String outFile;

    public CheckDuplicate(String path, String outFile) throws Exception {
        String nPath = path.trim();
        if (nPath != null) {
            this.path = nPath;
            log.info("The path is: ["+this.path+"]");
        } else {
            throw new Exception("The path is blank!");
        }

        String nOutFile = outFile.trim();
        if (nOutFile != null) {
            this.outFile = nOutFile;
            log.info("The outFile is: ["+this.outFile+"]");
        } else {
            throw new Exception("The outFile is blank!");
        }
    }

    public void DoCheck() throws Exception {
        log.notice("Check: >>>>> BEGIN:");

        loadFile();

        scanPath();

        writeFile();

        log.notice("Check: >>>>> END!");
    }

    private void loadFile() {
        log.info("Loading file: "+outFile);
    }

    private void scanPath() throws Exception {
        log.info("Scanning path: "+path);

        File pathFile = new File(path);
        if (!pathFile.isDirectory()) {
            throw new Exception("The path is NOT directory: "+path);
        }
        log.notice("The absolute path is: ["+pathFile.getAbsolutePath()+"]");

        File[] fileList = pathFile.listFiles();
        if (fileList.length > 0) {
            for (File file : fileList) {
                scanSubPathFile(file);
            }
        } else {
            log.warn("The path directory is EMPTY!");
        }
    }

    private void scanSubPathFile(File file) throws Exception {
        if (file.isDirectory()) {
            log.info("{DIR} : ["+file.getAbsolutePath()+"]");

            File[] fileList = file.listFiles();
            if (fileList.length > 0) {
                for (File subFile : fileList) {
                    scanSubPathFile(subFile);
                }
            } else {
                log.warn("The directory is EMPTY!");
            }
        } else {
            log.info("<FILE> : ["+file.getAbsolutePath()+"]");
            SHA256Hash(file);
        }
    }

    private void SHA256Hash(File file) throws Exception {
        if (file.length() > 0L) {
            InputStream input = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance(SHA256);
            byte[] block = new byte[BLOCK_SIZE];

            int len = 0;
            while ((len = input.read(block)) > 0) {
                digest.update(block, 0, len);
            }
            input.close();

            HexFormat hex = HexFormat.of();
            StringBuffer strBuf = new StringBuffer("File: ");
            strBuf.append(file.getName()).append(", ").append(SHA256).append("=");
            strBuf.append(hex.formatHex(digest.digest()));
            log.notice(strBuf.toString());
        } else {
            log.warn("The file is EMPTY!");
        }
    }

    private void writeFile() {
        log.info("Writing file: "+outFile);
    }
}
