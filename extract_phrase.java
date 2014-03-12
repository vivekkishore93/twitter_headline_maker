import java.io.*;
import java.util.*;

public class extract_phrase{
public static void main(String args[]){

try{
ArrayList<String> al=new ArrayList<String>();
ArrayList<tweet_infos> tweet_al=new ArrayList<tweet_infos>();

HashMap<String,Integer> mapkey=new HashMap<String,Integer>();
HashMap<String,String> tweet_word=new HashMap<String,String>();
HashMap<String,String> tweet_taggers=new HashMap<String,String>();
HashMap<String,String> time_chart=new HashMap<String,String>();
HashMap<String,String> tweet_media=new HashMap<String,String>();

String file=args[0];
String file_id=args[1];
String score_id=args[2];
String complete_tweets_ids=args[3];
String pos_tags=args[4];
String time_file=args[6];
String slot_id=args[7];
String media_file=args[8];
PrintWriter pw=new PrintWriter(new FileWriter(args[5]));
String time_line="";
String selected_time="";
BufferedReader time_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(time_file))));
while((time_line=time_br.readLine())!=null)
{
StringTokenizer st=new StringTokenizer(time_line,": ");
String str=st.nextToken();
String Time_details="";
Time_details=time_line.substring(8,22);
time_chart.put(str,Time_details);
}
PrintWriter hpw=new PrintWriter(new FileWriter("paper_.4headlines.txt",true));
int idslot=Integer.parseInt(slot_id);
selected_time=time_chart.get("slot_"+Integer.toString(idslot));
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader br_id=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file_id))));
BufferedReader score_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(score_id))));
BufferedReader comp_id=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(complete_tweets_ids))));
BufferedReader comp_pos=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(pos_tags))));
//Change2
BufferedReader me_pos=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(media_file))));
String tweets="";
String tweets_id="";

String all_ids="";
String all_pos="";
String me_line="";
String all_med="";

while((all_ids=comp_id.readLine())!=null){
all_pos=comp_pos.readLine();
all_med=me_pos.readLine();
tweet_taggers.put(all_ids,all_pos);
tweet_media.put(all_ids,all_med);
}

while((tweets=br.readLine())!=null){
tweets_id=br_id.readLine();
if(tweets_id.length()>5)
{
tweet_word.put(tweets_id,tweets);
}
}
String score_line="";

while((score_line=score_br.readLine())!=null){
String key_phrases="";
String[] al_score=score_line.split("\t");
int i=0;
String[] keylist=al_score[0].split("-");
int j=0;

while(j<keylist.length){


if((al.contains(keylist[j]))==false){

al.add(keylist[j]);
mapkey.put(keylist[j],0);
}
j++;
}


StringTokenizer st=new StringTokenizer(al_score[2],",");
int tweetcount=st.countTokens();
while(st.hasMoreTokens()){
String str=st.nextToken();
StringTokenizer tweet_st=new StringTokenizer(tweet_word.get(str),",");
while(tweet_st.hasMoreTokens()){
String keyphrase=tweet_st.nextToken();
if((al.contains(keyphrase))==false){

al.add(keyphrase);
mapkey.put(keyphrase,1);
}
else
{
int count=1+mapkey.get(keyphrase);

mapkey.put(keyphrase,count);
}
}
}
int mapcount=0;
int al_pvar=0;

while(al_pvar<al.size()){
double clus_rel=(double)mapkey.get(al.get(al_pvar))/(double)(tweetcount);

if(clus_rel>0.15)
{

key_phrases=key_phrases+al.get(al_pvar)+",";
}
al_pvar++;
}
mapkey.clear();
al.clear();

StringTokenizer tweet_st=new StringTokenizer(al_score[2],",");

while(tweet_st.hasMoreTokens())
{
String tweet_fin_id=tweet_st.nextToken();
double finsim=getSimilarity(key_phrases,tweet_word.get(tweet_fin_id));
if(finsim>0.4){
//System.out.println("\t"+al.get(al_pvar)+"\t"+clus_rel+"\t"+mapkey.get(al.get(al_pvar))+"\t"+tweetcount);
tweet_infos tinf=new tweet_infos(tweet_fin_id,finsim);
tweet_al.add(tinf);
}
}
if(tweet_al.size()>2)
{Collections.sort(tweet_al,new Comparator<tweet_infos>(){
public int compare(tweet_infos tinf1,tweet_infos tinf2){
if(tinf1.getScore()>tinf2.getScore()){
return -1;
}
return 1;
}
});

}
int fin_tweet_al_var=0;
String[] hash_al=key_phrases.split("#");
String[] comma_al=key_phrases.split(",");
if(tweet_al.size()>3 && ((comma_al.length-hash_al.length-1)>0) && (comma_al.length>4) && hash_al.length<3)
{
String main_tweet=tweet_taggers.get(tweet_al.get(0).getContent());
String headline=generate_headline.hgenerator(main_tweet);
headline=headline.replaceAll("#","");
String tweet_line="";
String img_line="";
int tweet_var=0;
while(tweet_var<tweet_al.size())
{
tweet_line=tweet_line+tweet_al.get(tweet_var).getContent()+",";
if(tweet_media.get(tweet_al.get(tweet_var).getContent()).length()>2)
img_line=img_line+tweet_media.get(tweet_al.get(tweet_var).getContent())+",";
tweet_var++;
}
if(img_line.length()>2)
img_line=img_line.substring(0,img_line.length()-1);
pw.println(selected_time+"\t"+headline+"\t"+key_phrases+"\t"+tweet_line+"\t"+img_line);
hpw.println(selected_time+"\t"+headline+"\t"+key_phrases+"\t"+tweet_line+"\t"+img_line);
}
tweet_al.clear();
}
pw.close();
hpw.close();
}
catch(Exception e){
e.printStackTrace();
}
}
public static double getSimilarity(String str1,String str2){
ArrayList<String> al1=new ArrayList<String>();
StringTokenizer st1=new StringTokenizer(str1,",");
int str1l=st1.countTokens();
while(st1.hasMoreTokens()){
String str=st1.nextToken();
if((al1.contains(str1))==false)
al1.add(str1);
}
StringTokenizer st2=new StringTokenizer(str2,",");
int str12=st2.countTokens();
int count=0;
while(st2.hasMoreTokens()){
String str2_tok=st2.nextToken();
int ivar=0;

while(ivar<al1.size()){
String str_al1=al1.get(ivar);
str_al1=str_al1.toLowerCase();
double sim=Similarity(str_al1,str2_tok);
if(sim>0.8)
{
count++;
}
ivar++;
}
}
double sim_set=(double) count/(double) (str1l+str12-count);
return sim_set;
}
public static double Similarity(String str1,String str2){
String LCS_str=LCS.printLCS(str1,str2);
int min=0;
if(str1.length()<str2.length()){
min=str1.length();
}
else min=str2.length();
double sim=(double)LCS_str.length()/(double)min;
return sim;
}
}
class tweet_infos{
String content="";
double score=0.0;
public tweet_infos(String content,double score){
this.content=content;
this.score=score;
}
public String getContent(){
return content;
}
public double getScore(){
return score;
}
public String toString(){
return content;
}
}