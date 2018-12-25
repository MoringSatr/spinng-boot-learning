package top.liubowen.learning08.common;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author liubowen
 * @date 2018/5/8 20:51
 * @description
 */
public class Injector {

	private final Class<?> master;
	private final InjectField[] fields;

	public Injector(Class<?> master, InjectField[] fields) {
		this.master = master;
		this.fields = fields;
	}

	public <T> T inject(T bean) {
		try {
			for (InjectField field : fields) {
				field.inject(bean);
			}
			return bean;
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Injector build(Class<?> clazz) {
		return build(clazz, null);
	}

	/**
	 * @param clazz
	 * @param upwardsAnno
	 *                    如果父类也有标注对应的Annotation则不处理父类
	 * @return
	 */
	public static Injector build(Class<?> clazz, Class<? extends Annotation> upwardsAnno) {
		List<InjectField> injectFields = Lists.newArrayList();
		Class<?> temp = clazz;
		do {
			Field[] fields = temp.getDeclaredFields();
			for (Field field : fields) {
				if (!field.isAnnotationPresent(Autowired.class)) {
					continue;
				}
				injectFields.add(new InjectField(field));
			}
			temp = temp.getSuperclass();
		} while ((upwardsAnno == null || !temp.isAnnotationPresent(upwardsAnno)) && !Object.class.equals(temp));
		return new Injector(clazz, injectFields.toArray(new InjectField[0]));
	}

	@Override
	public String toString() {
		return master.toString();
	}

}
