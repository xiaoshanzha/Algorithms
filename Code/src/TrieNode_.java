public class TrieNode_ {
    public static class TrieNode{
        public int path;
        public int end;
        public TrieNode[] map;

        public TrieNode() {
            path = 0;
            end = 0;
            map = new TrieNode[26]; //表示“A-Z”26条璐
        }
    }
    public static class Trie{
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String word) {
            if(word == null){
                return;
            }
            TrieNode node = root;
            char[] chars = word.toCharArray();
            node.path++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.map[index] == null){
                    node.map[index] = new TrieNode();
                }
                node = node.map[index];
                node.path++;
            }
            node.end++;
        }

        public boolean search(String word) {
            if(word == null){
                return false;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.map[index] == null){
                    return false;
                }
                node = node.map[index];
            }
            return node.end != 0;
        }

        public void delete(String word) {
            if(search(word)){
                TrieNode node = root;
                char[] chars = word.toCharArray();
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if(node.map[index].path-- == 1){
                        node.map[index] = null;
                        return;
                    }
                    node = node.map[index];
                }
                node.end--;
            }
        }
        public int prefixNum(String pre) {
            if(pre == null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            int index = 0;
            TrieNode node = root;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.map[index] == null){
                    return 0;
                }
                node = node.map[index];
            }
            return node.path;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));
        System.out.println(trie.prefixNum("zuo"));

    }

}
