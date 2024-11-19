package org.testing;


import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class TestMethodInfoListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestMethodInfo.class)) {
            TestMethodInfo testInfo = method.getAnnotation(TestMethodInfo.class);
            System.out.println("Test Method: " + method.getName());
            System.out.println("Priority: " + testInfo.priority());
            System.out.println("Author: " + testInfo.author());
            System.out.println("Last Modified: " + testInfo.lastModified());
        }
    }

    // Другие методы интерфейса ITestListener можно оставить пустыми или реализовать по необходимости
}