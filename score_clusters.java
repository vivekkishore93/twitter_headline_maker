import java.io.*;
import java.util.*;
public class score_clusters{
public static void main(String args[])
{
try{
PrintWriter cmppw=new PrintWriter(new FileWriter(new File(args[1]).getParent()+"\\final_score.txt"));
HashMap<String,ArrayList<String>> map_key=new HashMap<String,ArrayList<String>>();
HashMap<String,Double> map_score=new HashMap<String,Double>();
HashMap<String,String> tweet_text_map=new HashMap<String,String>();
ArrayList<clustercmp> map_cmp=new ArrayList<clustercmp>();
ArrayList<String> tweet_uni=new ArrayList<String>();
ArrayList<String> al=new ArrayList<String>();
ArrayList<String> tweet_al=new ArrayList<String>();
//ArrayList<String> tweet_al=new ArrayList<String>();
String file=args[0]; //Keywords(syria,twer)
String tweet_file=args[1]; //tweets phrases
String tweet_id_file=args[2]; //tweet_ids
String key_clusters=args[3]; //key file
String scores_file=args[4]; //score file
String text_tweet_file=args[5]; //score file texts
String all_tweetid_file=args[6]; //id file

BufferedReader keybr=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(key_clusters))));
BufferedReader br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
BufferedReader tweet_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_file))));
BufferedReader tweet_id_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(tweet_id_file))));
BufferedReader text_id_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(text_tweet_file))));
BufferedReader all_id_br=new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(all_tweetid_file))));
String line="";
String all_id_line="";
String tweet_text_line="";
while((all_id_line=all_id_br.readLine())!=null)
{
tweet_text_line=text_id_br.readLine();
tweet_text_map.put(all_id_line,tweet_text_line);
}

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

if(map_key.containsKey(al.get(alvar))==false)
{
map_key.put(al.get(alvar),new ArrayList<String>());
StringTokenizer st=new StringTokenizer(tweet_id_line,",");
while(st.hasMoreTokens()){
String str=st.nextToken();
if((map_key.get(al.get(alvar)).contains(str))==false)
map_key.get(al.get(alvar)).add(str);
}
}
else
{

StringTokenizer st=new StringTokenizer(tweet_id_line,",");
while(st.hasMoreTokens()){
String str=st.nextToken();
if((map_key.get(al.get(alvar)).contains(str))==false)
map_key.get(al.get(alvar)).add(str);
}
}



}
alvar++;
}
}
String keyline="";
map_score.putAll(score_normal.mainscore(scores_file));
while((keyline=keybr.readLine())!=null)
{
StringTokenizer keyarr=new StringTokenizer(keyline,"-");
double max=-1.0;
int maxtweets=0;
double max_rel=-1.0;
while(keyarr.hasMoreTokens())
{
String keystr=keyarr.nextToken();
//System.out.println(keystr);
if(max<map_score.get(keystr))
{
max=map_score.get(keystr);
}
}
tweet_uni.addAll(common_tweets.extract_common(keyline,tweet_file,tweet_id_file));
int i=0;
	while(i<al.size())
	{	
	int common_count=common_tweets.count_common_tweets(map_key.get(al.get(i)),tweet_uni);
	double relevance=(double)(common_count)/(double)(map_key.get(al.get(i)).size());
	if(max_rel<relevance)
	{max_rel=relevance;
	maxtweets=i;
	}
	//System.out.println(al.get(i)+"\t"+relevance+"\t"+common_count);
	i++;
	}
double clus_score=max_rel*Math.exp(-1*max);
//System.out.println(clus_score);
String common_str=common_tweets.write_common_tweets(map_key.get(al.get(maxtweets)),tweet_uni);
clustercmp cmp1=new clustercmp(keyline,clus_score,common_str);

map_cmp.add(cmp1);


tweet_uni.clear();
}
Collections.sort(map_cmp,new Comparator<clustercmp>(){
public int compare(clustercmp c1,clustercmp c2)
{
if(c1.getScore()>c2.getScore())
return -1;
return 1;

}


});

int cmp_var=0;
while(cmp_var<map_cmp.size())
{
System.out.println(cmp_var);
String tweets_list="";
int tweet_var=0;
String[] tweet_arr=map_cmp.get(cmp_var).gettweets().split(",");
ArrayList<String> tweet_un_id=new ArrayList<String>();
while(tweet_var<tweet_arr.length)
{
	int tweet_in_var=tweet_var+1;
	
	
	while(tweet_in_var<tweet_arr.length)
	{
	//int val=diff_lib(tweet_arr[tweet_var],tweet_arr[tweet_in_var]);
	//int val=Math.random();
	int l1=tweet_text_map.get(tweet_arr[tweet_var]).length();
	int l2=tweet_text_map.get(tweet_arr[tweet_in_var]).length();
	
	String lcs=LCS.printLCS(tweet_text_map.get(tweet_arr[tweet_var]),tweet_text_map.get(tweet_arr[tweet_in_var]));
	int length=lcs.length();
	int min=0;
	if(l1>=l2)
	min=l1;
	else
	min=l2;
	
	
	double simscore=(double)length/(double)min;
	//System.out.println((tweet_arr[tweet_var])+"\t"+(tweet_arr[tweet_in_var])+"\t"+simscore+"\t"+length+"\t"+min);
	double thresh_val=0.6;
	if(simscore>thresh_val){
	if(tweet_un_id.contains(tweet_arr[tweet_in_var])==false)
	tweet_un_id.add(tweet_arr[tweet_in_var]);
	}
	
	tweet_in_var++;
	}
	
tweet_var++;
//cmppw.println(map_cmp.get(cmp_var).getText()+"\t"+map_cmp.get(cmp_var).getScore()+"\t"+map_cmp.get(cmp_var).gettweets());
}
tweet_var=0;
String tweet_line1="";
	while(tweet_var<tweet_arr.length)
	{
	if(tweet_un_id.contains(tweet_arr[tweet_var])==false)
	{
	tweet_line1=tweet_line1+tweet_arr[tweet_var]+",";
	}
	tweet_var++;
	}
tweet_un_id.clear();
cmppw.println(map_cmp.get(cmp_var).getText()+"\t"+map_cmp.get(cmp_var).getScore()+"\t"+tweet_line1);
cmp_var++;
}
cmppw.close();
}





catch(Exception e){
e.printStackTrace();
}
}
}
class clustercmp{
String text="";
String tweets="";
double score=0.0;
clustercmp(String text,double score,String tweets)
{
this.text=text;
this.tweets=tweets;
this.score=score;
}
public String getText()
{
return text;
}

public double getScore()
{
return score;
}

public String gettweets()
{
return tweets;
}




}
