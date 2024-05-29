package org.jamelli88.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.util.StrUtil;
import org.jamelli88.log.XLogger;
import org.jamelli88.log.constants.LogConstants;
import org.slf4j.MDC;

/**
 * @author Jamel.Li
 */
public class XLineOfCallerConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        StackTraceElement[] stackTraceElements = event.getCallerData();
        if (stackTraceElements == null) {
            MDC.put(LogConstants.MDC_LINE, CallerData.NA);
            return CallerData.NA;
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(stackTraceElements[0].getClassName());
        } catch (ClassNotFoundException e) {
            MDC.put(LogConstants.MDC_LINE, CallerData.NA);
            return CallerData.NA;
        }
        if (XLogger.class.equals(clazz)) {
            String line = event.getMDCPropertyMap().get(LogConstants.MDC_LINE);
            if (StrUtil.isBlank(line)) {
                line = stackTraceElements[1].getLineNumber() + "";
            }
            MDC.put(LogConstants.MDC_LINE, line);
            return line;
        }
        String line = Integer.toString(stackTraceElements[0].getLineNumber());
        MDC.put(LogConstants.MDC_LINE, line);
        return line;
    }

}