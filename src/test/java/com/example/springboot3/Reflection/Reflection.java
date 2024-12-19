package com.example.springboot3.Reflection;

import java.lang.reflect.Field;

public class Reflection {
	public static void main(String[] args) {
		String className = "com.example.springboot3.Reflection.Const";

		Class<?> constClass = null;

		try {
			constClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		Field field = null;
		try {
			field = constClass.getDeclaredField("HELLO");
			field.setAccessible(true);
			String result = (String)field.get(null);

			System.out.println("the result = " + result);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
