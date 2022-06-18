import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

public class CheckSumFile {
    private static final Log log = Log.getInstance();
    private static final int BLOCK_SIZE = 4096;
    private final String file;
    private final LoadMode mode;
    private final DigestType type;

    private Map<String, String> sumMap;

    public CheckSumFile(String file, LoadMode mode, DigestType type) {
        this.file = file;
        this.mode = mode;
        this.type = type;

        sumMap = new HashMap<>();
    }

    public void loadFile() throws Exception {
        log.info(String.format("Loading file: %s, by mode %s", file, mode));

        if (mode.equals(LoadMode.UPDATE)) {
            ;
        } else if (mode.equals(LoadMode.APPEND)) {
            ;
        } else if (mode.equals(LoadMode.NEW)) {
            ;
        } else {
            throw new Exception(String.format("Unsupported load mode: [%s]", mode.getMode()));
        }
    }

    public void checkSum(File file) throws Exception {
        if (file.length() > 0L) {
            InputStream input = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance(type.getType());
            byte[] block = new byte[BLOCK_SIZE];

            int len = 0;
            while ((len = input.read(block)) > 0) {
                digest.update(block, 0, len);
            }
            input.close();

            HexFormat hex = HexFormat.of();
            String digestHex = hex.formatHex(digest.digest());
            sumMap.put(digestHex, file.getAbsolutePath());

            StringBuffer strBuf = new StringBuffer("File: ");
            strBuf.append(file.getName()).append(", ").append(type.getType()).append("=").append(digestHex);
            log.notice(strBuf.toString());
        } else {
            log.warn("The file is EMPTY!");
        }
    }

    public void writeFile() {
        log.info("Writing file: "+file);
    }
}
