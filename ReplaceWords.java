// Time Complexity: O(m*l) + O(n*l) where m is the number of words in the sentence and l is the average length of the words
// Space Complexity: O(m*l) + O(n*l) where n is the number of words in the dictionary and l is the average length of the words

import java.util.List;

class ReplaceWords {
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;

        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    TrieNode root;
    private void insert(String word){
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if(curr.children[ch-'a'] == null){
                curr.children[ch-'a'] = new TrieNode();
            }
            curr = curr.children[ch-'a'];
        }
        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        for(String word: dictionary){
            insert(word);
        }

        String[] strArray = sentence.split(" ");

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < strArray.length; i++){
            String currStr = strArray[i];

            TrieNode curr = root;

            StringBuilder replacement = new StringBuilder();
            // search
            for(int j = 0; j < currStr.length(); j++){
                char ch = currStr.charAt(j);

                if(curr.children[ch-'a'] == null || curr.isEnd){
                    break;
                }
                replacement.append(ch);
                curr = curr.children[ch-'a'];
            }

            if(curr.isEnd){
                result.append(replacement.toString());
            }else{
                result.append(currStr);
            }

            // Add space between words
            if (i < strArray.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
