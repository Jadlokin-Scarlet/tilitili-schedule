package club.tilitili.schedule.controller;

import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.PageModel;
import club.tilitili.schedule.entity.TilitiliLog;
import club.tilitili.schedule.entity.query.TilitiliLogQuery;
import club.tilitili.schedule.mapper.mysql.TilitiliLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/log")
public class LogController extends BaseController {
    private final TilitiliLogMapper tilitiliLogMapper;

    @Autowired
    public LogController(TilitiliLogMapper tilitiliLogMapper) {
        this.tilitiliLogMapper = tilitiliLogMapper;
    }

    @RequestMapping("/list")
    @ResponseBody
    public BaseModel<PageModel<TilitiliLog>> listJob(Integer current, Integer pageSize, String name, Boolean success) {
        int total = tilitiliLogMapper.countTilitiliLogByCondition(new TilitiliLogQuery().setName(name).setSuccess(success));
        List<TilitiliLog> logList = tilitiliLogMapper.getTilitiliLogByCondition(new TilitiliLogQuery().setName(name).setSuccess(success).setPageNo(current).setPageSize(pageSize).setSorter("id").setSorted("desc"));
        return PageModel.of(total, pageSize, current, logList);
    }
}
