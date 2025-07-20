/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.config.converter;

import cn.zhaofd.core.spring.jpa.config.converter.BaseInstant2LocalDateTimeConverter;
import jakarta.persistence.Converter;

/**
 * JPA属性转换器：Instant转LocalDateTime
 */
@Converter(autoApply = true)
public class Instant2LocalDateTimeConverter extends BaseInstant2LocalDateTimeConverter {
}
