package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingUtil {
    private static final Logger logger = LogManager.getLogger(LoggingUtil.class);

    public static Logger getLogger() {
        return logger;
    }

}
