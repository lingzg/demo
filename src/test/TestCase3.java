package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestCase3 {

//	@Test
	public void test1() throws UnsupportedEncodingException{
		Base64 base64 = new Base64();
		final String text = "字串文字";
		final byte[] textByte = text.getBytes("UTF-8");
		//编码
		final String encodedText = base64.encodeToString(textByte);
		System.out.println(encodedText);
		//解码
		System.out.println(new String(base64.decode("5rK56LS55oql6ZSA5piO57uG6K+05piOLmRvYw=="), "UTF-8"));
		System.out.println(java.net.URLDecoder.decode(text,"UTF-8"));
	}
	
//	@Test
	public void test2() throws IOException{
		File file = new File("D:/doc/times.xls");
		OutputStream out = new FileOutputStream(file);
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("Sheet1");
		int index=0;
		Row row  =sheet.createRow(index++);
		String[] header = {"n", "m", "times"};
		for(int i=0;i<3;i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(header[i]);
		}
		for(int i=2;i<=100;i++){
			for(int j=2;j<=10;j++){
				int times = times(i, j);
				row  =sheet.createRow(index++);
				Cell cell = row.createCell(0);
				cell.setCellValue(i);
				cell = row.createCell(1);
				cell.setCellValue(j);
				cell = row.createCell(2);
				cell.setCellValue(times);
			}
		}
		wb.write(out);
		wb.close();
		out.close();
	}
	
	@Test
	public void test3(){
		int t = times(999,5);
		System.out.println(t);
	}
	
	public int times(int n, int m){
		int times = 0;
		int[] arr = new int[n];
		int[] res=new int[n];
		for(int i=0;i<arr.length;i++){
			arr[i]=i+1;
			res[i]=i+1;
		}
		while(true){
			res = goout(res,m);
			times++;
//			System.out.println(Arrays.toString(res));
			if(arrayEqual(arr, res)){
				break;
			}
		}
		return times;
	}
	
	public boolean arrayEqual(int[] arr1, int[] arr2){
		if(arr1.length!=arr2.length){
			return false;
		}
		boolean flag = true;
		for(int j=0;j<arr1.length;j++){
			if(arr1[j]!=arr2[j]){
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	public int[] goout(int[] arr, int m){
		int n = arr.length;
		int count=1;
		int index=0;
		int out=0;
		int[] res = new int[n];
		while(out<n){
			if(count%m==0){
				res[out++] = arr[index];
				arr[index]=-1;
				if(out==n){
					break;
				}
			}
			count++;
			do{
				index++;
				if(index>=n){
					index=0;
				}
			}while(arr[index]==-1);
		}
		return res;
	}
}
