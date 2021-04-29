package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;
    @Mock
    private TrelloBoardDto trelloBoardDto;
    @Disabled
    @Test
    void testMapToBoard(){
        //Given
        List<TrelloList> lists = new ArrayList<>();
        List<TrelloListDto> lists1 = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1","name", lists);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","name1", lists1);
        when(trelloMapper.mapToBoard(trelloBoardDto)).thenReturn(trelloBoard);
        //When
        TrelloBoard expectedBoard = trelloMapper.mapToBoard(trelloBoardDto1);
        //Then
        assertEquals(expectedBoard.getId(), trelloBoardDto.getId());
    }

}
