package org.rabbitstack.rabbit.todo.data;

import java.util.Date;

/**
 * Every todo list consists of
 * unbounded number of tasks.
 *
 * @author Nedim Sabic
 */
public class Task {

    private String id;
    private String title;
    private Boolean done = false;

    private Integer order;
    private Date finishedDate;

    private TaskPriority priority = TaskPriority.MEDIUM;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
