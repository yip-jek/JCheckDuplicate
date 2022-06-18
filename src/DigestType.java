public enum DigestType {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");
    
    private final String type;

    private DigestType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static DigestType typeOf(String type) {
        DigestType[] types = DigestType.values();
        for (DigestType dt : types) {
            if (dt.getType().equals(type)) {
                return dt;
            }
        }

        return null;
    }

    public String toString() {
        return ("[" + type + "]");
    }
}
