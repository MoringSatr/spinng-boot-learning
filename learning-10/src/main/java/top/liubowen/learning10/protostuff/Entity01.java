package top.liubowen.learning10.protostuff;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.protostuff.Tag;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: liubowen
 * @date: 2018/12/7 下午3:47
 * @description:
 */
@ProtoStorage("entity01")
@Data
public class Entity01 {

	@Tag(1)
	private int anInt;
	@Tag(2)
	private long aLong;
	@Tag(3)
	private double aDouble;
	@Tag(4)
	private float aFloat;
	@Tag(5)
	private String aString;
	@Tag(6)
	private boolean aBoolean;
	@Tag(7)
	private BigDecimal bigDecimal;
	@Tag(8)
	private int[] ints;
	@Tag(9)
	private long[] longs;
	@Tag(10)
	private double[] doubles;
	@Tag(11)
	private float[] floats;
	@Tag(12)
	private boolean[] booleans;
	@Tag(13)
	private List<Integer> integerList;
	@Tag(14)
	private List<Long> longList;
	@Tag(15)
	private List<Double> doubleList;
	@Tag(16)
	private List<Float> floatList;
	@Tag(17)
	private List<String> stringList;
	@Tag(18)
	private List<Boolean> booleanList;
	@Tag(19)
	private Set<Integer> integerSet;
	@Tag(20)
	private Set<Long> longSet;
	@Tag(21)
	private Set<Double> doubleSet;
	@Tag(22)
	private Set<Float> floatSet;
	@Tag(23)
	private Set<String> stringSet;
	@Tag(24)
	private Set<Boolean> booleanSet;
	@Tag(25)
	private Map<String, String> stringStringMap;
	@Tag(26)
	private Map<Integer, Integer> integerIntegerMap;
	@Tag(27)
	private Map<Boolean, Boolean> booleanBooleanMap;
	@Tag(28)
	private Map<Object, Object> objectObjectMap;
	@Tag(29)
	private Set<Boolean> booleanSet2;
	@Tag(30)
	private int[] ints1;
	@Tag(31)
	private long id;

	public Entity01() {
		this.ints1 = new int[] { 1 };
		this.anInt = 1;
		this.aLong = 2;
		this.aDouble = 3.333d;
		this.aFloat = 4.44f;
		this.aString = "5";
		this.aBoolean = false;
		this.bigDecimal = new BigDecimal("6");
		this.ints = new int[0];
		this.longs = new long[0];
		this.doubles = new double[0];
		this.floats = new float[0];
		this.booleans = new boolean[0];
		this.integerList = Lists.newArrayList();
		this.longList = Lists.newArrayList();
		this.doubleList = Lists.newArrayList();
		this.floatList = Lists.newArrayList();
		this.stringList = Lists.newArrayList();
		this.booleanList = Lists.newArrayList();
		this.integerSet = Sets.newHashSet();
		this.longSet = Sets.newHashSet();
		this.doubleSet = Sets.newHashSet();
		this.floatSet = Sets.newHashSet();
		this.stringSet = Sets.newHashSet();
		this.booleanSet = Sets.newHashSet();
		this.stringStringMap = Maps.newHashMap();
		this.integerIntegerMap = Maps.newHashMap();
		this.booleanBooleanMap = Maps.newHashMap();
		this.objectObjectMap = Maps.newHashMap();

		this.booleanSet2 = Sets.newHashSet();
	}

	public Entity01(long id) {
		this.id = id;
		this.ints1 = new int[] { 1 };
		this.anInt = 1;
		this.aLong = 2;
		this.aDouble = 3.333d;
		this.aFloat = 4.44f;
		this.aString = "5";
		this.aBoolean = false;
		this.bigDecimal = new BigDecimal("6");
		this.ints = new int[0];
		this.longs = new long[0];
		this.doubles = new double[0];
		this.floats = new float[0];
		this.booleans = new boolean[0];
		this.integerList = Lists.newArrayList();
		this.longList = Lists.newArrayList();
		this.doubleList = Lists.newArrayList();
		this.floatList = Lists.newArrayList();
		this.stringList = Lists.newArrayList();
		this.booleanList = Lists.newArrayList();
		this.integerSet = Sets.newHashSet();
		this.longSet = Sets.newHashSet();
		this.doubleSet = Sets.newHashSet();
		this.floatSet = Sets.newHashSet();
		this.stringSet = Sets.newHashSet();
		this.booleanSet = Sets.newHashSet();
		this.stringStringMap = Maps.newHashMap();
		this.integerIntegerMap = Maps.newHashMap();
		this.booleanBooleanMap = Maps.newHashMap();
		this.objectObjectMap = Maps.newHashMap();

		this.booleanSet2 = Sets.newHashSet();
	}
}
