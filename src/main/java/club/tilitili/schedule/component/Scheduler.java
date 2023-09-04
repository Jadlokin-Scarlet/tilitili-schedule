package club.tilitili.schedule.component;

import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.mapper.mysql.TilitiliJobMapper;
import club.tilitili.schedule.mapper.mysql.TilitiliLogMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Component
public class Scheduler {
    private static final Logger log = Logger.getLogger(Scheduler.class);

    private final ScheduledExecutorService scheduledExecutorService;
    private final TilitiliJobMapper tilitiliJobMapper;
    private final TilitiliLogMapper tilitiliLogMapper;
    private final Map<String, Task> taskMap = new HashMap<>();

    @Autowired
    public Scheduler(TilitiliJobMapper tilitiliJobMapper, TilitiliLogMapper tilitiliLogMapper, @Value("${schedule.core-pool-size}") Integer corePoolSize) {
        scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);
        this.tilitiliJobMapper = tilitiliJobMapper;
        this.tilitiliLogMapper = tilitiliLogMapper;
    }

    public void addCronScheduler(Executor executor, String name) {
        TilitiliJob tilitiliJob = tilitiliJobMapper.getTilitiliJobByName(name);
        if (tilitiliJob == null) {
            taskMap.put(name, new Task(executor, scheduledExecutorService, tilitiliLogMapper));
            log.info(String.format("Job[%s]未登记，启动失败", name));
        } else {
            taskMap.put(name, new Task(executor, tilitiliJob, scheduledExecutorService, tilitiliLogMapper).scheduler());
        }
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

    public Task getTaskByName(String name) {
        return taskMap.get(name);
    }

}
