package tries;

import java.util.ArrayList;
import java.util.Iterator;

public class TrieNode {
	private ArrayList<TrieNode> children;
	private final Character nodeC;
	private final String nodeS;
	
	public TrieNode(Character c){
		nodeS = null;
		nodeC = c;
		children = new ArrayList<TrieNode>();
	}
	public TrieNode(String str){
		nodeC = null;
		nodeS = str;
	}
	public String toString(){
		String ret = "(";
		if(isWord()){
			ret=ret+nodeS;
		}else{
			ret=ret+nodeC;
			Iterator<TrieNode> it = children.iterator();
			while(it.hasNext()){
				TrieNode n = it.next();
				ret=ret+n.toString();
			}
		}
		ret=ret+")";
		return ret;
	}
	public boolean isWord(){
		return nodeS!=null;
	}
	public Character getChar(){return nodeC;}
	public String getWord(){return nodeS;}
	public ArrayList<TrieNode> getChildren(){return children;}
	public void addChild(TrieNode node){
		if(!isWord()&&children!=null){
			this.children.add(node);
		}
	}
	public void removeChild(TrieNode node){
		if(!isWord()&&children!=null){
			this.children.remove(node);
		}
	}
}
