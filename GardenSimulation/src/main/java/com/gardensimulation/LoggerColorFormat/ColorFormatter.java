package com.gardensimulation.LoggerColorFormat;

import java.util.Formattable;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class ColorFormatter extends Formatter {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";

    @Override
    public String format(LogRecord record) {
        String color;

        if (record.getLevel() == Level.SEVERE) {
            color = RED;
        } else if (record.getLevel() == Level.WARNING) {
            color = YELLOW;
        } else if (record.getLevel() == Level.INFO) {
            color = GREEN;
        } else {
            color = RESET;
        }

        return color + "[" + record.getLevel() + "] " + record.getMessage() + RESET + "\n";
    }
}
