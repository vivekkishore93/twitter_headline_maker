import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
public class gettext{
public static void main(String args[]){
try{

String file=args[0];
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file)))); 
String line="";
PrintWriter pw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_text.txt"));
PrintWriter ipw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_tid.txt"));
PrintWriter tpw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_time.txt"));
PrintWriter upw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_user.txt"));
PrintWriter rpw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_retwwetcount.txt"));
PrintWriter spw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_statuscount.txt"));
PrintWriter mpw=new PrintWriter(new FileWriter(new File(file).getParent()+"\\tweets_media.txt"));
int count=0;

while((line=br.readLine())!=null)
{
String media="";
System.out.println(count++);
if ((line.indexOf("filter_level")>=0)==false)
{
continue;
}
InputStream fis = new ByteArrayInputStream(line.getBytes());
         
        //create JsonReader object
        JsonReader jsonReader = null;
		JsonObject jsonObject =null;
		try{
		jsonReader = Json.createReader(fis);
		jsonObject = jsonReader.readObject();
		}
		catch(Exception e){
		continue;
		}
		;
		//jsonReader.close();
        //fis.close();
		
		//JsonObject innerJsonObject = jsonObject.getJsonObject("Recieved");
		//System.out.println(line);
		JsonObject innerJsonObject2 = null;
		/*if(!jsonObject.isNull("retweeted_status"))
		innerJsonObject2 = jsonObject.getJsonObject("retweeted_status");
		else continue;
		if(!innerJsonObject2.isNull("text"))
		pw.println(innerJsonObject2.getString("text"));
		else continue;
		*/
		//System.out.println(jsonObject.get("id"));
		pw.println(jsonObject.getString("text"));
		ipw.println(jsonObject.get("id"));
		tpw.println(jsonObject.get("created_at"));
		
		 
		JsonObject innerJsonObjectu = jsonObject.getJsonObject("user");
		upw.println(innerJsonObjectu.get("followers_count"));
		spw.println(innerJsonObjectu.get("statuses_count"));
		
		if(line.indexOf("retweeted_status")>=0)
		{JsonObject  innerJsonObjectid= jsonObject.getJsonObject("retweeted_status");
			
		rpw.println(innerJsonObjectid.get("retweet_count"));
		
		
		JsonObject entityJsonObjectu = innerJsonObjectid.getJsonObject("entities");
		//System.out.println(entityJsonObjectu);
		if(line.indexOf("media_url")>=0){
		InputStream fis2 = new ByteArrayInputStream(entityJsonObjectu.getJsonArray("media").toString().getBytes());
		JsonReader mediaJsonObjectu = Json.createReader(fis2);
		JsonArray array = mediaJsonObjectu.readArray();
		//System.out.println(entityJsonObjectu.getJsonArray("media"));
		//for (Object o : lineItems) {
        //JSONObject jsonLineItem = entityJsonObjectu.getJSONObject("media");
		 javax.json.JsonObject obj2 = array.getJsonObject(0);
		//mpw.println( obj2.get("expanded_url"));
		media=""+obj2.get("media_url");
		}
		else{
		//mpw.println("");
		}
		
		
		
		}
		else {
		rpw.println(0);
		//mpw.println("");
		}
	mpw.println( media);
		//JsonObject innerJsonObjectid = jsonObject.getJsonObject("");
}		
		pw.close();
		ipw.close();
		tpw.close();
		upw.close();
		rpw.close();
		spw.close();
		mpw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}