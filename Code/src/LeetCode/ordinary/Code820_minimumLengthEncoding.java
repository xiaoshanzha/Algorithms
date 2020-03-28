package LeetCode.ordinary;

import java.util.Arrays;

public class Code820_minimumLengthEncoding {
    public static class TreeNode{
        TreeNode[] children;

        public TreeNode() {
            children = new TreeNode[26];
        }
    }
    public static class Trie{
        TreeNode root;

        public Trie() {
            root = new TreeNode();
        }
        public int insert(String word){
            TreeNode cur = root;
            boolean isNew = false;
            for (int i = word.length() - 1; i >= 0 ; i--) {
                int index = word.charAt(i) - 'a';
                if(cur.children[index] == null){
                    isNew = true;
                    cur.children[index] = new TreeNode();
                }
                cur = cur.children[index];
            }
            return isNew ? word.length() + 1 : 0;
        }
    }
    public int minimumLengthEncoding(String[] words) {
        int len = 0;
        Trie trie = new Trie();
        // 先对单词列表根据单词长度由长到短排序
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        // 单词插入trie，返回该单词增加的编码长度
        for (String word: words) {
            len += trie.insert(word);
        }
        return len;
    }
}

