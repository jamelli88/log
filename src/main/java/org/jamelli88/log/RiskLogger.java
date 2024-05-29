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
public class RiskLogger extends XLogger {
    public RiskLogger(Class<?> clazz) {
        super(LogMarker.RISK.getMarker(), clazz);
    }
}