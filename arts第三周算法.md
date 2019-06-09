## Longest Substring Without Repeating Characters

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
         int ans = 0;
        HashMap<Character,Integer> resMap = new HashMap();
        for (int j= 0,i = 0 ;j< s.length(); j++){
            if (resMap.containsKey(s.charAt(j))) {
                i = Math.max(resMap.get(s.charAt(j)),i);
            }
            ans = Math.max(ans,j-i+1);
            resMap.put(s.charAt(j),j+1);
        }

        return ans;
    }
}
```

