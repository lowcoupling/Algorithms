package tries;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void doomNothing() {
		Trie trie = new Trie();
		trie.addWord("doom");
		trie.addWord("");
		assertEquals("empty insertion","( (d(doom)))",trie.toString());
	}
	
	@Test
	public void doomNull() {
		Trie trie = new Trie();
		trie.addWord("doom");
		trie.addWord(null);
		assertEquals("null insertion","( (d(doom)))",trie.toString());
	}
	
	@Test
	public void dotDoom() {
		Trie trie = new Trie();
		trie.addWord("dot");
		trie.addWord("doom");
		assertEquals("smaller first","( (d(o(o(doom))(t(dot)))))",trie.toString());
	}
	
	@Test
	public void doomDot() {
		Trie trie = new Trie();
		trie.addWord("doom");
		trie.addWord("dot");
		assertEquals("bigger first","( (d(o(t(dot))(o(doom)))))",trie.toString());
	}
	
	
	@Test
	public void doDoom() {
		Trie trie = new Trie();
		trie.addWord("do");
		trie.addWord("doom");
		assertEquals("smaller equal first","( (d(o(do)(o(doom)))))",trie.toString());
	}
	
	@Test
	public void doomDo() {
		Trie trie = new Trie();
		trie.addWord("doom");
		trie.addWord("do");
		assertEquals("smaller equal after","( (d(o(o(doom))(do))))",trie.toString());
	}
	
	@Test
	public void doorDoom() {
		Trie trie = new Trie();
		trie.addWord("door");
		trie.addWord("doom");
		
		assertEquals("equal size small difference","( (d(o(o(m(doom))(r(door))))))",trie.toString());
	}

}
