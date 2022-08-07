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
        todoCardList.add(new Card("Create DAO", "Create data access object for getting data from database", Status.TODO, "Max"));
        todoCardList.add(new Card("Add edit", "Create edit button for editing tasks", Status.TODO, "Max"));
        doneCardList.add(new Card("Add creation of tasks", "Add a button and a view for creating tasks", Status.DONE, "Max"));
        inProgressCardList.add(new Card("Add creating page", "Create a page for editing tasks", Status.INPROGRESS, "Max"));
        inProgressCardList.add(new Card("Create stylesheet", "Ask my roommate to create stylesheet for the main page", Status.INPROGRESS, "Andrew"));
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
