package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
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
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                System.out.println(trelloListDto.getName()+ " - " + trelloListDto.getId() + " - " + trelloListDto.isClosed());
            });
        });
    }

    @GetMapping("getTrelloBoardWithUsername")
    public void getTrelloBoardWithUsername(){
        List<TrelloBoardDto> trelloBoardsWithUsername = trelloClient.getTrelloBoardWithUsername();
        trelloBoardsWithUsername.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + "" + trelloBoardDto.getName());
        });
    }
    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }
}