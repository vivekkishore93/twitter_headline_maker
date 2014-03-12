import java.io.*;
import java.util.*;
import java.net.*;
public class generate_headline{
public static String hgenerator(String line){
String key_line="";
try{
ArrayList<String> pos_label_al=new ArrayList<String>();
ArrayList<String> key_add_al=new ArrayList<String>();
pos_label_al.add("NN");
pos_label_al.add("NNS");
pos_label_al.add("NNP");
pos_label_al.add("NNPS");
pos_label_al.add("HT");
pos_label_al.add("CC");
pos_label_al.add("CD");
pos_label_al.add("DT");
pos_label_al.add("AX");
pos_label_al.add("IN");
pos_label_al.add("JJ");
pos_label_al.add("JJR");
pos_label_al.add("JJS");
pos_label_al.add("LS");
pos_label_al.add("MD");
pos_label_al.add("PDT");
pos_label_al.add("POS");
pos_label_al.add("PRP");
pos_label_al.add("PRP$");
pos_label_al.add("RB");
pos_label_al.add("RBR");
pos_label_al.add("RBS");
pos_label_al.add("RP");
pos_label_al.add("TO");
pos_label_al.add("UH");
pos_label_al.add("VB");
pos_label_al.add("VBD");
pos_label_al.add("VBG");
pos_label_al.add("VBN");
pos_label_al.add("VBP");
pos_label_al.add("VBZ");
pos_label_al.add("WDT");
pos_label_al.add("WP");
pos_label_al.add("WP$");
pos_label_al.add("WRB");
//pos_label_al.add("USR");
//pos_label_al.add("URL");
//String file=args[0];
//BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
//PrintWriter pw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\keyphrases.txt"));
//String line="";

//while((line=br.readLine())!=null)
{
key_add_al.clear();
//String key_line="";
StringTokenizer st=new StringTokenizer(line,"_ ");
while(st.hasMoreTokens())
{
String str1=st.nextToken();
if((st.hasMoreTokens())==false)
break;
String pos_label=st.nextToken();



	if(pos_label_al.contains(pos_label)){
	if((pos_label.equals("URL"))==false)
	str1=str1.replaceAll("[^A-Za-z0-9#\t ]","");
	/*else if((pos_label.equals("URL")))
	{
	//System.out.println(str1);
	String ustr1=getLongURL(str1);
	if(ustr1!=null)
	ustr1=str1;
	}*/
	if((str1.length()>2)&&(key_add_al.contains(str1)==false))
	{
	key_add_al.add(str1);
	key_line=key_line+str1+" ";
	}
}
}
key_line.replaceAll("\t","_");
//pw.println(key_line);
}
//pw.close();
}
catch(Exception e){
e.printStackTrace();
}
return key_line;
}

public static String getLongURL(String shortURL)
{
//String shortURL="http://thne.ws/1h8Wptr" ;
		
		System.out.println("Short URL: "+ shortURL);
                URLConnection urlConn =  connectURL(shortURL);
try{    
	urlConn.getHeaderFields();
}
				catch(Exception e){
return null;
}               
			   System.out.println("Original URL: "+ urlConn.getURL());


/* connectURL - This function will take a valid url and return a 
URL object representing the url address. */
return ""+urlConn.getURL();
	}// End of Method
	static URLConnection connectURL(String strURL) {
        URLConnection conn =null;
 try {
     URL inputURL = new URL(strURL);
     conn = inputURL.openConnection();
            int test = 0;

 }catch(MalformedURLException e) {
     System.out.println("Please input a valid URL");
	 
 }catch(IOException ioe) {
     System.out.println("Can not connect to the URL");
 }
 return conn;
}
}