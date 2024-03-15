package com.dual2024.projectcompetition.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
     * @return {@link ModelMapper} The ModelMapper bean
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * OpenApi configuration
     *
     * @return {@link OpenAPI}
     */


    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("Project Competition API")
                        .description("API for tournament management")
                        .contact(new Contact()
                                .name("Francisco Balonero Olivera")
                                .url("https://github.com/Francisco-B-O"))
                        .version("0.0.1"));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
    }
}



