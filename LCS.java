
import java.io.*;
public class LCS {

    public static String printLCS(String x,String y) {
        //String x = "ATGAKCAA";
        //String y = "AAGKCAA";
    	 String LCS_str="";
    	 String LCS_val="";
    	int l=0;
    	
        int M = x.length();
        int N = y.length();

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[M+1][N+1];
        
        // compute length of LCS and all subproblems via dynamic programming
        for (int i = M-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (x.charAt(i) == y.charAt(j))
                    {opt[i][j] = opt[i+1][j+1] + 1;
                    //System.out.print(x.charAt(i));
                    }
                else 
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                
                
            }
            //System.out.println();
        }
       
        // recover LCS itself and print it to standard output
        int i = 0, j = 0;
        while(i < M && j < N) {
            if (x.charAt(i) == y.charAt(j)) {
                //System.out.print(x.charAt(i));
                LCS_str=LCS_str+x.charAt(i);
            	i++;
                j++;
            }
            else if (opt[i+1][j] >= opt[i][j+1]) i++;
            else                                 j++;
        }
        
        //System.out.print(opt[0][0]+"  ");
        LCS_val=Integer.toString(opt[0][0]);
    
    return LCS_str;
    }
    

}
class LCS_store{
private static int LCScount;
private static String LCSstr;
public static int getLCScount() {
	return LCScount;
}
public static void setLCScount(int lCScount) {
	LCScount = lCScount;
}
public static String getLCSstr() {
	return LCSstr;
}
public static void setLCSstr(String lCSstr) {
	LCSstr = lCSstr;
}


}
