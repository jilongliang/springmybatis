package com.flong.utils.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** 
 * 
 * 利用AbstractRoutingDataSource实现动态数据源切换
 * spring2.0以后增加了AbstractRoutingDataSource
 * 自定义一个类继承AbstractRoutingDataSource，重构一个setMethodType支持自定义开头的方法
 * 进行使用数据源的操作数据库
 */
public class DynamicChooseDataSource extends AbstractRoutingDataSource {
	public static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

	/**
	 *  获取数据源名称
	 */
	protected Object determineCurrentLookupKey() {
		return HandleDataSource.getDataSource();
	}

	/**
	 *  设置方法名前缀对应的数据源
	 * @param map
	 */
	public void setMethodType(Map<String, String> map) {
		for (String key : map.keySet()) {
			List<String> v = new ArrayList<String>();
			String[] types = map.get(key).split(",");
			for (String type : types) {
				if (StringUtils.isNotBlank(type)) {
					v.add(type);
				}
			}
			METHODTYPE.put(key, v);
		}
	}
}