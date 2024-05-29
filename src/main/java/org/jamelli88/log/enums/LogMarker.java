package org.jamelli88.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * <p>
 *
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-28 17:32
 */
@Getter
@AllArgsConstructor
public enum LogMarker {
    /**
     * 系统日志
     */
    SYSTEM("系统日志", MarkerFactory.getMarker("SYSTEM")),
    /**
     * 请求日志
     */
    REQUEST("请求日志", MarkerFactory.getMarker("REQUEST")),
    /**
     * Mybatis日志
     */
    MYBATIS("Mybatis日志", MarkerFactory.getMarker("MYBATIS")),
    /**
     * 风控日志
     */
    RISK("风控日志", MarkerFactory.getMarker("RISK")),
    /**
     * 登录日志
     */
    LOGIN("登录日志", MarkerFactory.getMarker("LOGIN"));

    private final String desc;
    private final Marker marker;
}