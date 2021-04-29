package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTestSuite {
    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloMapper trelloMapper;
    @Mock
    private TrelloValidator trelloValidator;

    @Test
    void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = List.of(
                new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = List.of(
                new TrelloBoardDto("1", "test", trelloLists));
        List<TrelloList> mappedTrelloList = List.of(
                new TrelloList("1", "test_list", false));
        List<TrelloBoard> mappedTrelloBoards = List.of(
                new TrelloBoard("1", "test", mappedTrelloList));
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertThat(trelloBoardDtos).isNotNull();
        assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertThat(trelloBoardDto.getId()).isEqualTo("1");
            assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(
                    trelloListDto -> {
                        assertThat(trelloListDto.getId()).isEqualTo("1");
                        assertThat(trelloListDto.getName()).isEqualTo("test_list");
                        assertThat(trelloListDto.isClosed()).isFalse();
                    }
            );
        });

    }

    @Test
    void testCreateCard() {
        //Given
        TrelloDto trello = new TrelloDto("board", "card");
        AttachmentByTypeDto attachmentByType = new AttachmentByTypeDto(trello);
        BadgesDto badges = new BadgesDto("votes", attachmentByType);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1", "name", "url", badges);
        TrelloCardDto trelloCardDto = new TrelloCardDto("nameDto", "descriptionDto", "positionDto", "listIdDto");
        TrelloCard trelloCard = new TrelloCard("name", "description", "position", "listId");
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
//        when(trelloValidator.validateCard(trelloCard)).thenReturn(any());
        //When
        CreatedTrelloCardDto card = trelloFacade.createCard(trelloCardDto);
        //Then
        assertThat(card).isNotNull();
        assertEquals("name", card.getName());
        assertEquals("url", card.getShortUrl());
        assertEquals(badges, card.getBadges());

    }
}
