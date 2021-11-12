package com.tilitili.schedule.component;

import com.tilitili.schedule.dao.TilitiliJobDAO;
import com.tilitili.schedule.entity.TilitiliJob;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class Scheduler {
    private static final Logger log = Logger.getLogger(Scheduler.class);

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private final TilitiliJobDAO tilitiliJobDAO;
    private final Map<String, Task> taskMap = new HashMap<>();

    @Autowired
    public Scheduler(TilitiliJobDAO tilitiliJobDAO) {
        this.tilitiliJobDAO = tilitiliJobDAO;
    }

    public void addCronScheduler(Runnable runnable, String name) {
        TilitiliJob tilitiliJob = tilitiliJobDAO.getTilitiliJobByName(name);
        if (tilitiliJob == null) {
            taskMap.put(name, new Task(runnable, scheduledExecutorService));
            log.info(String.format("Job[%s]未登记，启动失败", name));
        } else {
            taskMap.put(name, new Task(runnable, tilitiliJob, scheduledExecutorService).scheduler());
        }
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

    public Task getTaskByName(String name) {
        return taskMap.get(name);
    }

}
