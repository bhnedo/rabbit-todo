package org.rabbitstack.rabbit.todo.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <code>Todo</code> domain object mapping
 * of the Mongo document.
 *
 * @author Nedim Sabic
 */
@Document
public class Todo {

    @Id
    private String id;

    @NotNull
    private String name;
    private String description;

    private Date creationDate;

    public Todo() {

    }

    public Todo(String name) {
        this.name = name;
    }

    private List<Task> tasks = new ArrayList<Task>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}

