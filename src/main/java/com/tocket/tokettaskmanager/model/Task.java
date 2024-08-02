package com.tocket.tokettaskmanager.model;

public class Task {
    private int id;
    private String title;
    private String description;
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
}
