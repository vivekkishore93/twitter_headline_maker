import java.io.*;
import java.util.*;
public class common_tweets{
public static ArrayList<String> extract_common(String line_key,String file,String file_id)
{
ArrayList<String> tweet_uni=new ArrayList<String>();
try{
HashMap<String,ArrayList<String>> map_al=new HashMap<String,ArrayList<String>>();

//String file=args[0]; //tweets phrases
//String file_id=args[1]; //tweet ids
//String file_text=args[4]; //tweet texts
//String file_text_id=args[5]; //texts_id
//String file_key=args[2]; //keywords
//String file_out=args[3]; //output
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader br_id=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file_id))));



String line="";
String line_id="";
String line_text="";
String line_text_id="";

while((line=br.readLine())!=null){
line_id=br_id.readLine();

	map_al.put(line,new ArrayList<String>());
	StringTokenizer st=new StringTokenizer(line_id,",");
	
	while(st.hasMoreTokens()){
	map_al.get(line).add(st.nextToken());
	}
}
//String line_key="";
int count=0;
//while((line_key=br_key.readLine())!=null)
{
String[] st_arr=line_key.split("-");
if(st_arr.length>2)
{count++;

}
//System.out.println(map_al.get(st_arr[0]));
int i=0;

while(i<st_arr.length){
int j=0;
while(j<map_al.get(st_arr[i]).size())
{
if(((tweet_uni.contains(map_al.get(st_arr[i]).get(j))))==false)
tweet_uni.add(map_al.get(st_arr[i]).get(j));
j++;
}
//pw.println(st_arr[i]+"::"+st_arr[j]+"\t"+common);
i++;
}

}



}
catch(Exception e)
{e.printStackTrace();

}
return tweet_uni;
}
public static int count_common_tweets(ArrayList<String> al1,ArrayList<String> al2){
ArrayList<String> al=new ArrayList<String>();
int al1_var=0;
int count=0;
String final_str="";
//System.out.println(al1+"\n"+al2);
while(al1_var<al1.size())
{
//System.out.println(al1.get(al1_var)+"\t"+al2);
if(al2.contains(al1.get(al1_var)))
{

{
count++;
//final_str=final_str+id_map.get(al1.get(al1_var))+",";
final_str=final_str+al1.get(al1_var)+",";

}
}
al.clear();
al1_var++;
}
return count;
}
public static String write_common_tweets(ArrayList<String> al1,ArrayList<String> al2){
ArrayList<String> al=new ArrayList<String>();
int al1_var=0;
int count=0;
String final_str="";
//System.out.println(al1+"\n"+al2);
while(al1_var<al1.size())
{
//System.out.println(al1.get(al1_var)+"\t"+al2);
if(al2.contains(al1.get(al1_var)))
{

{
count++;
//final_str=final_str+id_map.get(al1.get(al1_var))+",";
final_str=final_str+al1.get(al1_var)+",";

}
}
al.clear();
al1_var++;
}
return final_str;
}
}
