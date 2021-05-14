package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void testGetAllTasks() throws Exception {
        //Given
        List<TaskDto> taskList = List.of(new TaskDto(1L, "Title1","Content1"));
        List<Task> tasks = List.of(new Task(2L,"Title2","Content2")
//                ,new Task(1L, "Title1","Content1")
        );

        when(service.getAllTasks()).thenReturn(tasks);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskList);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("2L")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Title2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Content2")));

    }
    @Test
    void testFindTaskByTitle() throws Exception {
    //Given

    //When&Then

    }
    @Test
    void testGetTask() throws TaskNotFoundException {
    //Given

    //When&Then
    }

}