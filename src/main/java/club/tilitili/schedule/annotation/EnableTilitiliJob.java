package club.tilitili.schedule.annotation;

import club.tilitili.schedule.TilitiliScheduleAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAsync
@Import(TilitiliScheduleAutoConfiguration.class)
public @interface EnableTilitiliJob {
}
