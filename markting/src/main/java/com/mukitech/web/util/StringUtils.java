package com.mukitech.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 字符串工具类
 * 
 * @author sky
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/** 空字符串 */
	private static final String NULLSTR = "";

	/** 下划线 */
	private static final char SEPARATOR = '_';

	/**
	 * 获取参数不为空值
	 * 
	 * @param value
	 *            defaultValue 要判断的value
	 * @return value 返回值
	 */
	public static <T> T nvl(T value, T defaultValue) {
		return value != null ? value : defaultValue;
	}

	/**
	 * * 判断一个Collection是否为空， 包含List，Set，Queue
	 * 
	 * @param coll
	 *            要判断的Collection
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return isNull(coll) || coll.isEmpty();
	}

	/**
	 * * 判断一个Collection是否非空，包含List，Set，Queue
	 * 
	 * @param coll
	 *            要判断的Collection
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return !isEmpty(coll);
	}

	/**
	 * * 判断一个对象数组是否为空
	 * 
	 * @param objects
	 *            要判断的对象数组
	 ** @return true：为空 false：非空
	 */
	public static boolean isEmpty(Object[] objects) {
		return isNull(objects) || (objects.length == 0);
	}

	/**
	 * * 判断一个对象数组是否非空
	 * 
	 * @param objects
	 *            要判断的对象数组
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Object[] objects) {
		return !isEmpty(objects);
	}

	/**
	 * * 判断一个Map是否为空
	 * 
	 * @param map
	 *            要判断的Map
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	/**
	 * * 判断一个Map是否为空
	 * 
	 * @param map
	 *            要判断的Map
	 * @return true：非空 false：空
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	/**
	 * * 判断一个字符串是否为空串
	 * 
	 * @param str
	 *            String
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(String str) {
		return isNull(str) || NULLSTR.equals(str.trim());
	}

	/**
	 * * 判断一个字符串是否为非空串
	 * 
	 * @param str
	 *            String
	 * @return true：非空串 false：空串
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * * 判断一个对象是否为空
	 * 
	 * @param object
	 *            Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * * 判断一个对象是否非空
	 * 
	 * @param object
	 *            Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * * 判断一个对象是否是数组类型（Java基本型别的数组）
	 * 
	 * @param object
	 *            对象
	 * @return true：是数组 false：不是数组
	 */
	public static boolean isArray(Object object) {
		return isNotNull(object) && object.getClass().isArray();
	}

	/**
	 * 去空格
	 */
	public static String trim(String str) {
		return (str == null ? "" : str.trim());
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            开始
	 * @return 结果
	 */
	public static String substring(final String str, int start) {
		if (str == null) {
			return NULLSTR;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return NULLSTR;
		}

		return str.substring(start);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            开始
	 * @param end
	 *            结束
	 * @return 结果
	 */
	public static String substring(final String str, int start, int end) {
		if (str == null) {
			return NULLSTR;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return NULLSTR;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
	 * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
	 * 例：<br>
	 * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
	 * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
	 * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
	 * 
	 * @param template
	 *            文本模板，被替换的部分用 {} 表示
	 * @param params
	 *            参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... params) {
		if (isEmpty(params) || isEmpty(template)) {
			return template;
		}
		return StrFormatter.format(template, params);
	}

	/**
	 * 下划线转驼峰命名
	 */
	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	/**
	 * 是否包含字符串
	 * 
	 * @param str
	 *            验证字符串
	 * @param strs
	 *            字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs) {
		if (str != null && strs != null) {
			for (String s : strs) {
				if (str.equalsIgnoreCase(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。
	 * 例如：HELLO_WORLD->HelloWorld
	 * 
	 * @param name
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String convertToCamelCase(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母大写
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String[] camels = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1).toLowerCase());
		}
		return result.toString();
	}

	public static Date parseServerTime(String serverTime, String format) {
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		Date date = null;
		try {
			date = sdf.parse(serverTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 转成 yyyy-MM-dd
	 * 
	 * @param str
	 *            原来日期格式 05-十一月-2018
	 * @return 2018-11-05
	 */
	public static String transFormat1(String str) {
		String year = format1GetYear(str);
		String month = format1GetMonth(str);
		String day = format1GetDay(str);

		StringBuffer sb = new StringBuffer(year);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(day);
		String str1 = sb.toString();
		return str1;

	}

	public static String transFormat2(String str) {
		String year = format1GetYear(str);
		String month = format1GetMonth(str);
		String day = format1GetDay(str);

		StringBuffer sb = new StringBuffer(year);
		sb.append(month);
		sb.append(day);
		String str1 = sb.toString();
		return str1;
	}

	public static String format1GetDay(String date) {
		int num = date.indexOf("-");
		String str = substring(date, 0, num);
		return str;
	}

	/**
	 * @param str
	 *            "23-十一月-2018" 拿出 十一月
	 * @return
	 */
	public static String format1GetMonth(String str) {
		int start = str.indexOf("-");
		int end = str.lastIndexOf("-");
		String str1 = StringUtils.substring(str, start + 1, end);

		return transfer(str1);
	}

	public static String format1GetYear(String date) {
		int num = date.lastIndexOf("-");
		String str = date.substring(num + 1);
		return str;
	}

	/**
	 * 我的垃圾代码
	 * 
	 * @param str
	 * @return
	 */
	public static String transfer(String str) {
		String tstr = "";
		if (str.equals("一月")) {
			tstr = "01";
		}
		if (str.equals("二月")) {
			tstr = "02";
		}
		if (str.equals("三月")) {
			tstr = "03";
		}
		if (str.equals("四月")) {
			tstr = "04";
		}
		if (str.equals("五月")) {
			tstr = "05";
		}
		if (str.equals("六月")) {
			tstr = "06";
		}
		if (str.equals("七月")) {
			tstr = "07";
		}
		if (str.equals("八月")) {
			tstr = "08";
		}
		if (str.equals("九月")) {
			tstr = "09";
		}
		if (str.equals("十月")) {
			tstr = "10";
		}
		if (str.equals("十一月")) {
			tstr = "11";
		}
		if (str.equals("十二月")) {
			tstr = "12";
		}
		return tstr;
	}

	public static String appendSerialnum(String str) {
		StringBuffer sb = new StringBuffer(str);
		StringBuffer sb1 = sb.append(random().toString());
		return sb1.toString();
	}

	public static Integer random() {
		int i = (int) (Math.random() * 900) + 100;
		return i;
	}
}