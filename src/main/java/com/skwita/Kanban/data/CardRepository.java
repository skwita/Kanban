package com.skwita.Kanban.data;

import com.skwita.Kanban.model.Card;
import com.skwita.Kanban.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByStatusOrderById(Status status);
    Card getCardById(long id);

    void deleteCardById(long id);
}
