package test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.alibaba.fastjson.JSONArray;

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
	}
	
	
}
