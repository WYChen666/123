//package com.nkl.admin.action;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.Writer;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//public class CalcResult2 {
//	public BigDecimal[][] arr1 = new BigDecimal[600][600];
//	public BigDecimal[] arr2 = new BigDecimal[600];
//	public BigDecimal[][] arr3 = new BigDecimal[600][600];
//	public int[][] M = new int [600][600];
//	public int[] MN = new int[600];
//	public int xe,lie,liee,lieee;
//
//	public String getLineFromTxt(File file, String split) throws Exception {
//		StringBuilder result = new StringBuilder();
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		String firstLine;
//		int s = 0;
//		xe = 1;
//		while ((firstLine = br.readLine()) != null) {
//			String[] arrs = firstLine.split(" ");
//			BigDecimal ans = BigDecimal.valueOf(0);
//			s = arrs.length;
//			int biao = 0;
//			for (int i = 0; i < arrs.length; i++) {
//				BigDecimal a = BigDecimal(arrs[i]);
//				// System.out.println(a);
//				if (i == 0)
//					ans = a;
//				else if (ans.compareTo(a) < 0) {
//					ans = a;
//					biao = i;
//				}
//				// arr3[xe][i]=a;
//			}
//			M[biao+1][MN[biao+1]++]=xe;
//			xe++;
//		}
//		//System.out.println(s);
//
//		result.append("最终结果：");
//        for(int i=1;i<=s;i++)
//        {
//        	result.append("第"+i+"列（");
//        	for(int j=0;j<MN[i];j++)
//        	{
//        		result.append(M[i][j]);
//        		if (j != MN[i]-1) {
//    				result.append(",");
//    			}
//        	}
//        	result.append("）");
//        	if (i != s) {
//				result.append("<br/>　　　　&nbsp;");
//			}
//        	
//        }
//		
//		return result.toString();
//	}
//
//	@SuppressWarnings("unused")
//	public void getLineFromTxt1(File file, String split, int y)
//			throws Exception {
//		BufferedReader br = new BufferedReader(new FileReader(file));
//        String firstLine;
//        int xee=1;
//        while((firstLine = br.readLine())!=null)
//        {
//            String[] arrs = firstLine.split(" ");
//            BigDecimal ans =BigDecimal.valueOf(0);
//           // s= arrs.length;
//            if(y==1)
//            lie=arrs.length;
//            
//            int biao=0;
//            for(int i = 0; i< arrs.length; i++)
//            {
//                BigDecimal a = BigDecimal(arrs[i]);
//                if(y==1)
//                    arr1[xee][i]=a;
//                else if(y==2)
//                	  arr3[xee][i]=a;
//                else
//                    arr2[i]=a;
//            }
//            xee++;
//        }
//        if(y==1)
//        	liee=xee;
//        if(y==2)
//        	lieee=xee;
//	}
//
//	private BigDecimal BigDecimal(String s) {
//		int flag=0;
//        BigDecimal ans=BigDecimal.valueOf(0),anss=BigDecimal.valueOf(0),ds=BigDecimal.valueOf(1);
//
//        for(int i=0; i<s.length(); i++)
//        {
//            if(s.charAt(i)=='.')
//            {
//                flag=1;
//            }
//            else
//            {
//                if(flag==0)
//                {
//                    ans=ans.multiply(new BigDecimal(10));
//                    ans=ans.add(new BigDecimal(s.charAt(i)-'0'));
//                }
//                else
//                {
//                    BigDecimal ds1=(ds.multiply(new BigDecimal(0.1))).multiply(new BigDecimal(s.charAt(i)-'0'));
//                    anss=anss.add(ds1);
//                    ds=ds.multiply(new BigDecimal(0.1));
//                }
//            }
//        }
//        return ans.add(anss);
//	}
//
//	public BigDecimal solve(int x) {
//		BigDecimal ans = BigDecimal.valueOf(0);
//		for (int i = 0; i < lie; i++) {
//			BigDecimal a = arr1[x][i].subtract(arr2[i]);
//			a = a.multiply(a);
//			ans = ans.add(a);
//		}
//		return ans;
//	}
//
//	public BigDecimal solvee(int x)
//    {
//		BigDecimal ans = BigDecimal.valueOf(0);
//		for (int i = 0; i < lie; i++) {
//			BigDecimal a = arr2[i].subtract(arr3[x][i]);
//			a = a.multiply(a);
//			ans = ans.add(a);
//		}
//		return ans;
//    }
//	
//	public String getResult1(String one_data, String center1, String data_in, String matrix1){
//		StringBuilder result = new StringBuilder();
//		try {
//			File file1 = new File(one_data);// 一行五列数据
//			getLineFromTxt1(file1, " ", 0);
//			File file2 = new File(center1);// 三行五列数据
//			getLineFromTxt1(file2, " ", 1);
//			File file3 = new File(data_in);// 37行5列数据
//			if (!file3.exists()) {
//				file3.createNewFile();
//			}
//			getLineFromTxt1(file3, " ", 2);
//			File file4 = new File(matrix1);// 37行3列数据
//			result.append(getLineFromTxt(file4, " "));
//			
//			BigDecimal anss =solvee(1);
//	        int bb=1;
//	        for(int i=2;i<lieee;i++){
//	        	if(solvee(i).compareTo(anss)<0){
//	        		anss=solvee(i);
//                    bb=i;
//	        	}
//			}
//			result.append("<br/>最短的那行数据：");
//			result.append(bb);
//			Writer out = new FileWriter(file3);
//			for(int i=1; i<lieee; i++){
//				for(int j=0; j<lie; j++){
//					out.write(arr3[i][j].setScale(2, RoundingMode.HALF_UP).toString()+" ");
//	            }
//	            out.write("\r\n");
//	        }
//			for(int i=0;i<lie;i++){
//				out.write(arr2[i].setScale(2, RoundingMode.HALF_UP).toString()+" ");
//	        }
//	        out.write("\r\n");
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		
//		return result.toString();
//	}
//
//	public static void main(String[] args) throws Exception {
//		CalcResult2 calcResult2 = new CalcResult2();
//		File file1 = new File("D:\\FCM手算\\java配置与输出\\one-data.txt");// 一行五列数据
//		calcResult2.getLineFromTxt1(file1, " ", 0);
//		File file2 = new File("D:\\FCM手算\\java配置与输出\\center1.txt");// 三行五列数据
//		calcResult2.getLineFromTxt1(file2, " ", 1);
//		File file3 = new File("D:\\FCM手算\\java配置与输出\\---data_in.txt");// 37行5列数据
//		calcResult2.getLineFromTxt1(file3, " ", 2);
//		File file4 = new File("D:\\FCM手算\\java配置与输出\\matrix1.txt");// 37行3列数据
//		// System.out.printf("3144442\n");
//		calcResult2.getLineFromTxt(file4, " ");
//		BigDecimal anss = calcResult2.solve(1);
//
//		int bb=1;
//	      
//        for(int i=2;i<calcResult2.lieee;i++)
//        {
//        	
//        	if(calcResult2.solvee(i).compareTo(anss)<0)
//              {
//                  anss=calcResult2.solvee(i);
//                       bb=i;
//              }
//        	
//        }
//        System.out.printf("最短的那行数据:%d\n",bb);
//        Writer out =new FileWriter(file3);
//        for(int i=1; i<calcResult2.lieee; i++)
//        {
//            for(int j=0; j<calcResult2.lie; j++)
//            {
//                out.write(calcResult2.arr3[i][j].setScale(2, RoundingMode.HALF_UP).toString()+" ");
//            }
//            out.write("\r\n");
//        }
//        for(int i=0;i<calcResult2.lie;i++)
//        {
//        	 out.write(calcResult2.arr2[i].setScale(2, RoundingMode.HALF_UP).toString()+" ");
//        }
//        out.write("\r\n");
//        out.close();
//	}
//}
