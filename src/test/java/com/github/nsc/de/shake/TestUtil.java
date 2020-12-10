package com.github.nsc.de.shake;

import static org.junit.jupiter.api.Assertions.assertSame;

public class TestUtil {

    public static void assertType(Class<?> expected, Object actual) { assertSame(expected, actual.getClass()); }

}
