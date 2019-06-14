package com.sr03.project.web.editors.property;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        System.out.println("HELLLLLLLLLLLLLLLLLLO " + locDateTime + " " + Timestamp.valueOf(locDateTime));
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        System.out.println("LLLLLLLLLLAAAAAAAAAAAAA " + sqlTimestamp + " " + sqlTimestamp.toLocalDateTime());
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}
