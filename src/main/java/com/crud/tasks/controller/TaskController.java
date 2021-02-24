package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final DbService service;
    private final TaskMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
//    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return mapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "findByTitle")
    public List<Task> findTaskByTitle(@RequestParam String name){
        List<Task> titledTasks = service.findTaskByTitle(name);
       return titledTasks;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return mapper.mapToTaskDto(
                service.getTask(taskId).orElseThrow(TaskNotFoundException::new)
        );
    }
    @DeleteMapping(value = "deleteByContent")
    public void deleteTaskByContent(@RequestParam String description){
        service.deleteTaskByContent(description);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
        service.deleteTaskById(taskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = mapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return mapper.mapToTaskDto(savedTask);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = mapper.mapToTask(taskDto);
        service.saveTask(task);
    }
}


