package com.tocket.tokettaskmanager.model;

import jakarta.validation.constraints.NotBlank;

public class Task {
    private int id;
    @NotBlank(message = "Title is empty")
    private String title;
    private String description;
    @NotBlank(message = "Status is empty")
    private String status;

    public void setId(int newId){
        id = newId;
    }

    public int getId(){
        return id;
    }

    public void setTitle(String newTitle){
        title = newTitle;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String newDescription){
        description = newDescription;
    }

    public String getDescription(){
        return description;
    }

    public void setStatus(String newStatus){
        status = newStatus;
    }

    public String getStatus(){
        return status;
    }

    public void setTask(Task newTask){
        id = newTask.getId();
        title = newTask.getTitle();
        description = newTask.getDescription();
        status = newTask.getStatus();
    }
}
