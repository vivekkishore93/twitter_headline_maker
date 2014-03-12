import java.io.*;
import java.util.*;
public class modify_files{
public static void main(String args[]){
try{
String file=args[0];
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
PrintWriter pw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\modified_file.txt",true));
String line="";
	
	while((line=br.readLine())!=null)
	{
		if((line.indexOf("Received:"))>=0)
		{
		String[] arr=line.split(":");
		String str_line="";
		int i=3;
			while(i<arr.length)
			{
			str_line=str_line+arr[i]+":";
			i++;
			}
		pw.println(str_line);
		}
	}
	pw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}