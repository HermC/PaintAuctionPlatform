package edu.nju.ise.auction.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date string2Date(String timeStr) {
        timeStr = timeStr.trim();
        if (timeStr == null) {
            return null;
        }
        if (timeStr.length() <= 10) {
            timeStr += " 00:00:00";
        } else if (timeStr.length() <= 16) {
            timeStr += ":00";
        }
        try {
            return FORMAT.parse(timeStr);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
