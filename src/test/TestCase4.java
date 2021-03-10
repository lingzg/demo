package test;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestCase4 {

//    @Test
    public void test1() throws IOException{
        String path = "d:/doc/歌单.txt";
        File file = new File(path);
        List<String> list = FileUtils.readLines(file, "utf-8");
        for(String name : list){
            File dir = new File("d:/doc/xiami/"+name);
            dir.mkdirs();
        }
        System.out.println(list);
    }
    
//    @Test
    public void test2() throws IOException{
        Long a = null;
        long b = a;
        System.out.println(b);
    }
    
//    @Test
    public void test3() throws IOException{
        LocalDate ldate = LocalDate.now();
        int b = ldate.lengthOfMonth();
        System.out.println(b);
    }
    
//    @Test
    public void test4() throws IOException{
        DecimalFormat df = new DecimalFormat("0.00");
        double d = 2.0/0;
        System.out.println(d);
        System.out.println(3<d);
        String b = df.format(d);
        System.out.println(b);
    }
    
//    @Test
    public void test5() throws IOException{
        List<Integer> list = Arrays.asList(1,2,3);
        System.out.println(list.subList(0, 3));
        List<Double> list2 = Arrays.asList(1.0,21.2,3.4,1.0/0,-2.0/0);
        list2.sort((a,b)->a<b?1:(a>b?-1:0));
        System.out.println(list2);
        System.out.println(-2.0/0>0);
    }
    
    @Test
    public void test6() throws IOException{
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");
        String date = LocalDate.of(2021, 2, 1).minusMonths(1).format(fmt);
        System.out.println(date);
        System.out.println(LocalDate.of(2020, 2, 29).minusYears(1).toString());
    }
}
