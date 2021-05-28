package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "Title", "content");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, mappedTask.getId());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "TitleTask", "ContentTask");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals("TitleTask", mappedTaskDto.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "TitleTask", "ContentTask");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> mappedListOfTaskDto = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1, mappedListOfTaskDto.size());
    }

}
