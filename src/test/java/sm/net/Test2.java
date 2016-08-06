package sm.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 下载jar包在
 * 	<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
	<dependency>
		<groupId>org.jsoup</groupId>
		<artifactId>jsoup</artifactId>
		<version>1.9.2</version>
	</dependency>
 *
 */
public class Test2 {
	
	
	public static void main(String[] args)throws Exception {
		
		 String url="http://mp.weixin.qq.com/s?src=3&timestamp=1468487533&ver=1&signature=-AD*8YPfuJQMiXK2EG2-DrOPyRbFCRMU2*fyj2w9-kTun8v4r4ujY1pLGMvphB1csOSx-k4LzH7RfqiQgsY8lRFDeWOpDD7AfsDSOQsqBLhOUQJHGHWJbjvcOGagDnYIEb3QGgkuRJciXZpMBrAV6MQ8XkSvoGncOKct2ja-34k=";
		 
		//Document doc=Jsoup.parse(new URL("http://www.iteye.com/news"), 3000);
		 String userAgent="Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0";
	   
		 Document doc = Jsoup.connect(url).userAgent(userAgent).ignoreContentType(true).timeout(30000).get();
		 
		 System.out.println(doc);
		
	}

 
	
	
	
	/**
	 * 移除中文,只获取数字
	 * @param 
	 */
	private static String removeCN(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String trim = m.replaceAll("").trim();
		return trim;
	}

}
