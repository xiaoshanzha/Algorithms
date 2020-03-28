package LeetCode.ordinary;

public class Code208_Trie {
    public static class TreeNode{
        TreeNode[] children ;
        int end;
        public TreeNode(){
            children = new TreeNode[26];
        }
    }
    private TreeNode root;
    public Code208_Trie() {
        root = new TreeNode();
    }

    public void insert(String word) {
        if(word == null){
            return;
        }
        TreeNode node = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i] - 'a';
            if(node.children[index] == null){
                node.children[index] = new TreeNode();
            }
            node = node.children[index];
        }
        node.end ++;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word == null){
            return false;
        }
        TreeNode node = root;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i] - 'a';
            if(node.children[index] == null){
                return false;
            }
            node = node.children[index];
        }
        return node.end != 0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix == null){
            return false;
        }
        TreeNode node = root;
        char[] words = prefix.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int index = words[i] - 'a';
            if(node.children[index] == null){
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
}

