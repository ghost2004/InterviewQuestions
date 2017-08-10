package Common;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    
    private class NodeTrack {
        TreeNode node;
        int idx;
    }
    public void printTree() {
        List<List<String>> out = this.printTree(this);
        for (List<String> list:out) {
            for (String str:list) {
                System.out.print(str);
            }
            System.out.println();
        }
    }
    public  List<List<String>> printTree(TreeNode root) {
        
        List<List<String>> out = new ArrayList<List<String>>();
        
        if (root == null)
            return out;
        
        List<List<NodeTrack>> track = new  ArrayList<List<NodeTrack>>();
        LinkedList<NodeTrack> cur = new LinkedList<NodeTrack>();
        
        
        NodeTrack first = new NodeTrack();
        first.node = root;
        first.idx = 0;
    

        cur.offer(first);
        
        while (!cur.isEmpty()) {

            LinkedList<NodeTrack> curLevel = new LinkedList<NodeTrack>();
            LinkedList<NodeTrack> next = new LinkedList<NodeTrack>();

            while (!cur.isEmpty()) {
                NodeTrack tnode = cur.poll();
                curLevel.add(tnode);
                NodeTrack t; 
                if (tnode.node.left != null) {
                    t = new NodeTrack();
                    t.node = tnode.node.left;
                    t.idx = tnode.idx * 2;
                    next.offer(t);
                }
                if (tnode.node.right != null) {
                    t = new NodeTrack();
                    t.node = tnode.node.right;
                    t.idx = tnode.idx * 2 + 1 ;
                    next.offer(t);
                }
            }
            track.add(curLevel);
            cur = next;
        }
        
        int column = (1 << track.size()) -1;
        for (int i = 0 ; i < track.size(); i++) {
            List<NodeTrack> curLevel = track.get(i);
            ArrayList<String> level = new ArrayList<String>(column);
            for (int j = 0; j < column; j++) {
                level.add("  ");
            }
            
            int start = (1 << (track.size() - i -1)) - 1;
            int jump = 1 << (track.size() - i);
            for (NodeTrack t : curLevel) {
                level.set(start + t.idx * jump, new Integer(t.node.val).toString());
            }
            
            out.add(level);
        }
        
        return out;
    }
    
    public static void main(String args[]) {
        TreeNode t = TreeNode.deserialize("1,2,3,#,#,4,#,#,5");
        System.out.println(t.serialize());
        t = TreeNode.deserialize("30,20,10,#,#,25,24,23,#,#,#,27,#,#,40,35,31,#,#,37,#,#,55");
        System.out.println(t.serialize());
        
    }
}
