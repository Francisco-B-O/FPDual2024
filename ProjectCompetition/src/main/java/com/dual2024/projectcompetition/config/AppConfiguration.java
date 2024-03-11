package com.dual2024.projectcompetition.config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class for the application.
 *
 * <p>This class provides configuration settings for the application, including the creation of a {@link ModelMapper} bean.
 * The {@link ModelMapper} bean is used for object mapping, facilitating the conversion between different object types.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * // In your application code or other configuration classes, you can autowire the ModelMapper bean
 * \@Autowired
 * private ModelMapper modelMapper;
 *
 * // Now you can use the modelMapper for object mapping in your components
 * // For example:
 * SomeDTO someDTO = modelMapper.map(someEntity, SomeDTO.class);
 * }
 * </pre>
 *
 * <p>Instances of this class are typically used as a configuration source for the Spring framework, defining beans and their dependencies.</p>
 *
 * @author Francisco Balonero Olivera
 * @see org.modelmapper.ModelMapper
 */
@Data
@Configuration
public class AppConfiguration {

    /**
     * Creates a ModelMapper bean.
     *
     * @return The ModelMapper bean
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
