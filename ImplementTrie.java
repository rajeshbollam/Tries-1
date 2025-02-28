//We implemented Trie datastructure. Each TrieNode has a TrieNode array of length and a boolean flag to mark a word at the end of it.
//We implemented insert, search and prefix search operations using Trienodes
//Time Complexity: O(l), for insert, search and prefix search where l is the 
class Trie {
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) { //O(l)
        TrieNode curr = root;
        for(int i = 0; i<word.length(); i++){
            Character ch = word.charAt(i);
            if(curr.children[ch-'a'] == null){
                curr.children[ch-'a'] = new TrieNode();
            }
            curr = curr.children[ch-'a'];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) { //O(l)
        TrieNode curr = root;
        for(int i = 0; i<word.length(); i++){
            Character ch = word.charAt(i);
            if(curr.children[ch-'a'] == null) return false;
            curr = curr.children[ch-'a'];
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) { //O(l)
        TrieNode curr = root;
        for(int i = 0; i<prefix.length(); i++){
            Character ch = prefix.charAt(i);
            if(curr.children[ch-'a'] == null) return false;
            curr = curr.children[ch-'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */