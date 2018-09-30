package org.luaj.vm2.lib.jse;

import junit.framework.TestCase;
import org.luaj.vm2.LuaValue;

public class OsLibTest extends TestCase {

	LuaValue jse_lib;
	double time;

	public void setUp() {
		jse_lib = JsePlatform.standardGlobals().get("os");
		;
		time = new java.util.Date(2001 - 1900, 7, 23, 14, 55, 02).getTime() / 1000.0;
	}

	public void testJseOsGetenvForEnvVariables() {
		LuaValue JAVA_HOME = LuaValue.valueOf("JAVA_HOME");
		LuaValue jse_user = jse_lib.get("getenv").call(JAVA_HOME);
		// no env in present in every os...
		//assertFalse(jse_user.isnil());
		System.out.println("User: " + jse_user);
	}

	public void testJseOsGetenvForSystemProperties() {
		System.setProperty("test.key.foo", "test.value.bar");
		LuaValue key = LuaValue.valueOf("test.key.foo");
		LuaValue value = LuaValue.valueOf("test.value.bar");
		LuaValue jse_value = jse_lib.get("getenv").call(key);
		assertEquals(value, jse_value);
	}
}
