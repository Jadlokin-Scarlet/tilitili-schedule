package com.tilitili.schedule;

import com.tilitili.schedule.controller.JobController;
import com.tilitili.schedule.entity.BaseModel;
import com.tilitili.schedule.entity.PageModel;
import com.tilitili.schedule.entity.TaskView;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTest {
    @Resource
    JobController jobController;

    @Test
    public void test() {
        BaseModel<PageModel<TaskView>> data = jobController.listJob(1, 10);
        System.out.println("?");
    }

}