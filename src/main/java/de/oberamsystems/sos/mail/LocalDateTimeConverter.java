package de.oberamsystems.sos.mail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import jakarta.annotation.Nullable;

@Component
@WritingConverter
@ConfigurationPropertiesBinding
public class LocalDateTimeConverter implements Converter<LocalDateTime, String> {
	
	@Bean
	public LocalDateTimeConverter ldtConverter() {	
		return new LocalDateTimeConverter();
	}

	@Override
	public @Nullable String convert(LocalDateTime source) {
		return source.format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
	}
}
