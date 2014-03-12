import java.io.*;
import java.util.*;
public class score_word{
public static void main(String args[]){
try{
String file=args[0];
String retweet_file=args[1];
String tweet_file=args[2];
String phrase_file=args[3];
String tweets_file=args[4];
String line="";
HashMap<String,Phrase> wordmap=new HashMap<String,Phrase>();
ArrayList<String> word_al=new ArrayList<String>();
BufferedReader strbr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader rebr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(retweet_file))));
BufferedReader tbr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_file))));
String reb_line="";
String tline="";

PrintWriter pw=new PrintWriter(new FileWriter(phrase_file));
PrintWriter tpw=new PrintWriter(new FileWriter(tweets_file));
while((line=strbr.readLine())!=null){
tline=tbr.readLine();
reb_line=rebr.readLine();
int reb_count=Integer.parseInt(reb_line);
StringTokenizer st=new StringTokenizer(line,",");
while(st.hasMoreTokens())
{
String str=st.nextToken();
if(str.length()<3)
continue;
if(word_al.contains(str)==false){
word_al.add(str);
Phrase phrs=new Phrase(str,reb_count,1,tline);
wordmap.put(str,phrs);
}
else{
Phrase phrs=wordmap.get(str);
phrs.setcount(reb_count);
phrs.settweetcount(1);
phrs.settweets(tline);
wordmap.put(str,phrs);
}
}

}

int i=0;
while(i<word_al.size()){
//System.out.println(word_al.get(i)+":"+wordmap.get((word_al.get(i))).getcount()+":"+wordmap.get((word_al.get(i))).gettweetcount()+":"+wordmap.get((word_al.get(i))).gettweets());

pw.println(word_al.get(i)+":"+wordmap.get((word_al.get(i))).getcount()+":"+wordmap.get((word_al.get(i))).gettweetcount());
tpw.println(wordmap.get((word_al.get(i))).gettweets());
i++;
}
pw.close();
tpw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}
class Phrase{
String str="";
String tweets="";
int count=0;
int tweetcount=0;
Phrase(String str,int count,int tweetcount,String tweets){
this.str=str;
this.count=count;
this.tweetcount=tweetcount;
this.tweets=tweets;
}
public int getcount(){
return count;
}
public void setcount(int newcount){
this.count+=newcount;
}

public int gettweetcount(){
return tweetcount;
}
public void settweetcount(int newcount){
this.tweetcount+=newcount;
}
public String gettweets(){
return tweets;
}
public void settweets(String newcount){
this.tweets=tweets+","+newcount;
}
}