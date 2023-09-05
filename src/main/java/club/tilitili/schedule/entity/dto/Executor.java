package club.tilitili.schedule.entity.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Executor {
    private final Object bean;
    private final Method method;

    public Executor(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }


    public Boolean run() throws InvocationTargetException, IllegalAccessException {
        Object result = method.invoke(bean);
        if (result == null) return null;
        return (Boolean) result;
    }
}
