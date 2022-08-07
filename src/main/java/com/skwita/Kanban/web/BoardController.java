package com.skwita.Kanban.web;

import com.skwita.Kanban.model.Card;
import com.skwita.Kanban.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    private List<Card> todoCardList = new ArrayList<>();
    private List<Card> inProgressCardList = new ArrayList<>();
    private List<Card> doneCardList = new ArrayList<>();
    {
        todoCardList.add(new Card("a", "bm", Status.TODO, "me"));
        todoCardList.add(new Card("b", "bbbm", Status.TODO, "me"));
        doneCardList.add(new Card("c", "bbbm", Status.DONE, "me"));
        inProgressCardList.add(new Card("d", "bbbm", Status.INPROGRESS, "me"));
    }
    @GetMapping("/")
    public String board(Model model){
        model.addAttribute("todoCards", todoCardList);
        model.addAttribute("inProgressCards", inProgressCardList);
        model.addAttribute("doneCards", doneCardList);
        return "board";
    }

    @GetMapping("new")
    public String createTask(@ModelAttribute Card card){
        return "new_card";
    }

    @PostMapping()
    public String create(@ModelAttribute("card") Card card){
        if (card.getStatus() == Status.TODO) {
            todoCardList.add(card);
        } else if (card.getStatus() == Status.INPROGRESS) {
            inProgressCardList.add(card);
        } else if (card.getStatus() == Status.DONE) {
            doneCardList.add(card);
        }
        return "redirect:/";
    }

}
