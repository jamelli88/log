package org.jamelli88.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * <p>
 *
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-12 19:15
 */
public class XLogger {

    private static Logger log;
    private static Marker marker;

    public XLogger(Marker marker, Class<?> clazz) {
        XLogger.marker = marker;
        log = LoggerFactory.getLogger(clazz);
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled(marker);
    }

    public void trace(String msg) {
        log.trace(marker, msg);
    }

    public void trace(String format, Object arg) {
        log.trace(marker, format, arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        log.trace(marker, format, arg1, arg2);
    }

    public void trace(String format, Object... arguments) {
        log.trace(marker, format, arguments);
    }

    public void trace(String msg, Throwable t) {
        log.trace(marker, msg, t);
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public void debug(String msg) {
        log.debug(marker, msg);
    }

    public void debug(String format, Object arg) {
        log.debug(marker, format, arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        log.debug(marker, format, arg1, arg2);
    }

    public void debug(String arg0, Object... arg1) {
        log.debug(marker, arg0, arg1);

    }

    public void debug(String msg, Throwable t) {
        log.debug(marker, msg, t);
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled(marker);
    }

    public void info(String msg) {
        log.info(marker, msg);
    }

    public void info(String format, Object arg) {
        log.info(marker, format, arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        log.info(marker, format, arg1, arg2);
    }

    public void info(String format, Object... arguments) {
        log.info(marker, format, arguments);
    }

    public void info(String msg, Throwable t) {
        log.info(marker, msg, t);
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled(marker);
    }

    public void warn(String msg) {
        log.warn(marker, msg);
    }

    public void warn(String format, Object arg) {
        log.warn(marker, format, arg);
    }

    public void warn(String format, Object... arguments) {
        log.warn(marker, format, arguments);
    }

    public void warn(String format, Object arg1, Object arg2) {
        log.warn(marker, format, arg1, arg2);
    }

    public void warn(String msg, Throwable t) {
        log.warn(marker, msg, t);
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled(marker);
    }

    public void error(String msg) {
        log.error(marker, msg);
    }

    public void error(String format, Object arg) {
        log.error(marker, format, arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        log.error(marker, format, arg1, arg2);
    }

    public void error(String format, Object... arguments) {
        log.error(marker, format, arguments);
    }

    public void error(String arg0, Throwable arg1) {
        log.error(marker, arg0, arg1);
    }
}