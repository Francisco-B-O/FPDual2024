package com.Dual2024.ProjectCompetition.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class AppConfiguration {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
