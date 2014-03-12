import java.io.*;
import java.util.*;
public class common_tweets{
public static void main(String args[])
{
try{
HashMap<String,ArrayList<String>> map_al=new HashMap<String,ArrayList<String>>();
HashMap<String,String> id_map=new HashMap<String,String>();
String file=args[0]; //tweets phrases
String file_id=args[1]; //tweet ids
String file_text=args[4]; //tweet texts
String file_text_id=args[5]; //texts_id
String file_key=args[2]; //keywords
String file_out=args[3]; //output
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader br_id=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file_id))));
BufferedReader br_key=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file_key))));
BufferedReader br_text=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file_text))));
BufferedReader br_text_id=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file_text_id))));
PrintWriter pw=new PrintWriter(new FileWriter(file_out));
String line="";
String line_id="";
String line_text="";
String line_text_id="";
while((line_text=br_text.readLine())!=null)
{
line_text_id=br_text_id.readLine();
id_map.put(line_text_id,line_text);
}
while((line=br.readLine())!=null){
line_id=br_id.readLine();

	map_al.put(line,new ArrayList<String>());
	StringTokenizer st=new StringTokenizer(line_id,",");
	
	while(st.hasMoreTokens()){
	map_al.get(line).add(st.nextToken());
	}
}
String line_key="";
int count=0;
while((line_key=br_key.readLine())!=null){
String[] st_arr=line_key.split("-");
if(st_arr.length>2)
{count++;
pw.println(line_key);
}
System.out.println(map_al.get(st_arr[0]));
int i=0;
while(i<st_arr.length){
int j=i+1;
while(j<st_arr.length){
//String common=common_tweets(map_al.get(st_arr[i]),map_al.get(st_arr[j]),id_map);
//pw.println(st_arr[i]+"::"+st_arr[j]+"\t"+common);
j++;
}
i++;
}

}
System.out.println(count);
pw.close();
}
catch(Exception e)
{e.printStackTrace();

}
}
public static String common_tweets(ArrayList<String> al1,ArrayList<String> al2,HashMap<String,String> id_map){
ArrayList<String> al=new ArrayList<String>();
int al1_var=0;
String final_str="";
while(al1_var<al1.size())
{

if(al2.contains(al1.get(al1_var)))
{
if(al.contains(id_map.get(al1.get(al1_var)))==false)
{
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
