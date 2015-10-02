package Linkedin;

/*
 * Given a multi-level list, return the sum of all level depth
 * 
 *  given the list {{1,1},2,{1,1}}, return 10 
 *  (four 1's at depth 2, one 2 at depth 1).
 *  
 *  given {1,{4,{6}}}, return 27
 *   (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
 */
public class LevelDepthSum {
    
    public static int getDepthSum (String s) {
        int sum = 0;
        int depth = 0;
        int len = s.length();
        String buf = "";
        for (int i = 0; i < len; i++) {
            char key = s.charAt(i);
            switch (key) {
            case '{':
                if (buf.length() > 0) {
                    int v = Integer.parseInt(buf);
                    sum += depth*v;
                    buf = "";
                }
                depth++;
                break;
            case '}':
                if (buf.length() > 0) {
                    int v = Integer.parseInt(buf);
                    sum += depth*v;
                    buf = "";
                }
                depth--;
                break;
            case ' ':
                break;
            case ',':
                if (buf.length() > 0) {
                    int v = Integer.parseInt(buf);
                    sum += depth*v;
                    buf = "";
                }
                    
                break;
            default:
                buf += key;
                
            }
        }
        
        return sum;

    }
    
    public static void main(String args[]) {
        String s1 = "{{1,1},2,{1,1}}";
        String s2 = "{1,{4,{6}}}";
        System.out.println(getDepthSum(s1));
        System.out.println(getDepthSum(s2));
    }

}
