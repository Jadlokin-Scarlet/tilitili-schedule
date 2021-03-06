package club.tilitili.schedule.controller;

import club.tilitili.schedule.component.Scheduler;
import club.tilitili.schedule.component.Task;
import club.tilitili.schedule.dao.TilitiliJobDAO;
import club.tilitili.schedule.entity.query.TilitiliJobQuery;
import club.tilitili.schedule.util.Asserts;
import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.PageModel;
import club.tilitili.schedule.entity.TilitiliJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/job")
public class JobController extends BaseController {

    private final Scheduler scheduler;
    private final TilitiliJobDAO tilitiliJobDAO;

    @Autowired
    public JobController(Scheduler scheduler, TilitiliJobDAO tilitiliJobDAO) {
        this.scheduler = scheduler;
        this.tilitiliJobDAO = tilitiliJobDAO;
    }

    @RequestMapping("/list")
    @ResponseBody
    public BaseModel<PageModel<TilitiliJob>> listJob(Integer current, Integer pageSize) {
        int total = tilitiliJobDAO.countTilitiliJobByCondition(new TilitiliJobQuery());
        List<TilitiliJob> jobList = tilitiliJobDAO.getTilitiliJobByCondition(new TilitiliJobQuery().setPageNo(current).setPageSize(pageSize));
        return PageModel.of(total, pageSize, current, jobList);
    }

    @RequestMapping("/start")
    @ResponseBody
    public BaseModel<?> startJob(String name) {
        Asserts.notNull(name, "参数异常");
        tilitiliJobDAO.updateTilitiliJobByName(name, 1);
        Task task = scheduler.getTaskByName(name);
        if (task == null) return BaseModel.success("已启动，但未找到任务。");
        task.start();
        return BaseModel.success();
    }

    @RequestMapping("/stop")
    @ResponseBody
    public BaseModel<?> stopJob(String name) {
        Asserts.notNull(name, "参数异常");
        tilitiliJobDAO.updateTilitiliJobByName(name, -1);
        Task task = scheduler.getTaskByName(name);
        if (task == null) return BaseModel.success("已停止，但未找到任务。");
        task.stop();
        return BaseModel.success();
    }

    @RequestMapping("/run")
    @ResponseBody
    public BaseModel<?> runJob(String name) {
        Asserts.notNull(name, "参数异常");
        Task task = scheduler.getTaskByName(name);
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

        TilitiliJob old = tilitiliJobDAO.getTilitiliJobByName(job.getName());
        Asserts.checkNull(old, "名称重复");

        TilitiliJob addJob = new TilitiliJob();
        addJob.setTitle(job.getTitle());
        addJob.setName(job.getName());
        addJob.setCron(job.getCron());
        addJob.setStatus(0);
        tilitiliJobDAO.addTilitiliJobSelective(addJob);

        Task task = scheduler.getTaskByName(job.getName());
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

        TilitiliJob old = tilitiliJobDAO.getTilitiliJobByName(job.getName());
        Asserts.notNull(old, "找不到Job");

        TilitiliJob upd = new TilitiliJob();
        upd.setId(old.getId());
        upd.setTitle(job.getTitle());
        upd.setCron(job.getCron());
        tilitiliJobDAO.updateTilitiliJobSelective(upd);

        Task task = scheduler.getTaskByName(job.getName());
        TilitiliJob newJob = tilitiliJobDAO.getTilitiliJobById(upd.getId());
        if (task == null) return BaseModel.success("已编辑，但未找到任务。");
        task.supplement(newJob);
        if (newJob.getStatus() == 1) {
            tilitiliJobDAO.updateTilitiliJobByName(newJob.getName(), 0);
            task.stop();
        }
        return BaseModel.success();
    }



}
