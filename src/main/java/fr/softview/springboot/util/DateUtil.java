package fr.softview.springboot.util;

import org.apache.log4j.Logger;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Created by sambakamara on 19/11/2017.
 */
public class DateUtil {

    private final static Logger LOG = Logger.getLogger(DateUtil.class);

    /**
     *
     * @return current date with DATE_FORMATTER format
     */
    public static String getCurrentDate () {
        String currentDateTime = null;
        try {
            currentDateTime = LocalDateTime.now().format(Constants.DATE_FORMATTER);
        } catch (DateTimeException e) {
            LOG.error("Error parsing date " + LocalDateTime.now() + " to format "+ Constants.DATE_FORMATTER);
        }

        return currentDateTime;
    }
}
