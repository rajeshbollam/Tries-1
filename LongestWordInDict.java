//In this approach, we make a trie out of all the words in the dictionary for efficient look up and we perform DFS using backtracking to traverse the trie.
//When traversing, we maintain a stringbuilder which appends all the values of nodes that we traverse.
//We also maintain a global maxStr string which always has the maximum length string that we find.
//We only traverse through the nodes which have isEnd as true.
//Time Complexity: O(Nl) where N is the number of words in dictionary and l is the average length of a word
//Space Complexity: O(Nl) for trie
class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(){
            this.isEnd = false;
            children = new TrieNode[26];
        }
    }

    private void insert(TrieNode root, String word){
        TrieNode curr = root;
        for(int i = 0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isEnd = true;
    }

    String maxStr = "";
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for(String word: words){ // N*l
            insert(root, word);
        }
        
        StringBuilder path = new StringBuilder();
        backtrack(root, path);
        
        return maxStr;
    }

    private void backtrack(TrieNode curr, StringBuilder path){
        //base
        if(path.length() > maxStr.length()){
            maxStr = path.toString();
        }

        //logic
        for(int i = 0; i<=25; i++){
            if(curr.children[i] != null && curr.children[i].isEnd){
                //action
                char ch = (char) ('a' + i);
                int le = path.length();
                path.append(ch);
                //recurse
                backtrack(curr.children[i], path);
                //backtrack
                path.setLength(le);
            }
            
        }

    }
}
