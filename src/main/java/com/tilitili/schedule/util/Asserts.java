package com.tilitili.schedule.util;


import com.tilitili.schedule.exception.AssertException;

import java.util.Objects;

public class Asserts {
    public Asserts() {
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new AssertException(message);
        }
    }

    public static void isTrue(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new AssertException(String.format(message, args));
        }
    }

    public static void isTrue(boolean expression, String message, Object arg) {
        if (!expression) {
            throw new AssertException(String.format(message, arg));
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new AssertException(message);
        }
    }

    public static void isFalse(boolean expression, String message, Object... args) {
        if (expression) {
            throw new AssertException(String.format(message, args));
        }
    }

    public static void isFalse(boolean expression, String message, Object arg) {
        if (expression) {
            throw new AssertException(String.format(message, arg));
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new AssertException("数据未获取到");
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new AssertException(message);
        }
    }

    public static void notNull(Object object, String message, Object arg) {
        if (object == null) {
            throw new AssertException(String.format(message, arg));
        }
    }

    public static void notNull(Object object, String message, Object... args) {
        if (object == null) {
            throw new AssertException(String.format(message, args));
        }
    }

    public static void checkNull(Object object, String message) {
        if (object != null) {
            throw new AssertException(message);
        }
    }

    public static void checkEquals(Object a, Object b, String message) {
        if (! Objects.equals(a, b)) {
            throw new AssertException(message);
        }
    }

}

