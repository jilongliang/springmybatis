package com.flong;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.flong.utils.HttpCode;
import com.flong.utils.HttpUtil;
import com.flong.utils.exceptions.BusinessException;

/**
 * 控制器支持类
 * @author liangjilong
 * @version 2013-3-23
 */
@SuppressWarnings("all")
public abstract class BaseController {
	

	@Autowired protected HttpServletRequest request;

	@Autowired protected HttpServletResponse response;

	@Autowired protected HttpSession session;

	@Autowired protected ServletContext application;
	
	//Logger logger = Logger.getLogger(T
	////redirect和RedirectView一样
	public static final String Redirect="redirect:";
	/**
	 * 验证Bean实例对象
	 */
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try {
					setValue(DateUtils.parseDate(text));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	 
	/************
	 * 打印JSON
	 * @param o
	 * @param response
	 */
	public void writeJson(Object o, HttpServletResponse response) {
		writeJson(JSON.toJSONString(o), response);
	}
	/************
	 * 打印JSON
	 * @param o
	 * @param response
	 */
	public void writeJson(String o, HttpServletResponse response) {
		try {
			response.getWriter().write(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/************
	 * 打印JSON
	 * @param o
	 * @param response
	 */
	  public static void writeJSON(Object json, HttpServletResponse response)
	  {
		try {
			response.setContentType("text/json;charset=utf-8");
			byte[] jsonBytes = json.toString().getBytes("utf-8");

			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
		} finally {
			try {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (IOException e) {
			}
		}
	}
	
	  
	/** 设置成功响应代码 */
	protected ModelMap setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	/** 设置成功响应代码 */
	protected ModelMap setSuccessModelMap(ModelMap modelMap, Object data) {
		return setModelMap(modelMap, HttpCode.OK, data);
	}

	/** 设置相应代码 */
	protected ModelMap setModelMap(ModelMap modelMap, HttpCode code) {
		return setModelMap(modelMap, code, null);
	}

	/** 设置相应代码 */
	protected ModelMap setModelMap(ModelMap modelMap, HttpCode code, Object data) {
		if (data != null) {
			modelMap.put("data", data);
		}
		modelMap.put("httpCode", code.value());
		modelMap.put("msg", code.msg());
		return modelMap;
	}

	/** 异常处理 */
	@ExceptionHandler(RuntimeException.class)
	public void exceptionHandler(HttpServletResponse response, Exception ex) throws Exception {
		// logger.error(Constants.Exception_Head, ex);
		ModelMap modelMap = new ModelMap();
		if (ex instanceof IllegalArgumentException) {
			if (StringUtils.isNotBlank(ex.getMessage())) {
				modelMap.put("httpCode", HttpCode.BAD_REQUEST.value());
				modelMap.put("msg", ex.getMessage());
			} else {
				setModelMap(modelMap, HttpCode.BAD_REQUEST);
			}
		} else if (ex instanceof BusinessException) {
			if (StringUtils.isNotBlank(ex.getMessage())) {
				modelMap.put("httpCode", HttpCode.CONFLICT.value());
				modelMap.put("msg", ex.getMessage());
			} else {
				setModelMap(modelMap, HttpCode.CONFLICT);
			}
		} else {
			setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
		}
		response.setContentType("application/json;charset=UTF-8");
		byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
		response.getOutputStream().write(bytes);
	}
	
	
	
	/**
	 * 是否为 post 请求
	 */
	protected boolean isPost() {
		return HttpUtil.isPost(request);
	}


	/**
	 * 是否为 get 请求
	 */
	protected boolean isGet() {
		return HttpUtil.isGet(request);
	}

}
