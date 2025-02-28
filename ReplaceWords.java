//The approach here is to keep all the words in the dictionary into a trie for efficient look up of smaller prefix words
//For every word in the given sentence, we check in the trie, if a smaller prefix exists and add it to the result if exists, else add the word by itself to the result.
//Time Complexity: O((m+n)*l), where m is the number of words in the string, n is the length of dictionary and l is the average length of each word in string and dictionary
//Space Complexity: O(n*l)
import java.util.List;

class Solution {
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }

    public void insert(String word) {
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

    private TrieNode root;

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        for(String word: dictionary){//O(n*l)
            insert(word);
        }
        StringBuilder result = new StringBuilder();
        //Split the sentence
        String[] strArr = sentence.split(" ");
        for(int i = 0; i < strArr.length; i++){
            if(i!=0){
                result.append(" ");
            }
            String currWord = strArr[i];
            //Search for a smaller prefix
            TrieNode curr = root;
            StringBuilder replacement = new StringBuilder();
            for(int j =0; j<currWord.length(); j++){
                Character ch = currWord.charAt(j);
                if(curr.children[ch-'a'] == null || curr.isEnd) break;
                replacement.append(ch);
                curr = curr.children[ch-'a'];
            }
            if(curr.isEnd){
                result.append(replacement.toString());
            } else {
                result.append(currWord);
            }

        }
        return result.toString();
    }
}
