package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    @Autowired
    private final TrelloFacade trelloFacade;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
//    @PostMapping("createTrelloCardWithBadges")
//    public CreatedTrelloCardWithBadges createdTrelloCardWithBadges(@RequestBody TrelloCardDtoWithBadges trelloCardDtoWithBadges){
//        return trelloClient.createNewCardWithBadges(trelloCardDtoWithBadges);
//    }
    //    @GetMapping("getTrelloBoardWithUsername")
//    public void getTrelloBoardWithUsername(){
//        List<TrelloBoardDto> trelloBoardsWithUsername = trelloClient.getTrelloBoardWithUsername();
//        trelloBoardsWithUsername.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + "" + trelloBoardDto.getName());
//        });
//    }
}
