package ru.otus.aop.proxy;

import ru.otus.aop.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class ProxyHelper {

    private ProxyHelper() {
    }

    static TestLogging createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(ProxyHelper.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLogging myClass;

        DemoInvocationHandler(TestLogging myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method implMethod = myClass.getClass().getDeclaredMethod(method.getName(),
                    method.getParameterTypes());
            if (implMethod.isAnnotationPresent(Log.class)) {
                System.out.println("LOG invoking method:" + implMethod);
                printMethodParams(args);
            }
            return method.invoke(myClass, args);
        }

        private void printMethodParams(Object[] args) {
            if (args == null) {
                System.out.println("LOG no params");
                return;
            }
            System.out.print("LOG params: (");
            for (int i = 0; i < args.length; i++)
            {
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
