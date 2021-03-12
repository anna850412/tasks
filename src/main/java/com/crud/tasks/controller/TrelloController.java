package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

       return trelloClient.getTrelloBoards();
    }

//    @GetMapping("getTrelloBoardWithUsername")
//    public void getTrelloBoardWithUsername(){
//        List<TrelloBoardDto> trelloBoardsWithUsername = trelloClient.getTrelloBoardWithUsername();
//        trelloBoardsWithUsername.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + "" + trelloBoardDto.getName());
//        });
//    }
    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }
//    @PostMapping("createTrelloCardWithBadges")
//    public CreatedTrelloCardWithBadges createdTrelloCardWithBadges(@RequestBody TrelloCardDtoWithBadges trelloCardDtoWithBadges){
//        return trelloClient.createNewCardWithBadges(trelloCardDtoWithBadges);
//    }
}
