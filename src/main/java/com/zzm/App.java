package com.zzm;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String s = "pwwkew";
        int ans = 0;
        HashMap<Character,Integer> resMap = new HashMap();
        for (int j= 0,i = 0 ;j< s.length(); j++){
            if (resMap.containsKey(s.charAt(j))) {
                i = Math.max(resMap.get(s.charAt(j)),i);
            }
            ans = Math.max(ans,j-i+1);
            resMap.put(s.charAt(j),j+1);
        }


        System.out.println(ans);
    }
}
