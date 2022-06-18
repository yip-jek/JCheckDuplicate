import java.io.File;
import java.io.FileWriter;

public class Log {
    private static final String LOG_PREFIX = "CHKDUP_LOG_";
    private static Log LOG = null;
    private static LogLevel LOG_LEVEL = LogLevel.DEBUG;
    private static FileWriter LOG_WRITER = null;

    public static Log getInstance() {
        if (LOG == null) {
            LOG = new Log();
        }

        return LOG;
    }

    public static void release() {
        if (LOG_WRITER != null) {
            try {
                LOG_WRITER.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setLogLevel(LogLevel level) {
        LOG_LEVEL = level;
    }

    public static void setLogPath(String path) {
        if (path != null && !path.isEmpty()) {
            try {
                File pathFile = new File(path);
                if (!pathFile.isDirectory()) {
                    throw new Exception("The path is NOT directory: " + path);
                }

                if (LOG_WRITER != null) {
                    LOG_WRITER.close();
                }

                StringBuilder strBuf = new StringBuilder(path);
                final char LAST_CH = path.charAt(path.length() - 1);
                if (LAST_CH != '/' && LAST_CH != '\\') {
                    strBuf.append("/");
                }

                strBuf.append(LOG_PREFIX).append(Global.currentDateTimeFormat(Global.YYYYMMDDHHMISS)).append(".log");
                justPrint("Create the log file: "+strBuf.toString());
                LOG_WRITER = new FileWriter(strBuf.toString(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void justPrint(String msg) {
        if (Global.isEmpty(msg)) {
            System.out.println();
        } else {
            System.out.println(msg);
        }
    }

    private Log() {
    }

    public void outPut(LogLevel level, String msg) {
        StringBuffer strBuf = new StringBuffer(Global.currentDateTimeString());
        strBuf.append(" ").append(level).append(" ").append(msg);

        justPrint(strBuf.toString());
        if (LOG_WRITER != null && level.getLevel() >= LOG_LEVEL.getLevel()) {
            try {
                strBuf.append("\n");
                LOG_WRITER.write(strBuf.toString());
                LOG_WRITER.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void debug(String msg) {
        outPut(LogLevel.DEBUG, msg);
    }

    public void info(String msg) {
        outPut(LogLevel.INFO, msg);
    }

    public void notice(String msg) {
        outPut(LogLevel.NOTICE, msg);
    }

    public void warn(String msg) {
        outPut(LogLevel.WARN, msg);
    }

    public void error(String msg) {
        outPut(LogLevel.ERROR, msg);
    }
}
