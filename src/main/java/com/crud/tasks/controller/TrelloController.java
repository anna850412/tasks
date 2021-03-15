package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        trelloClient.getTrelloBoards().stream()
                .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
                .filter(p -> p.getName().contains("Kodilla"))
                .collect(Collectors.toList());

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
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
//    @PostMapping("createTrelloCardWithBadges")
//    public CreatedTrelloCardWithBadges createdTrelloCardWithBadges(@RequestBody TrelloCardDtoWithBadges trelloCardDtoWithBadges){
//        return trelloClient.createNewCardWithBadges(trelloCardDtoWithBadges);
//    }
}
