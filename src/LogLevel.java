public enum LogLevel {
    DEBUG(0, "DEBUG"),
    INFO(1, "INFO"),
    NOTICE(2, "NOTICE"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR");

    private final int index;
    private final String level;

    private LogLevel(int index, String level) {
        this.index = index;
        this.level = level;
    }

    public int getIndex() {
        return index;
    }

    public String getLevel() {
        return level;
    }

    public static LogLevel indexOf(int index) {
        LogLevel[] levels = LogLevel.values();
        if (index >= 0 && index < levels.length) {
            return levels[index];
        }

        return null;
    }

    public String toString() {
        return ("[" + level + "]");
    }
}
