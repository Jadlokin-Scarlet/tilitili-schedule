package com.tilitili.schedule.controller;

import com.tilitili.schedule.component.Scheduler;
import com.tilitili.schedule.component.Task;
import com.tilitili.schedule.dao.TilitiliJobDAO;
import com.tilitili.schedule.entity.BaseModel;
import com.tilitili.schedule.entity.PageModel;
import com.tilitili.schedule.entity.TaskView;
import com.tilitili.schedule.entity.TilitiliJob;
import com.tilitili.schedule.entity.query.TilitiliJobQuery;
import com.tilitili.schedule.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class JobController extends BaseController {

    private final Scheduler scheduler;
    private final TilitiliJobDAO tilitiliJobDAO;

    @Autowired
    public JobController(Scheduler scheduler, TilitiliJobDAO tilitiliJobDAO) {
        this.scheduler = scheduler;
        this.tilitiliJobDAO = tilitiliJobDAO;
    }

    @RequestMapping("/job/list")
    @ResponseBody
    public BaseModel<PageModel<TaskView>> listJob(Integer current, Integer pageSize) {
        int total = tilitiliJobDAO.countTilitiliJobByCondition(new TilitiliJobQuery());
        List<TilitiliJob> jobList = tilitiliJobDAO.getTilitiliJobByCondition(new TilitiliJobQuery().setPageNo(current).setPageSize(pageSize));

        List<TaskView> taskViewList = jobList.stream().map(TaskView::new).collect(Collectors.toList());
        return PageModel.of(total, pageSize, current, taskViewList);
    }

    @RequestMapping("/job/start")
    @ResponseBody
    public BaseModel<?> startJob(String name) {
        Asserts.notNull(name, "参数异常");
        tilitiliJobDAO.updateTilitiliJobByName(name, 1);
        Task task = scheduler.getTaskByName(name);
        if (task == null) return BaseModel.success("已启动，但未找到可执行的任务。");
        task.start();
        return BaseModel.success();
    }

    @RequestMapping("/job/stop")
    @ResponseBody
    public BaseModel<?> stopJob(String name) {
        Asserts.notNull(name, "参数异常");
        tilitiliJobDAO.updateTilitiliJobByName(name, -1);
        Task task = scheduler.getTaskByName(name);
        if (task == null) return BaseModel.success("已停止，但未找到可停止的任务。");
        task.stop();
        return BaseModel.success();
    }

    @RequestMapping("/job/run")
    @ResponseBody
    public BaseModel<?> runJob(String name) {
        Asserts.notNull(name, "参数异常");
        Task task = scheduler.getTaskByName(name);
        Asserts.notNull(task, "未找到任务。");
        task.runOne();
        return BaseModel.success();
    }

    @RequestMapping("/job/add")
    @ResponseBody
    public BaseModel<?> addJob(TaskView taskView) {
        Asserts.notNull(taskView, "参数异常");
        Asserts.notNull(taskView.getName(), "参数异常");
        Asserts.notNull(taskView.getCron(), "参数异常");

        TilitiliJob old = tilitiliJobDAO.getTilitiliJobByName(taskView.getName());
        Asserts.checkNull(old, "名称重复");

        TilitiliJob tilitiliJob = new TilitiliJob();
        tilitiliJob.setName(taskView.getName());
        tilitiliJob.setCron(taskView.getCron());
        tilitiliJob.setStatus(0);
        tilitiliJobDAO.addTilitiliJobSelective(tilitiliJob);

        Task task = scheduler.getTaskByName(taskView.getName());
        if (task == null) return BaseModel.success("已添加，但未找到可执行的任务。");
        task.supplement(tilitiliJob);
        return BaseModel.success();
    }



}
