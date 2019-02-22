package pl.konrad.szulc.chata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pl.konrad.szulc.chata.service.StorageProperties;
import pl.konrad.szulc.chata.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ChataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChataApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }
}
