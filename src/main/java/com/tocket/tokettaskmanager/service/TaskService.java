package com.tocket.tokettaskmanager.service;

import com.tocket.tokettaskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int idCounter =1;

    public List<Task> getAllTasks(){
        return tasks;
    }

    public Task newTask(Task task){
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }
}
