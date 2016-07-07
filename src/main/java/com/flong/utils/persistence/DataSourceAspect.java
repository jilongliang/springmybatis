package com.flong.utils.persistence;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 切换数据源(不同方法调用不同数据源)
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
   private static Logger logger =Logger.getLogger(DataSourceAspect.class);

	@Pointcut("execution(* com.flong.*.dao.*.*(..))")
	public void aspect() {
	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		String className = point.getTarget().getClass().getName();
		String method = point.getSignature().getName();
		logger.info(className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
		try {
			for (String key : DynamicChooseDataSource.METHODTYPE.keySet()) {
				for (String type : DynamicChooseDataSource.METHODTYPE.get(key)) {
					if (method.startsWith(type)) {
						HandleDataSource.putDataSource(key);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
