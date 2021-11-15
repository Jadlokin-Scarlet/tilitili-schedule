package club.tilitili.schedule.entity;

import club.tilitili.schedule.component.Task;

public class TaskView {
    private Long id;
    private String name;
    private String cron;
    private Integer status;

    public TaskView() {}

    public TaskView(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.cron = task.getCron();
        this.status = task.getStatus();
    }

    public TaskView(TilitiliJob tilitiliJob) {
        this.id = tilitiliJob.getId();
        this.name = tilitiliJob.getName();
        this.cron = tilitiliJob.getCron();
        this.status = tilitiliJob.getStatus();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public TaskView setId(Long id) {
        this.id = id;
        return this;
    }
}
