package org.testing;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
@interface Selector {
    String xpath();
}

interface MainPage {
    @Selector(xpath = ".//*[@test-attr='input_search']")
    String textInputSearch();

    @Selector(xpath = ".//*[@test-attr='button_search']")
    String buttonSearch();
}

public class MethodInterception {
    @org.junit.Test
    public void annotationValue() {
        MainPage mainPage = createPage(MainPage.class);
        assertNotNull(mainPage);
        assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
        assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
    }

    private MainPage createPage(Class<MainPage> clazz) {
        return (MainPage) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class<?>[]{clazz},
                (proxy, method, args) -> {
                    Selector selector = method.getAnnotation(Selector.class);
                    if (selector != null) {
                        return selector.xpath();
                    }
                    return null;
                });
    }
}
