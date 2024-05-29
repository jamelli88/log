package org.jamelli88.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;
import org.jamelli88.log.util.IpUtil;

/**
 * logback获得当前服务器IP地址
 * @author Administrator
 */
@Slf4j
public class LogIpConverter extends ClassicConverter {

    private final static String IP = "XXX.XXX.XXX.XXX";

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return IpUtil.getServerIp();
        } catch (Exception e) {
            return IP;
        }
    }
}