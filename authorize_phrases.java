import java.io.*;
import java.util.*;
public class authorize_phrases{
public static void main(String[] args){
try{
ArrayList<String> al=new ArrayList<String>();
String file=args[0];
String kfile=args[5];
String follow_file=args[1];
String status_file=args[4];
String out_file=args[2];
String key_file=args[6];
String ufile=args[3];
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader fbr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(follow_file))));
BufferedReader sbr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(status_file))));
BufferedReader ubr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(ufile))));
BufferedReader kbr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(kfile))));
PrintWriter pw=new PrintWriter(new FileWriter(out_file));
PrintWriter kpw=new PrintWriter(new FileWriter(key_file));
String line="";
String kline="";
String follower="";
String status="";

while((line=br.readLine())!=null){
	follower=fbr.readLine();
	status=sbr.readLine();
	
	if(((Integer.parseInt(follower))>=600)&&((Integer.parseInt(status))>=6000))
	{
	al.add(line);
	}
}
String uline="";
while((uline=ubr.readLine())!=null)
{
kline=kbr.readLine();
StringTokenizer st=new StringTokenizer(uline,",");
String final_line="";
while(st.hasMoreTokens()){
String tweet_id=st.nextToken();
	if(al.contains(tweet_id))
{
final_line=final_line+tweet_id+",";
}
}
if(final_line.length()>3)
{
pw.println(final_line);
kpw.println(kline);
}
}
pw.close();
kpw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}