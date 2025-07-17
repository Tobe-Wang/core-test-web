/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * JPA属性转换器：Instant转LocalDateTime
 */
@Converter(autoApply = true)
public class Instant2LocalDateTimeConverter implements AttributeConverter<Instant, LocalDateTime> {
    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");

    /**
     * 将 Java 实体中的 Instant (UTC时间点) 转换为上海时区的 LocalDateTime
     *
     * @param instant 实体中的 Instant (UTC时间点)
     * @return LocalDateTime (上海时区)
     */
    @Override
    public LocalDateTime convertToDatabaseColumn(Instant instant) {
        return instant == null ? null : LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * 将数据库中的 LocalDateTime (解释为上海时间) 转回 UTC 时间点
     *
     * @param dbData 数据库中的 LocalDateTime (解释为上海时间)
     * @return UTC 时间点
     */
    @Override
    public Instant convertToEntityAttribute(LocalDateTime dbData) {
        return dbData == null ? null : dbData.atZone(zoneId).toInstant();
    }
}
