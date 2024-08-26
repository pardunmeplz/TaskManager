package com.tocket.tokettaskmanager.controller;
import com.tocket.tokettaskmanager.model.Task;
import com.tocket.tokettaskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/all")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "20") int size,
                               @RequestParam(defaultValue = "id") String sort,
                               @RequestParam(defaultValue = "true") boolean asc){
        return taskService.getTasks(sort, asc, size, page);
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable int id){
        return taskService.getTask(id);
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) LocalDate dueDate){
        final String[] blankArray  = {};
        return taskService.getFilteredTasks(
                status == null?
                        blankArray
                        :status.split(","),
                title,dueDate
                );
    }

    @PostMapping
    public Task makeNewTask(@Valid @RequestBody Task task){
        return taskService.newTask(task);
    }

    @DeleteMapping("/{id}")
    public  void removeTask(@PathVariable int id){
        taskService.removeTask(id);
    }

    @PutMapping("/status/{id}")
    public Optional<Task> updateStatus(@RequestBody String status, @PathVariable int id){
        return taskService.updateStatus(id,status);
    }

}
