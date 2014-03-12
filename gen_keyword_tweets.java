import java.io.*;
import java.util.*;
public class gen_keyword_tweets{
public static void main(String args[])
{
try{
ArrayList<String> al=new ArrayList<String>();
ArrayList<String> tweet_al=new ArrayList<String>();
String file=args[0]; //Keywords
String tweet_file=args[1]; //tweets
String tweet_id_file=args[2]; //tweet_ids
String key_tweets=args[3]; //tweets phrase final
String id_tweets=args[4]; //tweet id final
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader tweet_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_file))));
BufferedReader tweet_id_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_id_file))));
PrintWriter pw=new PrintWriter(new FileWriter(id_tweets));
PrintWriter final_tpw=new PrintWriter(new FileWriter(key_tweets));
String line="";

while((line=br.readLine())!=null){
line=line.toLowerCase();
al.add(line);
}

String tweet_line="";
String tweet_id_line="";

while((tweet_line=tweet_br.readLine())!=null){
tweet_id_line=tweet_id_br.readLine();
int alvar=0;
tweet_line=tweet_line.toLowerCase();
while(alvar<al.size())
{
String lcs=LCS.printLCS(al.get(alvar),tweet_line);

if(lcs.indexOf(al.get(alvar))>=0){

StringTokenizer st=new StringTokenizer(tweet_id_line,",");
while(st.hasMoreTokens()){
String str=st.nextToken();
if((tweet_al.contains(str))==false){
tweet_al.add(str);
}
}
}
alvar++;
}
}
tweet_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_file))));
tweet_id_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_id_file))));
String tline="";
int count=0;
while((tline=tweet_br.readLine())!=null)
{
String tidline="";
tweet_id_line=tweet_id_br.readLine();

StringTokenizer st=new StringTokenizer(tweet_id_line,",");
while(st.hasMoreTokens())
{
String str=st.nextToken();
if(tweet_al.contains(str))
{
tidline=tidline+str+",";
}
}
if(tidline.length()>3)
{count++;
if(count==770)
	break;
pw.println(tidline);
final_tpw.println(tline);
}
}
final_tpw.close();
pw.close();
}
catch(Exception e){
e.printStackTrace();
}
}

}