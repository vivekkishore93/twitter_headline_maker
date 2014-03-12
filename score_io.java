package zz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class score {
		
	
	public static void main(String args[])throws IOException
	{	
		Scanner in1=null;
		Scanner in2=null;
		PrintWriter out=null;
	     in1=new Scanner(new FileInputStream("in1.txt"));
	     in2=new Scanner(new FileInputStream("in2.txt"));
	     
		out=new PrintWriter(new FileOutputStream("output.txt"));
		ArrayList <String>words1=new ArrayList<String>();
		ArrayList<Integer> re1=new ArrayList<Integer>();
		ArrayList <Integer>t1=new ArrayList<Integer>();
		ArrayList <String>words2=new ArrayList<String>();
		ArrayList <Integer>re2=new ArrayList<Integer>();
		ArrayList <Integer>t2=new ArrayList<Integer>();
		
		ArrayList<scorephrase>sp1=new ArrayList<scorephrase>();
		
		
		
		while(in1.hasNextLine())
		{
			String s =in1.nextLine();
			StringTokenizer st1=new StringTokenizer(s,":");
			words1.add(st1.nextToken());
			re1.add(Integer.parseInt(st1.nextToken()));
			t1.add(Integer.parseInt(st1.nextToken()));
				
		}
		while(in2.hasNextLine())
		{
			String s =in2.nextLine();
			StringTokenizer st2=new StringTokenizer(s,":");
			words2.add(st2.nextToken());
			re2.add(Integer.parseInt(st2.nextToken()));
			t2.add(Integer.parseInt(st2.nextToken()));
			
		}
			
		for(int i=0;i<words1.size();i++)
		{
			int ret2=0;
			int twe2=0;
			for(int j=0;j<words2.size();j++)
			{
				if(words1.get(i).equals(words2.get(j)))
				{
					ret2=re2.get(j);
					twe2=t2.get(j);
					break;
				}
			}
			
				double presentscore=findscore(re1.get(i),t1.get(i),ret2,twe2);
				scorephrase sp=new scorephrase(words1.get(i),presentscore);
				System.out.println(sp.getStr()+" : "+sp.getScore());
				sp1.add(sp);
		}
		
		
		
			Collections.sort(sp1,new Comparator<scorephrase>(){
				public int compare(scorephrase s1,scorephrase s2)
				{
					System.out.println(s1.getScore()+"\t"+s2.getScore());
					if(s1.getScore()>s2.getScore()){
						return -1;
					}
				
					return 1;
				}
			});
	
			
			int var1=0;
			while(var1<sp1.size()){
				
				out.println(sp1.get(var1).getStr()+" : "+sp1.get(var1).getScore());
				
				var1++;
				
			}
				
		in1.close();
		in2.close();
		out.close();
		
	}
	
static double findscore(int retweet,int tweet,int preRetweet,int preTweet){
	double x= (double)((((double)(retweet-preRetweet)/Math.max(retweet, preRetweet))+1.0)/(double)(preTweet+1.0));
	
	double  f=tweet*Math.log(x+1)/Math.log(2);
	
	return f;
}
	
	
	
}


class scorephrase{
	String str;
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	double score;
	
	scorephrase(String str,double score){
		this.str=str;
		this.score=score;
		
	}
	
	
}



