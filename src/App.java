public class App {
    private static final Log log = Log.getInstance();
    private static final int ARG_SIZE = 2;

    public static void main(String[] args) {
        if (args.length != ARG_SIZE) {
            log.justPrint("[usage] path out_file");
            return;
        }

        try {
            CheckDuplicate checkDuplicate = new CheckDuplicate(args[0], args[1]);
            checkDuplicate.DoCheck();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Exception caught: "+ex.getMessage());
        }
    }
}
