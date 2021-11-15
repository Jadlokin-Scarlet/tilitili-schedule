package club.tilitili.schedule.component;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class Executor implements Runnable {
    private static final Logger log = Logger.getLogger(Executor.class);

    private final Object bean;
    private final Method method;

    public Executor(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    @Override
    public void run() {
        try {
            method.invoke(bean);
        } catch (Exception e) {
            log.error("Job 执行异常", e);
        }
    }
}
