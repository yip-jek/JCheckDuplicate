public enum LogLevel {
    DEBUG(0, "DEBUG"),
    INFO(1, "INFO"),
    NOTICE(2, "NOTICE"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR");

    private final int level;
    private final String levelDesc;

    private LogLevel(int level, String desc) {
        this.level = level;
        this.levelDesc = desc;
    }

    public int getLevel() {
        return level;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public static LogLevel levelOf(int level) {
        LogLevel[] levels = LogLevel.values();
        if (level >= 0 && level < levels.length) {
            return levels[level];
        }

        return null;
    }

    public String toString() {
        return ("[" + level + "]");
    }
}
