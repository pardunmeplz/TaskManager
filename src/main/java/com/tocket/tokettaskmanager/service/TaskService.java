package com.tocket.tokettaskmanager.service;

import com.tocket.tokettaskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int idCounter =1;

    public List<Task> getAllTasks(){
        return tasks;
    }

    public Task getTask(int id){
        return tasks.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }

    public List<Task> getFilteredTasks(String[] statuses, String title){
        return tasks.stream().filter(n-> Arrays.asList(statuses).contains(n.getStatus()) && (title == null || n.getTitle().contains(title))).toList();
    }

    public Task updateTask(Task task){
        final Task currTask = tasks.stream().filter(n -> n.getId() == task.getId()).findFirst().orElse(null);
        if(currTask == null)return task;
        currTask.setTask(task);
        return task;
    }

    public void removeTask(int id){
        tasks.removeIf(n->n.getId() == id);
    }

    public Task newTask(Task task) {
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }
}
