import java.io.*;
import java.util.*;
public class purify_tweets{
public static void main(String args[]){
try{
String file=args[0];
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader a_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(new File(file).getParent()+"\\abt 2k.txt"))));
PrintWriter pw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\purify_tweets.txt"));
PrintWriter apw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\info_pure_tweets.txt"));
PrintWriter rpw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\info_reply_tweets.txt"));
String line="";
String aline="";

while((line=br.readLine())!=null){
aline=a_br.readLine();
if(line.equals("null"))
continue;

line=line.replaceAll("[^A-Za-z0-9#@\\:./_ ]","");
line=line.replaceAll("_","\t");
while(line.indexOf(" ")==0)
line=line.substring(1,line.length());
pw.println(line);
String []a_arr=aline.split("\t");
apw.println(a_arr[6]);
rpw.println(a_arr[a_arr.length-1]);
}
pw.close();
apw.close();
rpw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}