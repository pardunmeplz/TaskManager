package com.tocket.tokettaskmanager.controller;
import com.tocket.tokettaskmanager.model.Task;
import com.tocket.tokettaskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(){
            return taskService.getAllTasks();
    }

    @PostMapping
    public Task makeNewTask(@RequestBody Task task){
        return taskService.newTask(task);
    }
}
