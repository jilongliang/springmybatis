package com.flong.utils.web;
 
/**
 * @author   liangjl
 * @Date	 2016年8月6日-下午3:22:39
 * @Version  1.0
 * @CopyRight:liangjl
 * @Description:  Web防火墙工具类
 */
public class WafHelper {

	/**
	 * @Description 过滤XSS脚本内容
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public static String stripXSS(String value) {
		if (value == null) {
			return null;
		}

		return new XSS().strip(value);
	}

	/**
	 * @Description 过滤SQL注入内容
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public static String stripSqlInjection(String value) {
		if (value == null) {
			return null;
		}

		return new SqlInjection().strip(value);
	}

	/**
	 * @Description 过滤SQL/XSS注入内容
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public static String stripSqlXSS(String value) {
		if (value == null) {
			return null;
		}

		return stripXSS(stripSqlInjection(value));
	}

}