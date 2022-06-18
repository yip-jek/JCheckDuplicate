public enum LoadMode {
    NEW("NEW", "新增"),
    APPEND("APPEND", "追加"),
    UPDATE("UPDATE", "更新");

    private final String mode;
    private final String modeDesc;

    private LoadMode(String mode, String desc) {
        this.mode = mode;
        this.modeDesc = desc;
    }

    public String getMode() {
        return mode;
    }

    public String getModeDesc() {
        return modeDesc;
    }

    public static LoadMode modeOf(String mode) {
        LoadMode[] modes = LoadMode.values();
        for (LoadMode m : modes) {
            if (m.getMode().equals(mode)) {
                return m;
            }
        }

        return null;
    }

    public String toString() {
        return ("[" + mode + "]");
    }
}
