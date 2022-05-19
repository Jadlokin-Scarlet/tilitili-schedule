package club.tilitili.schedule.controller;

import club.tilitili.schedule.dao.TilitiliLogDAO;
import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.PageModel;
import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.TilitiliLog;
import club.tilitili.schedule.entity.query.TilitiliJobQuery;
import club.tilitili.schedule.entity.query.TilitiliLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/log")
public class LogController extends BaseController {
    private final TilitiliLogDAO tilitiliLogDAO;

    @Autowired
    public LogController(TilitiliLogDAO tilitiliLogDAO) {
        this.tilitiliLogDAO = tilitiliLogDAO;
    }

    @RequestMapping("/list")
    @ResponseBody
    public BaseModel<PageModel<TilitiliLog>> listJob(Integer current, Integer pageSize, String name, Boolean success) {
        int total = tilitiliLogDAO.countTilitiliLogByCondition(new TilitiliLogQuery().setName(name).setSuccess(success));
        List<TilitiliLog> logList = tilitiliLogDAO.getTilitiliLogByCondition(new TilitiliLogQuery().setName(name).setSuccess(success).setPageNo(current).setPageSize(pageSize).setSorter("id").setSorted("desc"));
        return PageModel.of(total, pageSize, current, logList);
    }
}
