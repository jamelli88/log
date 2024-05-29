package org.jamelli88.log.constants;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-12 15:00
 */
public interface LogConstants {

    /**
     * MDC自定义值——请求信息
     */
    String MDC_REQUEST_INFO = "requestInfo";
    String MDC_LOGGER = "logger";
    String MDC_METHOD = "method";
    String MDC_LINE = "line";
    String MDC_USER_ID = "userId";

    /**
     * 可忽略日志的URL
     */
    List<String> IGNORE_LOG_URLS = CollUtil.newArrayList(
            "/favicon.ico",
            "/error",
            "/public/**",
            "/static/**",
            "/actuator/**",
            "/**/swagger-ui.html",
            "/**/doc.html",
            "/**/webjars/**",
            "/**/v2/api-docs**",
            "/**/v2/api-docs-ext/**",
            "/**/swagger-resources/**",
            "/**/druid",
            "/**/generator"
    );
}