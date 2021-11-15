package club.tilitili.schedule.annotation;

import club.tilitili.schedule.config.SchedulingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SchedulingConfiguration.class)
@Documented
public @interface EnableTilitiliJob {
}
