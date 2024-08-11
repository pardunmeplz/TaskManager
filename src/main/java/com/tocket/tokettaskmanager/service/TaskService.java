package com.tocket.tokettaskmanager.service;

import com.tocket.tokettaskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int idCounter =1;

    public List<Task> getAllTasks(){
        return tasks;
    }

    public List<Task> getTasks(String sort, boolean asc, int size, int page){
        // sort
        List<Task> sorted = tasks.stream().sorted(
                asc?
                        switch (sort.toLowerCase()) {
                            case "title" -> Comparator.comparing(Task::getTitle);
                            case "status" ->Comparator.comparing(Task::getStatus);
                            default ->Comparator.comparingInt(Task::getId);
                        }
                        :switch (sort.toLowerCase()) {
                            case "title" -> Comparator.comparing(Task::getTitle).reversed();
                            case "status" ->Comparator.comparing(Task::getStatus).reversed();
                            default ->Comparator.comparingInt(Task::getId).reversed();
                        }
        ).toList();

        // paginate
        return sorted.subList(Math.min(size*page,sorted.size()),Math.min(size*(page+1),sorted.size()));
    }

    public Task getTask(int id){
        return tasks.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }

    public List<Task> getFilteredTasks(String[] statuses, String title){
        return tasks.stream().filter(n-> Arrays.asList(statuses).contains(n.getStatus()) && (title == null || n.getTitle().contains(title))).toList();
    }

    public Task updateTask(Task task){
        final Task currTask = tasks.stream().filter(n -> n.getId() == task.getId()).findFirst().orElse(null);
        if(currTask == null) throw new IllegalArgumentException("No task with given Id " + task.getId());
        if(task.getTitle().isBlank() || task.getStatus().isBlank()) throw new IllegalArgumentException("Title and Status are required!");
        currTask.setTask(task);
        return task;
    }

    public Task updateStatus(int id, String status){
        final Task currTask = tasks.stream().filter(i->i.getId() == id).findFirst().orElse(null);
        if(currTask == null) throw new IllegalArgumentException("No task with given Id " + id);
        if(status.isBlank()) throw new IllegalArgumentException("Status is required!");
        currTask.setStatus(status);
        return currTask;
    }

    public void removeTask(int id){
        tasks.removeIf(n->n.getId() == id);
    }

    public Task newTask(Task task) {
        if(task.getTitle().isBlank() || task.getStatus().isBlank()) throw new IllegalArgumentException("Title and Status are required!");
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }
}
