package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestCase4 {

    @Test
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
}
