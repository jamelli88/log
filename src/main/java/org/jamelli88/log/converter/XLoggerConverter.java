/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2015, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package org.jamelli88.log.converter;

import ch.qos.logback.classic.pattern.NamedConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.jamelli88.log.aspect.LogAspect;
import org.jamelli88.log.constants.LogConstants;
import org.slf4j.MDC;

public class XLoggerConverter extends NamedConverter {

    @Override
    protected String getFullyQualifiedName(ILoggingEvent event) {
        StackTraceElement[] stackTraceElements = event.getCallerData();
        if (stackTraceElements == null) {
            MDC.put(LogConstants.MDC_LOGGER, CallerData.NA);
            return CallerData.NA;
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(stackTraceElements[0].getClassName());
        } catch (ClassNotFoundException e) {
            MDC.put(LogConstants.MDC_LOGGER, CallerData.NA);
            return CallerData.NA;
        }
        if (LogAspect.class.equals(clazz)) {
            String logger = event.getMDCPropertyMap().get(LogConstants.MDC_LOGGER);
            MDC.put(LogConstants.MDC_LOGGER, logger);
            return logger;
        }
        MDC.put(LogConstants.MDC_LOGGER, event.getLoggerName());
        return event.getLoggerName();
    }
}
