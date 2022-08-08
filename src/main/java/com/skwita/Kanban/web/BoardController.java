package com.skwita.Kanban.web;

import com.skwita.Kanban.data.CardRepository;
import com.skwita.Kanban.model.Card;
import com.skwita.Kanban.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {
    private final CardRepository cardRepository;

    @Autowired
    public BoardController(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @GetMapping("/")
    public String board(Model model){
        model.addAttribute("todoCards",
                            cardRepository.findByStatusOrderById(Status.TODO));
        model.addAttribute("inProgressCards",
                            cardRepository.findByStatusOrderById(Status.INPROGRESS));
        model.addAttribute("doneCards",
                            cardRepository.findByStatusOrderById(Status.DONE));
        return "board";
    }

    @GetMapping("new")
    public String createTask(@ModelAttribute Card card){
        return "new_card";
    }

    @PostMapping()
    public String create(@ModelAttribute("card") Card card){
        cardRepository.save(card);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable("id") long id,
                           Model model){
        model.addAttribute("card", cardRepository.getCardById(id));
        return "edit_card";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("post") Card card){
        cardRepository.save(card);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long id){
        cardRepository.deleteCardById(id);
        return "redirect:/";
    }

    @PostMapping("/{id}/move")
    public String movePost(@PathVariable("id") long id){
        Card card = cardRepository.getCardById(id);
        if (card.getStatus() == Status.TODO) {
            card.setStatus(Status.INPROGRESS);
        } else if (card.getStatus() == Status.INPROGRESS) {
            card.setStatus(Status.DONE);
        }
        cardRepository.save(card);
        return "redirect:/";
    }

}
