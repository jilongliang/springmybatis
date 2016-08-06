package com.flong.utils.web;

/**
 * @author   liangjl
 * @Date	 2016年8月6日-下午3:22:39
 * @Version  1.0
 * @CopyRight:liangjl
 * @Description: SQL注入攻击
 */
public class SqlInjection implements Istrip {

	/**
	 * @Description SQL注入内容剥离
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public String strip(String value) {

		//剥离SQL注入部分代码
		return value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
	}
}
