package top.liubowen.learning10;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javassist.*;
import redis.clients.jedis.Jedis;
import top.liubowen.learning10.protostuff.Entity01;
import top.liubowen.learning10.protostuff.ProtoStorage;
import top.liubowen.learning10.protostuff.ProtoStorageUtils;
import top.liubowen.learning10.protostuff.utils.ClassScanner;
import top.liubowen.learning10.protostuff.utils.ProtoStorageKeyUtils;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		registerProtostuffEntity();
		// SpringApplication application = new SpringApplication(Application.class);
		// application.run(args);
		// save();
		select();
	}

	public static void save() {
		Entity01 entity01 = new Entity01(1);
		System.err.println(entity01.toString());
		byte[] serializer = ProtoStorageUtils.serializer(entity01);
		System.err.println(serializer);
		Jedis jedis = create();
		byte[] key = ProtoStorageKeyUtils.getKey(Entity01.class);
		jedis.set(key, serializer);

	}

	public static void select() {
		Jedis jedis = create();
		byte[] key = ProtoStorageKeyUtils.getKey(Entity01.class);
		byte[] bytes = jedis.get(key);
		System.err.println(bytes);
		Entity01 deserializer = ProtoStorageUtils.deserializer(bytes, Entity01.class);
		System.err.println(deserializer.toString());
	}

	public static Jedis create() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
		return jedis;
	}

	private static void registerProtostuffEntity() {
		List<String> clazzNames = ClassScanner.scanClassNames("top.liubowen.*", "*.jar");
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(Application.class)); // 主要用于web环境

		for (String clazzName : clazzNames) {
			try {
				CtClass ctClass = pool.get(clazzName);
				boolean b = ctClass.hasAnnotation(ProtoStorage.class);
				if (b) {
					Class clazz = ctClass.toClass();
					ProtoStorageUtils.registerSchema(clazz);
					ProtoStorageKeyUtils.registerKey(clazz);
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (CannotCompileException e) {
				e.printStackTrace();
			}
		}

	}
}
