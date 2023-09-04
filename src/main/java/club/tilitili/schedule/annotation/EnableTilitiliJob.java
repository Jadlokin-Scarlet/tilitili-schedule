package club.tilitili.schedule.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAsync
@ComponentScan("club.tilitili.schedule")
public @interface EnableTilitiliJob {
}
