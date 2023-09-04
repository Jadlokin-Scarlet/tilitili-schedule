package club.tilitili.schedule;

import club.tilitili.schedule.controller.JobController;
import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.PageModel;
import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.TilitiliJobDTO;
import club.tilitili.schedule.entity.query.TilitiliJobQuery;
import club.tilitili.schedule.mapper.mysql.TilitiliJobMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestApplicationTest {
    @Resource
    JobController jobController;
    @Resource
    TilitiliJobMapper tilitiliJobMapper;

    @Test
    public void test() {
        BaseModel<PageModel<TilitiliJobDTO>> data = jobController.listJob(1, 10);
        System.out.println("?");
    }

    @Test
    public void sumJob() {
        List<TilitiliJob> jobList = tilitiliJobMapper.getTilitiliJobByCondition(new TilitiliJobQuery().setStatus(1));
        List<String> cronList = jobList.stream().map(TilitiliJob::getCron).collect(Collectors.toList());
        this.maxTriggerCount(cronList);
    }

    public Map<Date, Integer> countCronTrigger(List<String> cronList, Date startDate, Date endDate) {
        Map<Date, Integer> triggerCountMap = new HashMap<>();

        for (String cron : cronList) {
            CronSequenceGenerator generator = new CronSequenceGenerator(cron);
            Date nextTriggerTime = startDate;

            do {
                nextTriggerTime = generator.next(nextTriggerTime);
                triggerCountMap.put(nextTriggerTime, triggerCountMap.getOrDefault(nextTriggerTime, 0) + 1);
            } while (nextTriggerTime.before(endDate));
        }

        return triggerCountMap;
    }

    public void maxTriggerCount(List<String> cronExpressions) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.add(Calendar.SECOND, -1);

        Date startTime = calendar.getTime();
        calendar.add(Calendar.SECOND, 1);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endTime = calendar.getTime();
        maxTriggerCount(cronExpressions, startTime, endTime);
    }
    public void maxTriggerCount(List<String> cronExpressions, Date startTime, Date endDate) {
        Map<Date, Integer> cronTriggerMap = countCronTrigger(cronExpressions, startTime, endDate);
        List<Date> cronList = cronTriggerMap.keySet().stream().sorted().collect(Collectors.toList());

        int maxCount = 0;
        int maxI = 0;
        int windowCount = 0;
        for (int i = 0,j = 0; i < cronList.size(); i++) {
            Date theDate = cronList.get(i);
            Date lastDate = cronList.get(j);
            windowCount += cronTriggerMap.get(theDate);
            while (j < i && theDate.getTime() - lastDate.getTime() > 5 * 60 * 1000) {
                windowCount -= cronTriggerMap.get(lastDate);
                lastDate = cronList.get(++j);
            }
            if (windowCount > maxCount) {
                maxCount = windowCount;
                maxI = i;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("一天内随机5分钟任务最多为%s前的5分钟，执行了%d个任务%n", sdf.format(cronList.get(maxI)), maxCount);
    }

}