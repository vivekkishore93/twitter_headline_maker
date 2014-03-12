import java.io.*;
import java.util.*;
public class score_normal{
public static HashMap<String,Double> mainscore(String file)throws IOException{
//String file=args[0];
BufferedReader br=new BufferedReader(new InputStreamReader (new DataInputStream(new FileInputStream(file))));
String line="";
HashMap<String,Double> scoremap=new HashMap<String,Double>();
ArrayList<String> scoreal =new ArrayList<String>();
ArrayList<Double > scoreal2 =new ArrayList<Double>();
int i=0;
while((line=br.readLine())!=null)
{
line=line.replaceAll(" : ",":");
StringTokenizer st=new StringTokenizer(line,":");
String str1=st.nextToken();
String str2=st.nextToken();
double score=Double.parseDouble(str2);
scoremap.put(str1,score);
scoreal.add(str1);
scoreal2.add(score);


}
double max=findmax(scoreal2);
double min=findmin(scoreal2);

double dif=max-min;
while(i<scoreal.size())
{
double nscore=(scoremap.get(scoreal.get(i))-min)/dif;
scoremap.put(scoreal.get(i),nscore);
if(scoreal.get(i).equals("ISLAMABAD"))
System.out.println(scoreal.get(i) +"\t"+nscore);
i++;
}



return scoremap;

}

public static double findmin(ArrayList<Double> al1){
double min=1000000.0;
int i=0;
while(i<al1.size())
{
if(min>al1.get(i))
{
min=al1.get(i);
}
i++;

}
return (min);
}
public static double findmax(ArrayList<Double> al1){
int i=0;
double max=-1.0;
while(i<al1.size())
{
if(max<al1.get(i))
{
max=al1.get(i);
}
i++;

}
return (max);
}



}