// Time Complexity: O(m*l)  where m is the number of words and l is the average length of the words
// Space Complexity: O(m*l)
class LongestWord {
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
    public String longestWord(String[] words) {
        this.root = new TrieNode();

        for(String word: words){
            insert(word);
        }

        return dfs(root, "");
    }

    private String dfs(TrieNode node, String word){
        String longest = word;

        for(int i = 0; i < 26; i++){
            if(node.children[i] != null && node.children[i].isEnd){
                String nextString = word + (char)(i + 'a');
                String candidate = dfs(node.children[i], nextString);

                if(candidate.length() > longest.length() ||
                        (candidate.length() == longest.length() && candidate.compareTo(longest) < 0)){
                    longest = candidate;
                }

            }
        }
        return longest;

    }
}