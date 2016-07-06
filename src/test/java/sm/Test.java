package sm;

import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.flong.utils.HttpsNetUtils;

public class Test {
	public static void main(String[] args) throws Exception{
		
		HttpsNetUtils.trustEveryoneSSL();
		
		for (int i = 1; i <=42; i++) {
			String url="https://sn.122.gov.cn/publicitypage?size="+i+"&page=7&tjyf=201601&fzjg=%E9%99%95A&fwdmgl=6003";
			
			Document doc=Jsoup.parse(new URL(url), 3000);
		 
			 //处理从页面的class=table table-stirped table-hover样式下面的li标签  
            Elements trs=doc.getElementsByAttributeValue("class", "table table-stirped table-hover").select("tr"); 
            for(Element tr:trs){  
            	 String rq = tr.select("td:eq(0)").text();//日期
            	 String kcmc = tr.select("td:eq(1)").text();//考场名称
            	 System.out.println(kcmc);
            }
		}
	}
	
   
}
