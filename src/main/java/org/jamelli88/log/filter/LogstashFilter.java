package org.jamelli88.log.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import cn.hutool.core.util.ArrayUtil;
import org.jamelli88.log.constants.LogConstants;
import org.slf4j.MDC;

/**
 * <p>
 * 不知道为什么Logstash的Appender没有获取到自定义方法对象值
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-30 15:30
 */
public class LogstashFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getMDCPropertyMap().containsKey(LogConstants.MDC_METHOD)) {
            return FilterReply.ACCEPT;
        }
        StackTraceElement[] stackTraceElements = event.getCallerData();
        if (ArrayUtil.isEmpty(stackTraceElements)) {
            return FilterReply.ACCEPT;
        }
        StackTraceElement stackTraceElement = stackTraceElements[0];
        MDC.put(LogConstants.MDC_LOGGER, event.getLoggerName());
        MDC.put(LogConstants.MDC_METHOD, stackTraceElement.getMethodName());
        return FilterReply.ACCEPT;
    }
}