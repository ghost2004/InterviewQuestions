package Common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
    private static String ele[];
    private static int idx;

    private void printNode(TreeNode node, StringBuffer buf) {
        if (node == null) {
            buf.append("#,");
        } else {
            buf.append(node.val+",");
            /*
            buf.append(node.val+"(left: ");
            if (node.left!= null)
                buf.append(node.left.val);
            else
                buf.append("#");
            buf.append("  right:");
            if (node.right!= null)
                buf.append(node.right.val);
            else
                buf.append("#");
            buf.append(")");
            */
            printNode(node.left, buf);
            printNode(node.right, buf);
            
        }
    }
    
    public String serialize() {
        StringBuffer buf = new StringBuffer();
        printNode(this, buf);
        int idx = buf.length()-1;
        return buf.toString().substring(0, idx);
    }
    

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches(); 
    }
    
    public static TreeNode getFromArray() {
        
        if (idx >= ele.length)
            return null;
        TreeNode node = null;
        if (isInteger(ele[idx])) {
            node = new TreeNode(Integer.parseInt(ele[idx]));
            idx++;
            node.left = getFromArray();

            idx++;
            node.right = getFromArray();
        } 
        
        return node;        
    }
    
    public static TreeNode deserialize(String input) {
        if (input == null || input.length() == 0)
            return null;
        String stream = replaceBlank(input);
        ele = stream.split(",");
        idx = 0;
        return getFromArray();
        
        
    }
    
    public static void main(String args[]) {
        TreeNode t = TreeNode.deserialize("1,2,3,#,#,4,#,#,5");
        System.out.println(t.serialize());
        t = TreeNode.deserialize("30,20,10,#,#,25,24,23,#,#,#,27,#,#,40,35,31,#,#,37,#,#,55");
        System.out.println(t.serialize());
        
    }
}
