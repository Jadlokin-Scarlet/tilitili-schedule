package club.tilitili.schedule.annotation;

import club.tilitili.schedule.TilitiliScheduleAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TilitiliScheduleAutoConfiguration.class)
public @interface EnableTilitiliJob {
}
