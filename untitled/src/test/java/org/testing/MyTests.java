package org.testing;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MyTests {


    @Test
    @TestMethodInfo(priority = Priority.Critical, author = "Test user", lastModified = "20.08.2019")
    public void annotation() {
        assertEquals(1, 1);
    }
}