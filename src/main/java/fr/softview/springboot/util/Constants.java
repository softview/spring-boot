package fr.softview.springboot.util;

import java.time.format.DateTimeFormatter;

/**
 * Created by sambakamara on 19/11/2017.
 */
public class Constants {

    public static final DateTimeFormatter DATE_FORMATTER =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final String ERROR_INVALID_ACCOUNT = "ERROR_INVALID_ACCOUNT";
    public static final String ERROR_INVALID_OPERATION = "ERROR_INVALID_OPERATION";
}
