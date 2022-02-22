package com.tremorinternational.kickstart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleFinder {
    /* roles to keywords list */

    private static Map<String, List<String>> roleToKeywords = new HashMap<>();

    /* default roles */

    static {
        roleToKeywords.put("CTO", Arrays.asList("engineering","managing","software","development","organic","design","leader","building"));
        roleToKeywords.put("CPO", Arrays.asList("Product", "development", "experience","managing","design","leader","building","marketing"));
        roleToKeywords.put("CMO", Arrays.asList("leader","building","marketing","sports","leadership","Index","corporate","expertise"));
        roleToKeywords.put("CEO", Arrays.asList("digital","Matomy","media","start-up","public","leader"));
    }
    /* utility functions */

    private static boolean searchString(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            for (int n = 0; n < str2.length(); n++) {
                if (str1.charAt(i) == str2.charAt(n)) {
                    i++;
                    if (n + 2 == str2.length()) return true;
                }
                else break;
            }
        }

        return false;
    }

    private static boolean multiSearch(String str1, List<String> str2) {
        float found = 0;
        int max = 0;

        for (int i = 1; i <= str2.size(); i++)
            max += i;

        for (int i = 0; i < str2.size(); i++)
            if (searchString(str1, str2.get(i))) found += 8 - (i + 1);

        return found / max >= 0.5;
    }

    private static boolean multiSearch(String str1, List<String> str2, float confidence) {
        float found = 0;
        int max = 0;

        for (int i = 1; i <= str2.size(); i++)
            max += i;

        for (int i = 0; i < str2.size(); i++)
            if (searchString(str1, str2.get(i))) found += 8 - (i + 1);

        return found / max >= confidence;
    }

    /* gets role of string */

    public static String getRoleOfString(String data) {
        for (String key : roleToKeywords.keySet())
            if (multiSearch(data, roleToKeywords.get(key))) return key;
        return null;

    }
    /* get/set roles for future use */

    /* gets list */
    public Map<String, List<String>> getRoleToKeywords() { return roleToKeywords; }

    /* get value in list */
    public List<String> getRole(String key) { return roleToKeywords.get(key); }

    /* adds role */
    public void addRole(String key, List<String> value) { roleToKeywords.put(key,value); }

    /* removes role */
    public void removeRole(String key) { roleToKeywords.remove(key); }

    /* replaces role */
    public void setKey(String key, List<String> value) { roleToKeywords.replace(key, value); }
}
