package com.utopia.social_network.utopia_api;

import com.utopia.social_network.utopia_api.utils.FileStorageProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class UtopiaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtopiaApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
