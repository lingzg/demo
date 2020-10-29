package test;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

public class TestCase {

//    @Test
    public void test1(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 0); //当月最后一天
        String date = fmt.format(c.getTime());
        int days = c.get(Calendar.DATE); //当月天数
        System.out.println(date);
        System.out.println(days);
    }
    
//    @Test
    public void test2(){
        System.out.println(Integer.parseInt("-1"));
        String numStr = "-42";
        numStr = numStr.replaceAll("^(\\+?|-?)0+", "");
        System.out.println(numStr);
        String max = String.valueOf(Integer.MAX_VALUE);
        System.out.println(max);
        String min = String.valueOf(Integer.MIN_VALUE);
        System.out.println(min);
        System.out.println(new StringBuilder("abc").indexOf("a"));
        System.out.println(100110^100101);
        int number = 12321;
        int start = /*number /*/ (int) Math.pow(10,Math.floor(Math.log10(number)));
        System.out.println(start);
    }
    
//    @Test
    public void test3(){
        Set<String> set = new HashSet<String>();
        set.add("ac");
        set.add("ab");
        set.add(new String("ac"));
        System.out.println(set);
        System.out.println(Integer.MAX_VALUE/5);
    }
    
//    @Test
    public void test4(){
        char[] arr = {'1','2','3','2'};
        char c = arr[0];
        for(int i=1;i<arr.length;i++){
            c^=arr[i];
        }
        System.out.println(c);
    }
//    @Test
    public void test5(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int x=(i/3)*3+j/3;
                int y=(i%3)*3+j%3;
                System.out.print("("+x+","+y+") ");
                if(j%3==2){
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
    
//    @Test
    public void test6(){
        System.out.println((int)'(');
        System.out.println((int)')');
        System.out.println((int)'[');
        System.out.println((int)']');
        System.out.println((int)'{');
        System.out.println((int)'}');
        System.out.println('1'+'2');
    }
//    @Test
    public void test7(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(0, 1);
        list.add(1, 2);
        System.out.println(list);
        System.out.println("aabac".replaceFirst("a", ""));
        double d=-1.000;
        System.out.println(d==-1);
        System.out.println(Integer.MAX_VALUE);

    }
//    @Test
    public void test8(){
    	System.out.println(1&0);
    	System.out.println(16&15);
    	System.out.println(15&14);
    	System.out.println(17^16);
    }
    
//    @Test
    public void test9(){
    	String s=String.format("%04d", 12345);
    	System.out.println(s);
    }
    
//    @Test
    public void test10(){
    	List<Integer> list = Arrays.asList(1,2,3,4);
    	list.forEach(x->{
    		if(x==3){
    			return;
    		}
    		System.out.println(x);
    	});
    	System.out.println("------------------");
    	list.stream().anyMatch(x -> {
    		System.out.println(x);
    		return x==3;
    	});
    	System.out.println("------------------");
    	list.stream().filter(x -> {
    		System.out.println(x);
    		return x==3;
    	}).findAny();
    }
    
//    @Test
    public void test11(){
    	String s1="2020-09-11";
    	String s2="2020-09-19";
    	System.out.println(s1.compareTo(s2));
    	
    	String json = "[{\"type\":\"a\",\"num\":1},{\"type\":\"a\",\"num\":2},{\"type\":\"a\",\"num\":3},{\"type\":\"b\",\"num\":2},{\"type\":\"b\",\"num\":4},{\"type\":\"c\",\"num\":3}]";
    	List<Map> list = JSONArray.parseArray(json, Map.class);
    	System.out.println(list);
    	list.stream().collect(Collectors.groupingBy(y -> y.get("type"))).forEach((k,v) -> {System.out.println(k);System.out.println(v);});
    }
    
//    @Test
    public void test12(){
    	int x=Integer.MAX_VALUE;
    	int y=2;
//    	Math.addExact(x, y);
//    	Math.subtractExact(-x, y);
    	System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    	
    }
    
//    @Test
    public void test13(){
    	String json = "[{\"type\":\"a\",\"num\":1},{\"type\":\"a\",\"num\":2},{\"type\":\"a\",\"num\":3},{\"type\":\"b\",\"num\":2},{\"type\":\"b\",\"num\":4},{\"type\":\"c\",\"num\":3}]";
    	List<Map> list = JSONArray.parseArray(json, Map.class);
    	System.out.println(list);
    	list.stream().collect(Collectors.groupingBy(y -> y.get("type"))).forEach((k,v) -> {System.out.println(k);System.out.println(v);});
    }
    
//    @Test
    public void test14(){
    	BigInteger a = new BigInteger("1");
    	BigInteger b = new BigInteger("2").subtract(a);
    	System.out.println(a==b);
    	System.out.println(a.equals(null));
    	LocalDate date = LocalDate.now();
    	System.out.println(date);
    	date = date.minusDays(1);
    	System.out.println(date);
    }
    
    @Test
    public void test15(){
    	String list = Stream.of("1","2","3","4","5","6","7","8","9").skip(8).limit(3).reduce("", (x,y) -> x+","+y);
    	System.out.println(list);
    	String lastMonth = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"));
    	System.out.println(lastMonth);
    }
}
