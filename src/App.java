public class App {
    private static final Log log = Log.getInstance();
    private static final int ARG_SIZE = 4;

    public static void main(String[] args) {
        if (args.length != ARG_SIZE) {
            Log.justPrint("[usage] path out_file load_mode log_path");
            return;
        }
        Log.setLogPath(args[3]);

        try {
            CheckDuplicate checkDuplicate = new CheckDuplicate(args[0], args[1], args[2]);
            checkDuplicate.doCheck();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Exception caught: "+ex.getMessage());
        } finally {
            Log.release();
        }
    }
}
