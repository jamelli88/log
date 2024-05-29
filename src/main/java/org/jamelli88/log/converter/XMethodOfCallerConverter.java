package org.jamelli88.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.util.StrUtil;
import org.jamelli88.log.XLogger;
import org.jamelli88.log.constants.LogConstants;
import org.slf4j.MDC;

/**
 * <p>
 *
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-12 19:56
 */
public class XMethodOfCallerConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        StackTraceElement[] stackTraceElements = event.getCallerData();
        if (stackTraceElements == null) {
            MDC.put(LogConstants.MDC_METHOD, CallerData.NA);
            return CallerData.NA;
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(stackTraceElements[0].getClassName());
        } catch (ClassNotFoundException e) {
            MDC.put(LogConstants.MDC_METHOD, CallerData.NA);
            return CallerData.NA;
        }
        if (XLogger.class.equals(clazz)) {
            String method = event.getMDCPropertyMap().get(LogConstants.MDC_METHOD);
            if (StrUtil.isBlank(method)) {
                method = stackTraceElements[1].getMethodName();
            }
            MDC.put(LogConstants.MDC_METHOD, method);
            return method;
        }
        MDC.put(LogConstants.MDC_METHOD, stackTraceElements[0].getMethodName());
        return stackTraceElements[0].getMethodName();
    }
}