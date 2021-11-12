package com.tilitili.schedule.component;

import com.tilitili.schedule.entity.TilitiliJob;
import org.apache.log4j.Logger;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private static final Logger log = Logger.getLogger(Task.class);

    private final ScheduledExecutorService scheduledExecutorService;
    private final Runnable runnable;

    private CronSequenceGenerator sequenceGenerator;
    private ScheduledFuture<?> future;
    private Date lastRunTime;

    private Long id;
    private String name;
    private String cron;
    private Integer status;

    public Task(Runnable runnable, TilitiliJob tilitiliJob, ScheduledExecutorService scheduledExecutorService) {
        this.id = tilitiliJob.getId();
        this.name = tilitiliJob.getName();
        this.cron = tilitiliJob.getCron();
        this.status = tilitiliJob.getStatus();

        this.sequenceGenerator = new CronSequenceGenerator(this.cron, TimeZone.getDefault());
        this.scheduledExecutorService = scheduledExecutorService;
        this.runnable = runnable;
    }

    public Task(Runnable runnable, ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.runnable = runnable;
    }

    public Task supplement(TilitiliJob tilitiliJob) {
        this.id = tilitiliJob.getId();
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

    public void runOne() {
        this.lastRunTime = new Date();
        runnable.run();
    }

    public Task scheduler() {
        if (this.status == 1) {
            Date lastRunTime = this.sequenceGenerator.next(Optional.ofNullable(this.lastRunTime).orElse(new Date()));
            long nextTime = lastRunTime.getTime() - System.currentTimeMillis();
            this.future = scheduledExecutorService.schedule(this, nextTime, TimeUnit.MILLISECONDS);
        }
        return this;
    }

    @Override
    public void run() {
        this.lastRunTime = new Date();
        runnable.run();
        scheduler();
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
}
