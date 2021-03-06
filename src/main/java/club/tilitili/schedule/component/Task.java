package club.tilitili.schedule.component;

import club.tilitili.schedule.dao.TilitiliLogDAO;
import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.TilitiliLog;
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

    private final TilitiliLogDAO tilitiliLogDAO;
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

    public Task(Executor executor, TilitiliJob tilitiliJob, ScheduledExecutorService scheduledExecutorService, TilitiliLogDAO tilitiliLogDAO) {
        this.tilitiliLogDAO = tilitiliLogDAO;
        this.scheduledExecutorService = scheduledExecutorService;
        this.executor = executor;

        supplement(tilitiliJob);
    }

    public Task(Executor executor, ScheduledExecutorService scheduledExecutorService, TilitiliLogDAO tilitiliLogDAO) {
        this.tilitiliLogDAO = tilitiliLogDAO;
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
        if (this.status == 1) {
            Date lastRunTime = this.sequenceGenerator.next(Optional.ofNullable(this.lastRunTime).orElse(new Date()));
            long nextTime = lastRunTime.getTime() - System.currentTimeMillis();
            this.future = scheduledExecutorService.schedule(this, nextTime, TimeUnit.MILLISECONDS);
        }
        return this;
    }

    @Override
    public void run() {
        _run();
        scheduler();
    }

    private void _run() {
        this.lastRunTime = new Date();
        try {
            Boolean success = Optional.ofNullable(executor.run()).orElse(false);

            TilitiliLog addLog = new TilitiliLog();
            addLog.setName(this.name);
            addLog.setSuccess(success);
            addLog.setRunTime(this.lastRunTime);
            tilitiliLogDAO.addTilitiliLogSelective(addLog);
        } catch (Exception e) {
            log.error("job run error", e);
            TilitiliLog addLog = new TilitiliLog();
            addLog.setName(this.name);
            addLog.setSuccess(false);
            addLog.setRunTime(this.lastRunTime);
            addLog.setFailReason(ExceptionUtils.getStackTrace(e));
            tilitiliLogDAO.addTilitiliLogSelective(addLog);
        }
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
