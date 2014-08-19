package com.eudemon.taurus.app.cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoUtil {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object toObject(byte[] value,Class cls) {
		if (null == value) {
			return null;
		}
		Input input = new Input(new ByteArrayInputStream(value));
		Kryo kryo = new Kryo();
		Object t = kryo.readObject(input, cls);
		return t;
	}
	
	public static byte[] toByteArray(Object t) {
		if (null == t) {
			return new byte[] {};
		}
		Output output = new Output(new ByteArrayOutputStream());
		Kryo kryo = new Kryo();
		kryo.writeObject(output, t);
		byte[] value = output.toBytes();

		return value;
	}
}
