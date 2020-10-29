package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution20 {

	/**
     * 953. 验证外星语词典 
     * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
     * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回false。
     * 
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }
        Map<Character, Integer> orderMap = new HashMap<Character, Integer>();
        for (int i = 0, len = order.length(); i < len; i++) {
            orderMap.put(order.charAt(i), i + 1);
        }
        for (int i = 0; i < words.length - 1; i++) {
            int len1 = words[i].length();
            int len2 = words[i + 1].length();
            int len = Math.max(len1, len2);
            for (int j = 0; j < len; j++) {
                int order1 = j >= len1 ? 0 : orderMap.get(words[i].charAt(j));
                int order2 = j >= len2 ? 0 : orderMap.get(words[i + 1].charAt(j));
                if (order1 < order2) {
                    break;
                }
                if (order1 > order2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Solution20 s = new Solution20();
        System.out.println(s.isAlienSorted(new String[]{"fxasxpc","dfbdrifhp","nwzgs","cmwqriv","ebulyfyve","miracx","sxckdwzv","dtijzluhts","wwbmnge","qmjwymmyox"},"zkgwaverfimqxbnctdplsjyohu"));
    }
}
