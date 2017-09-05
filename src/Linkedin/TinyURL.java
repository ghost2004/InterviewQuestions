package Linkedin;
/*
 * How would you design a URL shortening service that is similar to TinyURL?

Background:
TinyURL is a URL shortening service where you enter a URL such as 
https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Requirements:
For instance, "http://tinyurl.com/4e9iAk" is the tiny url for the page 
"https://leetcode.com/problems/design-tinyurl". The identifier (the highlighted part) 
can be any string with 6 alphanumeric characters containing 0-9, a-z, A-Z.
Each shortened URL must be unique; that is, no two different URLs can be shortened to the same URL.
 */
import java.util.*;

public class TinyURL {
    HashMap<String, Integer> longToShort;
    HashMap<Integer, String> shortToLong;
    int counter;
    static final String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String BASE_URL = "http://tiny.url/";
    static final String BASE_URL_NOT_FOUND = "http://tiny.url/404";
    public TinyURL() {
        longToShort = new HashMap<>();
        shortToLong = new HashMap<>();
        counter = 1;
    }
    
    private String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n != 0) {
            sb.append(elements.charAt(n % 62));
            n /= 62;
        }
        
        while (sb.length() != 6) 
            sb.append('0');
        return sb.toString();
    }
    
    private int convert(char c) {
        if (c >= '0' && c <= '9')
            return c-'0';
        else if (c >= 'a' && c <= 'z')
            return c-'a'+10;
        else if (c >= 'A' && c <= 'Z')
            return c-'A'+36;
        
        return -1;
    }
    
    private int base62ToBase10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = convert(s.charAt(i));
            if (idx == -1)
                return -1;
            n = n*62 + idx;
        }
        return n;
    }
    
    public String urlToTiny(String url) {
        String tiny = base10ToBase62(counter);
        longToShort.put(url, counter);
        shortToLong.put(counter, url);
        return BASE_URL+tiny;
    }
    
    public String tinyToURL(String tiny) {
        String t = tiny.substring(BASE_URL.length());
        int idx = base62ToBase10(t);
        
        if (idx == -1)
            return BASE_URL_NOT_FOUND;
        String u = shortToLong.get(idx);
        
        if (u == null)
            return BASE_URL_NOT_FOUND;
        
        return u;
    }
    

}
