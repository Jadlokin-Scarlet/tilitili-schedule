package club.tilitili.schedule.component;

import club.tilitili.schedule.controller.BaseController;
import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.TilitiliLog;
import club.tilitili.schedule.entity.dto.Executor;
import club.tilitili.schedule.exception.AssertException;
import club.tilitili.schedule.mapper.schedule.TilitiliJobMapper;
import club.tilitili.schedule.mapper.schedule.TilitiliLogMapper;
import club.tilitili.schedule.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class Scheduler {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

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
            taskMap.put(name, new Task(executor));
            log.info(String.format("Job[%s]未登记，启动失败", name));
        } else {
            taskMap.put(name, new Task(executor, tilitiliJob).scheduler());
        }
    }

    public Task getTaskByName(String name) {
        return taskMap.get(name);
    }

    public class Task implements Runnable {
        private final Executor executor;

        private CronSequenceGenerator sequenceGenerator;
        private ScheduledFuture<?> future;
        private Date lastRunTime;

        private Long id;
        private String title;
        private String name;
        private String cron;
        private Integer status;

        public Task(Executor executor, TilitiliJob tilitiliJob) {
            this.executor = executor;
            supplement(tilitiliJob);
        }

        public Task(Executor executor) {
            this.executor = executor;
        }

        public void supplement(TilitiliJob tilitiliJob) {
            this.id = tilitiliJob.getId();
            this.title = tilitiliJob.getTitle();
            this.name = tilitiliJob.getName();
            this.cron = tilitiliJob.getCron();
            this.status = tilitiliJob.getStatus();

            this.sequenceGenerator = new CronSequenceGenerator(this.cron, TimeZone.getDefault());
        }

        public void start() {
            if (this.status != 1) {
                this.lastRunTime = null;
                this.status = 1;
                scheduler();
            }
        }

        public void stop() {
            this.status = -1;
            this.future.cancel(true);
        }

        public void runOne() {
            if (this.future.getDelay(TimeUnit.MILLISECONDS) <= 0) {
                throw new AssertException("任务撞车");
            }
            scheduledExecutorService.schedule(this::doRun, 1, TimeUnit.MILLISECONDS);
        }

        public Task scheduler() {
            log.info(String.format("%s start scheduler", name));
            try {
                if (this.status == 1) {
                    Date lastRunTime = this.sequenceGenerator.next(Optional.ofNullable(this.lastRunTime).orElse(new Date()));
                    long nextTime = lastRunTime.getTime() - System.currentTimeMillis();
                    this.future = scheduledExecutorService.schedule(this, nextTime, TimeUnit.MILLISECONDS);
                    log.info(String.format("%s scheduler in %s", name, nextTime));
                } else {
                    log.info(String.format("%s stop scheduler", name));
                }
            } catch (Exception e) {
                log.error("加载任务异常", e);
            }
            return this;
        }

        @Override
        public void run() {
            doRun();
            scheduler();
        }

        public boolean hasRunning() {
            return this.future != null;
        }

        public boolean isDone() {
            return this.future.isDone();
        }

        private void doRun() {
            log.info(String.format("%s start", name));
            this.lastRunTime = new Date();
            try {
                Boolean success = Optional.ofNullable(executor.run()).orElse(false);

                TilitiliLog addLog = new TilitiliLog();
                addLog.setName(this.name);
                addLog.setSuccess(success);
                addLog.setRunTime(this.lastRunTime);
                tilitiliLogMapper.addTilitiliLogSelective(addLog);
            } catch (Exception e) {
                log.error("job run error", e);
                TilitiliLog addLog = new TilitiliLog();
                addLog.setName(this.name);
                addLog.setSuccess(false);
                addLog.setRunTime(this.lastRunTime);
                addLog.setFailReason(ExceptionUtils.getStackTrace(e));
                tilitiliLogMapper.addTilitiliLogSelective(addLog);
            }
            this.lastRunTime = new Date();
            log.info(String.format("%s end", name));
        }

        public TilitiliJob getTilitiliJob() {
            TilitiliJob tilitiliJob = new TilitiliJob();
            tilitiliJob.setId(this.id);
            tilitiliJob.setTitle(this.title);
            tilitiliJob.setName(this.name);
            tilitiliJob.setCron(this.cron);
            tilitiliJob.setStatus(this.status);
            return tilitiliJob;
        }

        public String getName() {
            return name;
        }

        public String getCron() {
            return cron;
        }

        public Integer getStatus() {
            return status;
        }

        public Long getId() {
            return id;
        }

        public Task setId(Long id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Task setTitle(String title) {
            this.title = title;
            return this;
        }
    }

}
