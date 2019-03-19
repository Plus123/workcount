package jiajia.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFrame;

public class WordRead  extends JFrame{

	public static String[] ss;
	public static TreeMap<String, Integer> wordsave ;
	public void wordRead(){
		//文件的读入
		//将读入的文件存放在集合里面
		//读入文件Word.txt
	    String J = "Word.txt";
		File file = new File(J);
		InputStreamReader f = null;						
		try {				 
			f = new InputStreamReader(new FileInputStream(file), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(f);//读入缓冲流													
		//一次读入一行，读入不为空时 进行单词统计				 				  
	     
		//将读入的文件存放到集合
		 wordsave = new TreeMap<String, Integer>();
		
		try {
			while((J=reader.readLine())!=null){
				J=J.toLowerCase();//忽略大小写
				String[] str=J.split("[^a-zA-Z]");//过滤出字母，过滤掉符号及其他的
				//把单词和长度为0 的单词存放到集合里面
				for(int i=0;i<str.length;i++){
					String word = str[i].trim();
					if(wordsave.containsKey(word) &&  word.length()!=0){
						wordsave.put(word, wordsave.get(word)+1);
					}else{
						wordsave.put(word,1);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}   
		System.out.println("词频统计");
		System.out.println("请输入词频？");
		System.out.println("1.指定单词统计，并显示查找单词的柱状图");
		System.out.println("2.高频单词的统计");
		System.out.println("3.统计该文本所有单词数量和词频数，并将单词和词频统计输出");
		
		Scanner input = new Scanner(System.in);
		int i =input.nextInt();
		if(i==1){
			System.out.println("请输入你想查询的，用;隔开");
			String  str = input.next();
			Wordlook wl= new Wordlook();
			 ss= wl.look(wordsave, str);
			 WordRead demo = new WordRead();
				demo.setVisible(true);
			
		}else if (i==2){
			//高频单词的显示
			String st=null;
			Integer in=0;
			Integer temp;
			 Iterator<String> it1 = wordsave.keySet().iterator();
	            while(it1.hasNext())
	            {
	            	String key = (String) it1.next();
		        	Integer value = wordsave.get(key);
		        	if(value>in){
		        		temp=value;	
		        		in=temp;
		        		value=in;
		        		st=key;
		        	}
	            }
			System.out.println(st+" \t"+in);
			
		}else if (i==3){
			Wordlook wl=new Wordlook();
			wl.save(wordsave);
			System.out.println("已存入！查看吧！");
		}
		
	}
	
	
	//柱状图的实现
	//柱状图的显示
		public WordRead(){
			super();
			setTitle("绘制柱形图");
			setBounds(3, 200, 450, 450);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		@Override
		public void paint(Graphics g){
			int Width = getWidth();
			int Height = getHeight();
			int leftMargin = 50;//柱形图左边界
			int topMargin = 50;//柱形图上边界
			Graphics2D g2 = (Graphics2D) g;
			int ruler = Height-topMargin;
			int rulerStep = ruler/20;//将当前的高度评分为20个单位
			g2.setColor(Color.WHITE);//绘制白色背景
			g2.fillRect(0, 0, Width, Height);//绘制矩形图
			g2.setColor(Color.LIGHT_GRAY);
			for(int i=0;i<rulerStep;i++){
				g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
			}
			g2.setColor(Color.RED);
			int m=0;
			for(int i = 0;i<ss.length;i++){
				int value = wordsave.get(ss[i]);
				int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
				g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
				g2.drawString(ss[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
				m++;
			}
		}
	
}
