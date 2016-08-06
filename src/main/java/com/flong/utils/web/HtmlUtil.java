package com.flong.utils.web;
/**
 * @author   liangjl
 * @Date	 2016年8月6日-下午3:54:43
 * @Version  1.0
 * @CopyRight:liangjl
 * @Description:HTML工具类
 */

public class HtmlUtil {

	public static final String RE_HTML_MARK = "(<.*?>)|(<[\\s]*?/.*?>)|(<.*?/[\\s]*?>)";
	public static final String RE_SCRIPT = "<[\\s]*?script[^>]*?>.*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

	/**
	 * 还原被转义的HTML特殊字符
	 * 
	 * @param htmlStr
	 *            包含转义符的HTML内容
	 * @return 转换后的字符串
	 */
	public static String restoreEscaped(String htmlStr) {
		if (htmlStr == null || "".equals(htmlStr)) {
			return htmlStr;
		}
		return htmlStr.replace("&lt", "<").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&")
				.replace("&quot;", "\"").replace("&#39;", "'").replace("&nbsp;", " ");
	}
}