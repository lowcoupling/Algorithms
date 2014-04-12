package tries;

import java.util.Iterator;

public class Trie {
	private TrieNode root;
	public Trie(){
		char init = '\0';
		root = new TrieNode(init);
	}
	public String toString(){
		return root.toString();
	}
	public void addWord(String word){
		if (word==null)return;
		if (word.equals(""))return;
		char[] chars = word.toLowerCase().toCharArray();
		TrieNode currentNode = root;
		char currentChar = chars[0];
		int charIndex = 0;

		//we have to traverse the tree looking for a sequence
		//of characters mapping our word until we reach a leaf
		//then we add the word
		// the word is Google
		TrieNode bestCandidate = null;
		while (charIndex<chars.length){
			try{
				Iterator<TrieNode> nodes = currentNode.getChildren().iterator();
				boolean found= false;
				while(nodes.hasNext()){
					TrieNode n = nodes.next();
					if(!n.isWord()){
						if(n.getChar()==chars[charIndex]){
							currentNode = n;
							bestCandidate = n;
							charIndex++;
							found=true;
							break;
						}
					}else{
						bestCandidate = n;
					}
				}
				if(!found)break;
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		if(bestCandidate == null){
			//we have finished the children and we have not found any valid node
			TrieNode newNode = new TrieNode(chars[charIndex]);
			currentNode.addChild(newNode);
			TrieNode wordNode = new TrieNode(word);
			newNode.addChild(wordNode);
			return;
		}else if (bestCandidate.isWord()){
			if(bestCandidate.getWord().equals(word))return;
			int tmpIndex = charIndex;
			while(tmpIndex<chars.length && tmpIndex<bestCandidate.getWord().length()){
				if(chars[tmpIndex]==bestCandidate.getWord().charAt(tmpIndex)){
					//add a further node for each equal character and push
					//the best candidate down
					TrieNode tn = new TrieNode(chars[tmpIndex]);
					tn.addChild(bestCandidate);
					currentNode.removeChild(bestCandidate);
					currentNode.addChild(tn);
					currentNode = tn;
					tmpIndex++;
				}else{
					TrieNode tn1 = new TrieNode(chars[tmpIndex]);
					tn1.addChild(new TrieNode(word));
					currentNode.addChild(tn1);

					currentNode.removeChild(bestCandidate);
					TrieNode tn2 = new TrieNode(bestCandidate.getWord().charAt(tmpIndex));
					tn2.addChild(bestCandidate);
					currentNode.addChild(tn2);
					return;
				}
			}

			//if we are here then we have finished the characters of a word
			if(tmpIndex>=chars.length && tmpIndex<bestCandidate.getWord().length()){
				//it can be the word we have to insert
				//then we have to push down the word which was here before
				currentNode.removeChild(bestCandidate);
				TrieNode tn2 = new TrieNode(bestCandidate.getWord().charAt(tmpIndex));
				tn2.addChild(bestCandidate);
				currentNode.addChild(tn2);
				currentNode.addChild(new TrieNode(word));

			}else if (tmpIndex<chars.length && tmpIndex>=bestCandidate.getWord().length()){
				//or the best candidate
				//then we have to push down the new word
				TrieNode tn1 = new TrieNode(chars[tmpIndex]);
				tn1.addChild(new TrieNode(word));
				currentNode.addChild(tn1);
			}


		}else{
			if(charIndex<chars.length){
				char c = chars[charIndex];
				TrieNode newNode = new TrieNode(c);
				currentNode.addChild(newNode);
				TrieNode wordNode = new TrieNode(word);
				newNode.addChild(wordNode);
			}
		}



	}

	public static void main(String[] args){
		Trie trie = new Trie();

		
		trie.addWord("doom");
		trie.addWord("do");

		System.out.println(trie);
	}
}
