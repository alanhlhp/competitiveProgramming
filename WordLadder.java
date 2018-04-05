import java.util.*;

class WordNode{
	String word;
	int numSteps;

	public WordNode(String word, int numSteps){
		this.word = word;
		this.numSteps = numSteps;
	}
}

public class WordLadder{

	public static void main(String[] args){

		Set<String> dict = new HashSet();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");

		System.out.println(ladderLength("hit", "cog", dict));

	}	

	//BFS using class WordNode
	public static int ladderLength(String beginWord, String endWord, Set<String> dict){
		LinkedList<WordNode> queue = new LinkedList();
		queue.add(new WordNode(beginWord, 1));

		dict.add(endWord);

		while(queue.size() != 0){
			WordNode top = queue.poll();
			String word = top.word;

			if(word.equals(endWord))
				return top.numSteps;

			char[] arr = word.toCharArray();

			for(int i = 0; i<arr.length; i++){
				for(char c='a'; c<='z'; c++){
					char temp = arr[i];

					if(arr[i]!=c)
						arr[i] = c;

					String newWord = new String(arr);
					if(dict.contains(newWord)){
						queue.add(new WordNode(newWord, top.numSteps + 1));
						dict.remove(newWord);
						//System.out.println(newWord);
					}

					arr[i] = temp;

				}
			}
		}

		return 0;
	}	
	
}