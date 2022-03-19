package ru.otus.aop.proxy;

import ru.otus.aop.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

class LoggingProxy {

    private LoggingProxy() {
    }

    static TestLogging createInstanceForClass(Class<? extends TestLogging> clazz) {
        try {
            InvocationHandler handler = new DemoInvocationHandler(clazz.getDeclaredConstructor().newInstance());
            return (TestLogging) Proxy.newProxyInstance(LoggingProxy.class.getClassLoader(),
                    new Class<?>[]{TestLogging.class}, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLogging myClass;
        private Set<Method> logAnnotatedMethods = new HashSet<>();

        DemoInvocationHandler(TestLogging myClass) {
            this.myClass = myClass;
            loadLogAnnotatedMethods();
        }

        private void loadLogAnnotatedMethods() {
            try {
                for (Method currMethod : myClass.getClass().getDeclaredMethods()) {
                    if (currMethod.isAnnotationPresent(Log.class)) {
                        Method interfaceMethod = TestLogging.class.getDeclaredMethod(currMethod.getName(),
                                currMethod.getParameterTypes());
                        logAnnotatedMethods.add(interfaceMethod);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logAnnotatedMethods.contains(method)) {
                System.out.println("LOG invoking method:" + method);
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
