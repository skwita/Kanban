package com.skwita.Kanban.web;

import com.skwita.Kanban.data.CardRepository;
import com.skwita.Kanban.model.Card;
import com.skwita.Kanban.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j//logger
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

    @GetMapping("search")
    public String search(Model model,
                         @RequestParam String text,
                         @RequestParam String field){
        if ("name".equals(field)){
            model.addAttribute("todoCards",
                    cardRepository.findAllByStatusAndNameIgnoreCaseContainsOrderById(Status.TODO, text));
            model.addAttribute("inProgressCards",
                    cardRepository.findAllByStatusAndNameIgnoreCaseContainsOrderById(Status.INPROGRESS, text));
            model.addAttribute("doneCards",
                    cardRepository.findAllByStatusAndNameIgnoreCaseContainsOrderById(Status.DONE, text));
        } else if ("description".equals(field)) {
            model.addAttribute("todoCards",
                    cardRepository.findAllByStatusAndDescriptionIgnoreCaseContainsOrderById(Status.TODO, text));
            model.addAttribute("inProgressCards",
                    cardRepository.findAllByStatusAndDescriptionIgnoreCaseContainsOrderById(Status.INPROGRESS, text));
            model.addAttribute("doneCards",
                    cardRepository.findAllByStatusAndDescriptionIgnoreCaseContainsOrderById(Status.DONE, text));
        } else if ("person".equals(field)) {
            model.addAttribute("todoCards",
                    cardRepository.findAllByStatusAndPersonIgnoreCaseContainsOrderById(Status.TODO, text));
            model.addAttribute("inProgressCards",
                    cardRepository.findAllByStatusAndPersonIgnoreCaseContainsOrderById(Status.INPROGRESS, text));
            model.addAttribute("doneCards",
                    cardRepository.findAllByStatusAndPersonIgnoreCaseContainsOrderById(Status.DONE, text));
        }

        return "board";
    }

    @GetMapping("new")
    public String createTask(@ModelAttribute Card card){
        return "new_card";
    }

    @PostMapping("new")
    public String create(@ModelAttribute("card") @Valid Card card, Errors errors){
        if (errors.hasErrors()) {
            return "new_card";
        }
        cardRepository.save(card);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String editCard(@PathVariable("id") long id,
                           Model model){
        model.addAttribute("card", cardRepository.getCardById(id));
        return "edit_card";
    }

    @PatchMapping("{id}/edit")
    public String update(@ModelAttribute("card") @Valid Card card, Errors errors){
        if (errors.hasErrors()) {
            return "edit_card";
        }
        cardRepository.save(card);
        return "redirect:/";
    }

    @Transactional
    @DeleteMapping("{id}/delete")
    public String deletePost(@PathVariable("id") long id){
        cardRepository.deleteCardById(id);
        return "redirect:/";
    }

    @PatchMapping("{id}/move")
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
