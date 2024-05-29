package org.jamelli88.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;
import org.jamelli88.log.enums.LogMarker;
import org.slf4j.Marker;

/**
 * <p>
 *
 * <p>
 *
 * @author Jamel.Li
 * @date 2022-01-12 14:04
 */
@Slf4j
public class XMarkerConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        Marker marker = event.getMarker();
        if (marker != null) {
            return marker.toString();
        }
        return LogMarker.SYSTEM.name();
    }
}