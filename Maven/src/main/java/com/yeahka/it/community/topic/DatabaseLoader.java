package com.yeahka.it.community.topic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

    @Bean
    CommandLineRunner init(ArticleRepository repository) { // (1)
        return args -> { // (2)
            repository.save(new Article("grande mocha")); // (3)
            repository.save(new Article("venti hazelnut machiatto"));
        };
    }
}
