package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;
    @Mock
    private TrelloBoardDto trelloBoardDto;

    @Test
    void testMapToBoard() {
        //Given
        List<TrelloList> lists = List.of(
                new TrelloList("1", "test_list", false));
        List<TrelloListDto> lists1 = List.of(
                new TrelloListDto("1", "test_list", false));
        TrelloBoard trelloBoard = new TrelloBoard("1", "name", lists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "name2", lists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        trelloBoardList.add(trelloBoard2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "name1", lists1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "name2", lists1);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        trelloBoardDtoList.add(trelloBoardDto2);
//        when(trelloMapper.mapToBoard(trelloBoardDto)).thenReturn(trelloBoard);
//        when(trelloMapper.mapToBoardsDto(trelloBoardList)).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToBoardsDto(List.of(ArgumentMatchers.any(TrelloBoard.class)))).thenReturn(trelloBoardDtoList);
        //When
        TrelloBoard expectedBoard = trelloMapper.mapToBoard(trelloBoardDto1);
        //Then
        verify(trelloMapper, times(1)).mapToBoard(trelloBoardDto1);
        assertEquals(expectedBoard, trelloBoardDto1);
    }

}
