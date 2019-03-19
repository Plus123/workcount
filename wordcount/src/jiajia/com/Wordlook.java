package jiajia.com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Wordlook {
	//用户单个单词的查询
   public String[] look(TreeMap<String, Integer> wordsave,String s)  {
	   TreeMap<String,Integer> map1 = new TreeMap<String, Integer>();  
		String[] word= s.split(";");//截取的是用户传来的单词
       int i;
       for(i=0; i<word.length; i++) {
       	for(Entry<String,Integer> entry : wordsave.entrySet()) { 
       		if(word[i].equals(entry.getKey()))
       		{
       			map1.put(entry.getKey(), entry.getValue());
       			System.out.println(entry.getKey() + "的个数是" + entry.getValue()); 
       			break;
       		}
           } 
       }
		return word;
   }
   public void save(TreeMap<String, Integer> word){
	   //传入一个map类型的集合参数
	 //统计该文本所有单词数量及词频数，并能将单词及词频数按字典顺序输出到文件result.txt
		BufferedWriter bw = null;
		try {
			File file1 = new File("result.txt");
			if (!file1.exists()) {
				file1.createNewFile();
			}
			FileWriter fw = new FileWriter(file1.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
       Iterator<String> it1 = word.keySet().iterator();//用迭代器把集合中的单词遍历
       while(it1.hasNext())
       {
       	String key = (String) it1.next();//拿到key和value的值
       	Integer value = word.get(key);
       	
       	try {
				bw.write(key+"="+value+"\n");//将key和value的值写入
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }
	
   }
}
