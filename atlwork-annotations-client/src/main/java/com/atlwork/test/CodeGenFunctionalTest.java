package com.atlwork.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import junit.framework.TestCase;

public class CodeGenFunctionalTest extends TestCase {

	public void testGeneratedJson() throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException {
		Order order = new Order("toy", 10);
		// TODO: figure out how to access the generated type adapter
		Field f = ClassLoader.class.getDeclaredField("classes");
		f.setAccessible(true);

		@SuppressWarnings("unchecked")
		Vector<Class<?>> classes = (Vector<Class<?>>) f.get(this.getClass().getClassLoader());

		List<Class<?>> listClazz = new ArrayList<Class<?>>(classes);
		for (Class<?> clazz : listClazz) {
			System.out.println(clazz.getCanonicalName());
		}
	}
}
