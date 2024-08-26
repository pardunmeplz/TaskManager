package com.tocket.tokettaskmanager.service;

import com.tocket.tokettaskmanager.model.Task;
import com.tocket.tokettaskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;


@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTasks(String sort, boolean asc, int size, int page){
        // sort
        List<Task> sorted = taskRepository.findAll().stream().sorted(
                asc?
                        switch (sort.toLowerCase()) {
                            case "title" -> Comparator.comparing(Task::getTitle);
                            case "status" ->Comparator.comparing(Task::getStatus);
                            case "duedate"->Comparator.comparing(Task::getDueDate,
                                    Comparator.nullsLast(Comparator.naturalOrder()));
                            default ->Comparator.comparingLong(Task::getId);
                        }
                        :switch (sort.toLowerCase()) {
                            case "title" -> Comparator.comparing(Task::getTitle).reversed();
                            case "status" ->Comparator.comparing(Task::getStatus).reversed();
                            case "duedate"->Comparator.comparing(Task::getDueDate,
                                    Comparator.nullsLast(Comparator.naturalOrder())).reversed();
                            default ->Comparator.comparingLong(Task::getId).reversed();
                        }
        ).toList();

        // paginate
        return sorted.subList(Math.min(size*page,sorted.size()),Math.min(size*(page+1),sorted.size()));
    }

    public Optional<Task> getTask(long id){
        return taskRepository.findById(id);
    }

    public List<Task> getFilteredTasks(String[] statuses, String title, LocalDate dueDate){
        return taskRepository.findAll().stream().filter(n-> Arrays.asList(statuses).contains(n.getStatus())
                && (title == null || n.getTitle().contains(title))
                && (dueDate ==null || (n.getDueDate() != null && n.getDueDate().equals(dueDate)))
        ).toList();
    }

    public Task updateTask(Task task){
        final Optional<Task> currTask = taskRepository.findById(task.getId());
        if(currTask.isEmpty()) throw new IllegalArgumentException("No task with given Id " + task.getId());
        if(task.getTitle().isBlank() || task.getStatus().isBlank()) throw new IllegalArgumentException("Title and Status are required!");
        taskRepository.save(task);

        return task;
    }

    public Optional<Task> updateStatus(long id, String status){
        final Optional<Task> currTask = taskRepository.findById(id);
        if(currTask.isEmpty()) {
                throw new IllegalArgumentException("No task with given Id " + id);
            }
        if(status.isBlank()) {
            throw new IllegalArgumentException("Status is required!");
        }
        currTask.ifPresent((task)->{
            task.setStatus(status);
            taskRepository.save(task);
        });
        return currTask;
    }

    public void removeTask(long id){
        taskRepository.deleteById(id);
    }

    public Task newTask(Task task) {
        if(task.getTitle().isBlank() || task.getStatus().isBlank()) throw new IllegalArgumentException("Title and Status are required!");
        taskRepository.save(task);
        return task;
    }
}
