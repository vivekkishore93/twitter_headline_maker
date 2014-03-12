import java.io.*;
import java.util.*;
public class print_pattern{
public static void main(String args[])
{
try{
PrintWriter pw=new PrintWriter(new FileWriter("phrase.bat"));
int i=1;
while(i<81)
{
String slot="slot_"+i;
String slot_prev="slot_"+(i-1);
//pw.println("java score_word D:\\null\\Slots_new\\"+slot+"\\keyphrases.txt"+" D:\\null\\Slots_new\\"+slot+"\\tweets_retwwetcount.txt D:\\null\\Slots_new\\"+slot+"\\tweets_tid.txt D:\\null\\Slots_new\\"+slot+"\\phrases.txt D:\\null\\Slots_new\\"+slot+"\\tweets.txt");
//pw.println("java score_vivek D:\\null\\Slots_new\\"+slot+"\\phrases.txt"+" D:\\null\\Slots_new\\"+slot_prev+"\\phrases.txt"+" D:\\null\\Slots_new\\"+slot+"\\phrase_score.txt"+" D:\\null\\Slots_new\\"+slot+"\\tweets.txt");

//pw.println("java authorize_phrases D:\\null\\Slots_new\\"+slot+"\\tweets_tid.txt"+" D:\\null\\Slots_new\\"+slot+"\\tweets_user.txt"+" D:\\null\\Slots_new\\"+slot+"\\auth_phrases.txt"+" D:\\null\\Slots_new\\"+slot+"\\out3.txt"+" D:\\null\\Slots_new\\"+slot+"\\tweets_statuscount.txt"+" D:\\null\\Slots_new\\"+slot+"\\out2.txt"+" D:\\null\\Slots_new\\"+slot+"\\out4.txt");
//pw.println("java gen_keyword_tweets keywords.txt D:\\null\\Slots_new\\"+slot+"\\out4.txt"+" D:\\null\\Slots_new\\"+slot+"\\auth_phrases.txt"+" D:\\null\\Slots_new\\"+slot+"\\final_tweets_reduce.txt"+" D:\\null\\Slots_new\\"+slot+"\\final_ids_reduce.txt");
//pw.println("java score_clusters keywords.txt   D:\\null\\output\\"+slot+"\\out2.txt"+" D:\\null\\output\\"+slot+"\\out3.txt"+" D:\\null\\output\\"+slot+"\\result_files\\thresh_results\\0.5\\new_Queries.txt"+" D:\\null\\output\\"+slot+"\\phrase_score.txt"+" D:\\null\\output\\"+slot+"\\tweets_text.txt"+" D:\\null\\output\\"+slot+"\\tweets_tid.txt");
pw.println("java extract_phrase D:\\null\\output\\"+slot+"\\keyphrases.txt"+" D:\\null\\output\\"+slot+"\\tweets_tid.txt"+" D:\\null\\output\\"+slot+"\\final_score.txt D:\\tweets_tid.txt t_eval.txt D:\\null\\output\\"+slot+"\\headlines.txt D:\\null\\Slots_new\\slot_1\\time_chart.txt "+i+" D:\\tw\\media.txt");
i++;
}
pw.close();
}
catch(Exception e){
e.printStackTrace();
}

}
}