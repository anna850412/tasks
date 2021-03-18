package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloService trelloService;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    //    @GetMapping("getTrelloBoardWithUsername")
//    public void getTrelloBoardWithUsername(){
//        List<TrelloBoardDto> trelloBoardsWithUsername = trelloClient.getTrelloBoardWithUsername();
//        trelloBoardsWithUsername.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + "" + trelloBoardDto.getName());
//        });
//    }
    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
//    @PostMapping("createTrelloCardWithBadges")
//    public CreatedTrelloCardWithBadges createdTrelloCardWithBadges(@RequestBody TrelloCardDtoWithBadges trelloCardDtoWithBadges){
//        return trelloClient.createNewCardWithBadges(trelloCardDtoWithBadges);
//    }
}
