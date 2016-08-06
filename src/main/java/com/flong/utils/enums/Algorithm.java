package com.flong.utils.enums;

/**
 * @author   liangjl
 * @Date	 2016年8月6日-下午4:00:27
 * @Version  1.0
 * @CopyRight:liangjl
 * @Description:算法类型枚举类
 */

public enum Algorithm {
	MD5("MD5", "md5 encrypt"),
	SHA("SHA", "has encrypt"),
	AES("AES", "aes encrypt");

	/** 主键 */
	private final String key;

	/** 描述 */
	private final String desc;


	Algorithm( final String key, final String desc ) {
		this.key = key;
		this.desc = desc;
	}


	public String getKey() {
		return this.key;
	}


	public String getDesc() {
		return this.desc;
	}

}