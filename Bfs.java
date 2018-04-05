import java.util.*;
import java.io.*;

class Bfs{
	private int vertices;
	private LinkedList<Integer> adj[];

	public static void main(String args[]){
		Bfs g = new Bfs(10);

		/*g.addEdge(0,1);
		g.addEdge(0,2);
		g.addEdge(1,2); 
		g.addEdge(2,0);
		g.addEdge(2,3);
		g.addEdge(3,3);*/

		g.addEdge(0,9);
		g.addEdge(0,8);
		g.addEdge(0,4);
		g.addEdge(1,9);
		g.addEdge(2,4);
		g.addEdge(3,9);
		g.addEdge(4,5);
		g.addEdge(6,8);
		g.addEdge(7,9);       

		System.out.println("Breadth first search starting from vertex 2");

		g.BFS(1);
	}

	Bfs(int v){
		vertices = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; i++)
			adj[i] = new LinkedList();
	}

	void addEdge(int v, int w){
		adj[v].add(w);
	}

	void BFS(int s){

		boolean visited[] = new boolean[vertices];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		while( queue.size() != 0){
			s = queue.poll(); //devuelve el primero y lo elimina
			System.out.println(s+" ");

			Iterator<Integer> i = adj[s].listIterator();
			while(i.hasNext()){
				int n = i.next();
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
}

