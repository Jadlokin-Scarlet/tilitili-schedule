package club.tilitili.schedule.component;

import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.TilitiliLog;
import club.tilitili.schedule.mapper.schedule.TilitiliLogMapper;
import club.tilitili.schedule.util.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private static final Logger log = Logger.getLogger(Task.class);

    private final TilitiliLogMapper tilitiliLogMapper;
    private final ScheduledExecutorService scheduledExecutorService;
    private final Executor executor;

    private CronSequenceGenerator sequenceGenerator;
    private ScheduledFuture<?> future;
    private Date lastRunTime;

    private Long id;
    private String title;
    private String name;
    private String cron;
    private Integer status;

    public Task(Executor executor, TilitiliJob tilitiliJob, ScheduledExecutorService scheduledExecutorService, TilitiliLogMapper tilitiliLogMapper) {
        this.tilitiliLogMapper = tilitiliLogMapper;
        this.scheduledExecutorService = scheduledExecutorService;
        this.executor = executor;

        supplement(tilitiliJob);
    }

    public Task(Executor executor, ScheduledExecutorService scheduledExecutorService, TilitiliLogMapper tilitiliLogMapper) {
        this.tilitiliLogMapper = tilitiliLogMapper;
        this.scheduledExecutorService = scheduledExecutorService;
        this.executor = executor;
    }

    public Task supplement(TilitiliJob tilitiliJob) {
        this.id = tilitiliJob.getId();
        this.title = tilitiliJob.getTitle();
        this.name = tilitiliJob.getName();
        this.cron = tilitiliJob.getCron();
        this.status = tilitiliJob.getStatus();

        this.sequenceGenerator = new CronSequenceGenerator(this.cron, TimeZone.getDefault());

        return this;
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

    @Async
    public void runOne() {
        _run();
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
        _run();
        scheduler();
    }

    public boolean hasRunning() {
        return this.future != null;
    }

    public boolean isDone() {
        return this.future.isDone();
    }

    private void _run() {
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
