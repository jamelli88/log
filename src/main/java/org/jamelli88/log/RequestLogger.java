package org.jamelli88.log;

import org.jamelli88.log.enums.LogMarker;

/**
 * <p>
 *
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-12 20:48
 */
public class RequestLogger extends XLogger {
    public RequestLogger(Class<?> clazz) {
        super(LogMarker.REQUEST.getMarker(), clazz);
    }
}