package com.Dual2024.ProjectCompetition.Config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type App configuration.
 */
@Data
@Configuration
public class AppConfiguration {

    /**
     * Model mapper.
     *
     * @return The model mapper
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
