package com.flong.utils.web;

/**
 * @author   liangjl
 * @Date	 2016年8月6日-下午3:22:39
 * @Version  1.0
 * @CopyRight:liangjl
 * @Description: 攻击过滤父类
 */
public interface Istrip {

	/**
	 * @Description 脚本内容剥离
	 * @param value
	 * 				待处理内容
	 * @return
	 */
	public String strip(String value);
}
