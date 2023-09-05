package club.tilitili.schedule;

import club.tilitili.schedule.aspect.JobAspect;
import club.tilitili.schedule.component.Scheduler;
import club.tilitili.schedule.config.ScheduleSourceConfig;
import club.tilitili.schedule.controller.JobController;
import club.tilitili.schedule.controller.LogController;
import club.tilitili.schedule.controller.ResourceController;
import club.tilitili.schedule.controller.UserController;
import club.tilitili.schedule.service.ResourceService;
import club.tilitili.schedule.service.TilitiliJobService;
import club.tilitili.schedule.service.TilitiliUserService;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Import({
        ScheduleSourceConfig.class,
        JobAspect.class, Scheduler.class,
        JobController.class, LogController.class, ResourceController.class, UserController.class,
        ResourceService.class, TilitiliJobService.class, TilitiliUserService.class
})
public class TilitiliScheduleAutoConfiguration {
}
