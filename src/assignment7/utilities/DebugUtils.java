package assignment7.utilities;

/**
 * Created by martineisemann on 01.11.16.
 */
public class DebugUtils {

    /**
     * Returns
     * @return
     */
    public static String getFileAndLine(int index){
        StackTraceElement stackElement = Thread.currentThread().getStackTrace()[index];
        return "File: " + stackElement.getFileName() + ", Line: " + stackElement.getLineNumber();
    }
}
