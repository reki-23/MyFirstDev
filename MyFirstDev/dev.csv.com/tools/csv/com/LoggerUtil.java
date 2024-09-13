package tools.csv.com;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {

    private static final ResourceBundle messages = ResourceBundle.getBundle("logging");
    private static FileHandler fileHandler;  // クラス全体で使い回すFileHandler
    private static final String LOG_FILE_PATH = "logs/CsvApplication.log";  // ログファイルパス
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            // FileHandlerを初期化して使い回す
            fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(false); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static Logger createLogger(Class<?> clazz) {
//    	Logger logger = Logger.getLogger(clazz.getName());
//    	logger.setUseParentHandlers(false);
//    	return logger;
//    }

    private static void addFileHandler(Logger logger) {
        // 既にファイルハンドラが追加されているか確認
        for (Handler handler : logger.getHandlers()) {
            if (handler instanceof FileHandler) {
                return;  // 既にFileHandlerが追加されている場合、何もしない
            }
        }
        logger.addHandler(fileHandler);  // FileHandlerが追加されていない場合、追加する
    }

    // ログ出力を統一するためのメソッド
    public static void logInfo(Class<?> clazz, String messageKey, Object... params) {
        Logger logger = Logger.getLogger(clazz.getName());
        addFileHandler(logger);
        String message = MessageFormat.format(messages.getString(messageKey), params);
        logger.info(message);
    }

    public static void logWarning(Class<?> clazz, String messageKey, Object... params) {
        Logger logger = Logger.getLogger(clazz.getName());
        addFileHandler(logger);
        String message = MessageFormat.format(messages.getString(messageKey), params);
        logger.warning(message);
    }

    public static void logSevere(Class<?> clazz, String messageKey, Object... params) {
        Logger logger = Logger.getLogger(clazz.getName());
        addFileHandler(logger);
        String message = MessageFormat.format(messages.getString(messageKey), params);
        logger.severe(message);
    }
}
