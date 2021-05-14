package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import java.util.Optional;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        List<TaskDto> taskList = List.of(new TaskDto(1L, "Title1", "Content1"));
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskList);
//        when(service.getAllTasks()).thenReturn(tasks);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskList);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Content1")));
    }

    @Test
    void testGetTask() throws TaskNotFoundException, Exception {
        //Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(2L, "TitleDto", "ContentDto");
        when(service.getTask(task.getId())).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("TitleDto")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("ContentDto")));
    }

    @Test
    void shouldDeleteTaskById() throws Exception {
        //Given, When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteTask?taskId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteTaskByContent() throws Exception {
        //Given
        Task task = new Task(1L, "Title", "Content");
        TaskDto taskDto = new TaskDto(2L, "TitleDto", "ContentDto");
        when(service.getTask(task.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/task/deleteByContent?description=Content")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindTasksByTitle() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Title", "Content");
        Task task2 = new Task(2L, "Title2", "Content2");
        taskList.add(task);
        taskList.add(task2);

     //   Mockito.when(service.saveTask(task)).thenReturn(task);
        when(service.findTasksByTitle(task.getTitle())).thenReturn(taskList);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/findByTitle?name=Title")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Title")));
    }
    @Test
    public void testCreateTask() throws Exception{
        //Given
        Task createdTask = new Task(1L, "Name", "Description");
        TaskDto createdTaskDto = new TaskDto(1L, "Test Name", "Test Description");
        when(service.saveTask(taskMapper.mapToTask(createdTaskDto))).thenReturn(createdTask);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdTaskDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/task/createTask/")
        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().is(200));
        Mockito.verify(service, times(1)).saveTask(taskMapper.mapToTask(createdTaskDto));
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Title","Content");
        TaskDto updatedTaskDto = new TaskDto(2L, "New", "New one");
        Task savedTask = service.saveTask(task);
        when(taskMapper.mapToTaskDto(savedTask)).thenReturn(updatedTaskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
        .put("/v1/task/updateTask/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("New")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("New one")));
    }
}