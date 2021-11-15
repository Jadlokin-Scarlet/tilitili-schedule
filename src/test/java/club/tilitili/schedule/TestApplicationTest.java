package club.tilitili.schedule;

import club.tilitili.schedule.controller.JobController;
import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.PageModel;
import club.tilitili.schedule.entity.TilitiliJob;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTest {
    @Resource
    JobController jobController;

    @Test
    public void test() {
        BaseModel<PageModel<TilitiliJob>> data = jobController.listJob(1, 10);
        System.out.println("?");
    }

}