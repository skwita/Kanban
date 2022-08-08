package com.skwita.Kanban.web;

import com.skwita.Kanban.data.CardRepository;
import com.skwita.Kanban.model.Card;
import com.skwita.Kanban.model.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebController implements WebMvcConfigurer {
    @Bean
    public CommandLineRunner dataLoader(CardRepository cardRepository) {
        if (cardRepository.count() == 0) {
            return args -> {
                cardRepository.save(
                        new Card("Create DAO",
                                 "Create data access object for getting data from database",
                                 Status.TODO,
                                 "Max"));
                cardRepository.save(
                        new Card("Add edit",
                                 "Create edit button for editing tasks",
                                 Status.TODO,
                                 "Max"));
                cardRepository.save(
                        new Card("Add creation of tasks",
                                 "Add a button and a view for creating tasks",
                                 Status.DONE,
                                 "Max"));
                cardRepository.save(
                        new Card("Add creating page",
                                 "Create a page for editing tasks",
                                 Status.INPROGRESS,
                                 "Max"));
                cardRepository.save(
                        new Card("Create stylesheet",
                                 "Ask my roommate to create stylesheet for the main page",
                                 Status.INPROGRESS,
                                 "Andrew"));
            };
        }
        return null;
    }
}
