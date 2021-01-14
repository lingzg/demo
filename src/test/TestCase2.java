package test;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import data.HttpUtil;
import model.OaProjectPlan;

public class TestCase2 {

//	@Test
	public void test1(){
		String s="e10adc3949ba59abbe56e057f20f883e";//123456
		System.out.println(new Sha256Hash(s).toHex());//cdf4a007e2b02a0c49fc9b7ccfbb8a10c644f635e1765dcf2a7ab794ddc7edac
		s="21232f297a57a5a743894a0e4a801fc3";//admin
		System.out.println(new Sha256Hash(s).toHex());//465c194afb65670f38322df087f0a9bb225cc257e43eb4ac5a0c98ef5b3173ac
	}
	
	//@Test
	public void test2(){
		String[] arr = "ab|cd".split("\\|");
		System.out.println(Arrays.toString(arr));
		arr = "ab&cd".split("&");
		System.out.println(Arrays.toString(arr));
		arr = "ab:cd".split(":");
		System.out.println(Arrays.toString(arr));
	}
	
//	@Test
	public void test3(){
		String s="[{\"wbs\":\"1\",\"projectStage\":\"在\",\"responsibleUser\":\"1\",\"planStartDate\":\"2020-09-08\",\"planEndDate\":\"2020-10-07\",\"planPeriod\":\"30\",\"workItem\":\"1\",\"isMilestone\":\"1\"}]";
		List<OaProjectPlan> list = JSONArray.parseArray(s, OaProjectPlan.class);
		System.out.println(list);
		System.out.println(new Date(1602000000000L));
		System.out.println(new Date(1599494400000L));
		//http://quotes.money.163.com/service/chddata.html?code=0000001&start=19901219&end=20201209&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER
	}
	
//	@Test
    public void test4(){
        String url = "http://quotes.money.163.com/service/chddata.html?code=1002616&start=19901219&end=20201209&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;VOTURNOVER;VATURNOVER";
        HttpUtil.sendGetFile(url,"d:/doc/stock/00002616.csv");
    }
	
//	@Test
    public void test5() throws IOException{
//        File file = new File("d:/doc/stock/0000001.csv");
//        List<String> lines = FileUtils.readLines(file,"gbk");
//        lines.subList(0, 100).forEach(x->System.out.println(x));
//        System.out.println(lines);
	    DecimalFormat df = new DecimalFormat("0");
	    Double d = new Double("3.39317806321e+11");
	    System.out.println(df.format(d)); 
	    System.out.println(LocalDateTime.now().minusDays(30).toInstant(ZoneOffset.UTC).toEpochMilli());
	    System.out.println(new Date().toLocaleString());
	    System.out.println(new Date().getTime());
    }
	
	@Test
    public void test6(){
        List<Integer> list = Arrays.asList(1,3,4,2,6,10,9,8,3);
        List<Integer> list2 = list.stream().sorted((x,y)->x-y).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
        System.out.println(JSON.toJSONString(System.getProperties()));
        System.out.println(JSON.toJSONString(System.getenv()));
    }
}
