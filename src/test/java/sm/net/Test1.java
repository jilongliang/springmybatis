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
public class Test1 {
	
	
	public static void main(String[] args)throws Exception {
		
		 String url="http://www.iteye.com/news";
		 
		//Document doc=Jsoup.parse(new URL("http://www.iteye.com/news"), 3000);
		 String userAgent="Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0";
	   
		 //抓取10页的数据
	   	for(int i=1;i<=10;i++){
	   		
	   		if(i==1){
	   			Document doc = Jsoup.connect(url).userAgent(userAgent).ignoreContentType(true).timeout(30000).get();
	   			getContent(doc);
	   		}
	   		else{
	   			String newUrl="http://www.iteye.com/news?page="+i;
	   			Document doc = Jsoup.connect(newUrl).userAgent(userAgent).ignoreContentType(true).timeout(30000).get();
	   			getContent(doc);
	   			
	   		}
	   	}
		
	}


	/**
	 * 获取内容
	 * @param doc
	 */
	private static void getContent(Document doc) {
		if(doc!=null){
			Element index_main = doc.getElementById("index_main");
			
			Elements clearfixs = index_main.getElementsByAttributeValue("class", "news clearfix"); 
			
			for(Element clearfix:clearfixs){
				Elements contents = clearfix.getElementsByAttributeValue("class", "content");
				
				for(Element content:contents){
					String  title = content.select("a").get(1).attr("title");
					Elements news_info = content.getElementsByAttributeValue("class", "news_info");
					
					//从span这个html的标签获取内容
					String comment = news_info.select("span").get(0).getElementsByAttributeValue("class","comment").text();//评论数
					String view = news_info.select("span").get(1).getElementsByAttributeValue("class","view").text();//阅读数
					
					String date = news_info.select("span").get(2).getElementsByAttributeValue("class","date").text();//发布时间
					
					comment = removeCN(comment);
					view = removeCN(view);
					
					System.out.println(title+"\t"+comment+"\t"+view+"\t"+date);
					
				}
			}
		}
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
