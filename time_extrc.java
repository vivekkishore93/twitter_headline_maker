import java.io.*;
import java.util.*;
public class time_extrc{
public static void main(String args[]){
try{
ArrayList<String> al=new ArrayList<String>();
String file=args[0];
String time_file=args[1];
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
PrintWriter pw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\timed_tweets.txt"));
String line="";

while((line=br.readLine())!=null){
if((al.contains(line))==false)
al.add(line);

}
BufferedReader tbr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(time_file))));
String tline="";
while((tline=tbr.readLine())!=null){
String[] t_arr=tline.split("\t");

if(al.contains(t_arr[0]))
{
System.out.println(t_arr[0]);
pw.println(t_arr[1]);
}

}
pw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}