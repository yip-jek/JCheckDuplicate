public class Log {
    private static Log LOG = null;
    LogLevel logLevel;

    public static Log getInstance() {
        if (LOG == null) {
            LOG = new Log();
        }

        return LOG;
    }

    public static Log getInstance(LogLevel level) {
        if (LOG == null) {
            LOG = new Log(level);
        }

        return LOG;
    }

    private Log() {
        System.out.println("Set the LogLevel to default: "+LogLevel.DEBUG);
        logLevel = LogLevel.DEBUG;
    }

    private Log(LogLevel level) {
        if (level != null) {
            System.out.println("Set the LogLevel: "+level);
            this.logLevel = level;
        } else {
            System.out.println("The setting LogLevel is null! Reset the LogLevel to "+LogLevel.DEBUG);
            this.logLevel = LogLevel.DEBUG;
        }
    }

    public void outPut(LogLevel level, String msg) {
        if (level.getIndex() >= logLevel.getIndex()) {
            StringBuffer strBuf = new StringBuffer(Global.currentDateTimeString());
            strBuf.append(" ").append(level).append(" ").append(msg);
            justPrint(strBuf.toString());
        }
    }

    public void justPrint(String msg) {
        System.out.println(msg);
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
