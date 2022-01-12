package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j:
 * private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());
 * slf4j:
 * private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}