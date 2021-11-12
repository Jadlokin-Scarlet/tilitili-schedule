package com.tilitili.schedule.aspect;

import com.tilitili.schedule.annotation.Job;
import com.tilitili.schedule.component.Executor;
import com.tilitili.schedule.component.Scheduler;
import com.tilitili.schedule.component.Task;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

@Component
public class JobAspect implements BeanPostProcessor {
    private final Scheduler scheduler;

    @Autowired
    public JobAspect(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        if (AnnotationUtils.isCandidateClass(targetClass, Collections.singletonList(Job.class))) {
            Map<Method, Job> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
                    (MethodIntrospector.MetadataLookup<Job>) method -> AnnotatedElementUtils.findMergedAnnotation(method, Job.class));
            annotatedMethods.forEach((method, Job) -> processJob(Job, method, bean));
        }
        return bean;
    }

    private void processJob(Job job, Method method, Object bean) {
        Method invocableMethod = AopUtils.selectInvocableMethod(method, bean.getClass());
        invocableMethod.setAccessible(true);
        Runnable runnable = new Executor(bean, invocableMethod);
        scheduler.addCronScheduler(runnable, job.name());
    }

}
