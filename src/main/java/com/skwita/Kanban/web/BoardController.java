package com.skwita.Kanban.web;

import com.skwita.Kanban.data.CardRepository;
import com.skwita.Kanban.model.Card;
import com.skwita.Kanban.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
                            cardRepository.findByStatus(Status.TODO));
        model.addAttribute("inProgressCards",
                            cardRepository.findByStatus(Status.INPROGRESS));
        model.addAttribute("doneCards",
                            cardRepository.findByStatus(Status.DONE));
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

}
