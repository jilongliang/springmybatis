package sm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flong.utils.HttpsNetUtils;

import sm.entity.TaobaoIpData;

public class IpTaobao {
		public static void main(String[] args) throws Exception{
			
			String url="http://ip.taobao.com/service/getIpInfo2.php?ip=116.214.12.74";
			
			String requestStr = HttpsNetUtils.requestHttpURLConnection(url,"POST");
			
			 JSONObject parseObject = JSON.parseObject(requestStr);
			 
			 //判断是否有这个data的json节点
			 boolean dataFlag = parseObject.containsKey("data");
			 if(dataFlag){
				 String dataStr = parseObject.get("data").toString();
				 //JSON转换成一个Vo实体方便取值,减少get(key);
				 TaobaoIpData taobaoIpData = JSON.parseObject(dataStr, TaobaoIpData.class);
				 
				 
				 String region = taobaoIpData.getRegion();
				 
				 String isp = taobaoIpData.getIsp();
				 String country = taobaoIpData.getCountry();
				 
				 System.out.println(isp+"\t"+country+"\t"+region);
				 
			 }
			
		}
		 
}
