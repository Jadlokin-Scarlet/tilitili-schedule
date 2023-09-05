package club.tilitili.schedule.controller;

import club.tilitili.schedule.component.Scheduler;
import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.PageModel;
import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.TilitiliJobDTO;
import club.tilitili.schedule.entity.query.TilitiliJobQuery;
import club.tilitili.schedule.mapper.schedule.TilitiliJobMapper;
import club.tilitili.schedule.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/job")
public class JobController extends BaseController {

    private final Scheduler scheduler;
    private final TilitiliJobMapper tilitiliJobMapper;

    @Autowired
    public JobController(Scheduler scheduler, TilitiliJobMapper tilitiliJobMapper) {
        this.scheduler = scheduler;
        this.tilitiliJobMapper = tilitiliJobMapper;
    }

    @RequestMapping("/list")
    @ResponseBody
    public BaseModel<PageModel<TilitiliJobDTO>> listJob(Integer current, Integer pageSize) {
        int total = tilitiliJobMapper.countTilitiliJobByCondition(new TilitiliJobQuery());
        List<TilitiliJob> jobList = tilitiliJobMapper.getTilitiliJobByCondition(new TilitiliJobQuery().setPageNo(current).setPageSize(pageSize));
        List<TilitiliJobDTO> result = new ArrayList<>();
        for (TilitiliJob job : jobList) {
            Scheduler.Task task = scheduler.getTaskByName(job.getName());
            TilitiliJobDTO item = new TilitiliJobDTO();
            item.setId(job.getId());
            item.setCron(job.getCron());
            item.setUpdateTime(job.getUpdateTime());
            item.setCreateTime(job.getCreateTime());
            item.setName(job.getName());
            item.setTitle(job.getTitle());
            item.setStatus(job.getStatus());
            if (task == null) {
                item.setRunStatus("不存在");
            } else if (!task.hasRunning()) {
                item.setRunStatus("未运行");
            } else if (task.isDone()) {
                item.setRunStatus("已中断");
            } else {
                item.setRunStatus("运行中");
            }
            result.add(item);
        }
        return PageModel.of(total, pageSize, current, result);
    }

    @RequestMapping("/start")
    @ResponseBody
    public BaseModel<?> startJob(String name) {
        Asserts.notNull(name, "参数异常");
        tilitiliJobMapper.updateTilitiliJobByName(name, 1);
        Scheduler.Task task = scheduler.getTaskByName(name);
        if (task == null) return BaseModel.success("已启动，但未找到任务。");
        task.start();
        return BaseModel.success();
    }

    @RequestMapping("/stop")
    @ResponseBody
    public BaseModel<?> stopJob(String name) {
        Asserts.notNull(name, "参数异常");
        tilitiliJobMapper.updateTilitiliJobByName(name, -1);
        Scheduler.Task task = scheduler.getTaskByName(name);
        if (task == null) return BaseModel.success("已停止，但未找到任务。");
        task.stop();
        return BaseModel.success();
    }

    @RequestMapping("/run")
    @ResponseBody
    public BaseModel<?> runJob(String name) {
        Asserts.notNull(name, "参数异常");
        Scheduler.Task task = scheduler.getTaskByName(name);
        Asserts.notNull(task, "未找到任务。");
        task.runOne();
        return BaseModel.success();
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseModel<?> addJob(TilitiliJob job) {
        Asserts.notNull(job, "参数异常");
        Asserts.notNull(job.getName(), "参数异常");
        Asserts.notNull(job.getCron(), "参数异常");

        TilitiliJob old = tilitiliJobMapper.getTilitiliJobByName(job.getName());
        Asserts.checkNull(old, "名称重复");

        TilitiliJob addJob = new TilitiliJob();
        addJob.setTitle(job.getTitle());
        addJob.setName(job.getName());
        addJob.setCron(job.getCron());
        addJob.setStatus(0);
        tilitiliJobMapper.addTilitiliJobSelective(addJob);

        Scheduler.Task task = scheduler.getTaskByName(job.getName());
        if (task == null) return BaseModel.success("已添加，但未找到任务。");
        task.supplement(addJob);
        return BaseModel.success();
    }

    @RequestMapping("/edit")
    @ResponseBody
    public BaseModel<?> editJob(TilitiliJob job) {
        Asserts.notNull(job, "参数异常");
        Asserts.notNull(job.getName(), "参数异常");
        Asserts.notNull(job.getCron(), "参数异常");

        TilitiliJob old = tilitiliJobMapper.getTilitiliJobByName(job.getName());
        Asserts.notNull(old, "找不到Job");

        TilitiliJob upd = new TilitiliJob();
        upd.setId(old.getId());
        upd.setTitle(job.getTitle());
        upd.setCron(job.getCron());
        tilitiliJobMapper.updateTilitiliJobSelective(upd);

        Scheduler.Task task = scheduler.getTaskByName(job.getName());
        TilitiliJob newJob = tilitiliJobMapper.getTilitiliJobById(upd.getId());
        if (task == null) return BaseModel.success("已编辑，但未找到任务。");
        task.supplement(newJob);
        if (newJob.getStatus() == 1) {
            tilitiliJobMapper.updateTilitiliJobByName(newJob.getName(), 0);
            task.stop();
        }
        return BaseModel.success();
    }



}
