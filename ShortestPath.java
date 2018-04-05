import java.util.*;

class ShortestPath{
	
	public static void main(String[] args){
		int graph[][] = new int[][]{
									{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                  	{4, 0, 8, 0, 0, 0, 0, 11, 0},
                                  	{0, 8, 0, 7, 0, 4, 0, 0, 2},
                                  	{0, 0, 7, 0, 9, 14, 0, 0, 0},
                                  	{0, 0, 0, 9, 0, 10, 0, 0, 0},
                                  	{0, 0, 4, 14, 10, 0, 2, 0, 0},
                                  	{0, 0, 0, 0, 0, 2, 0, 1, 6},
                                  	{8, 11, 0, 0, 0, 0, 1, 0, 7},
                                  	{0, 0, 2, 0, 0, 0, 6, 7, 0}
								   };

		printPath(dijkstra(graph, 0));

	}

	static int[] dijkstra(int[][] graph, int src){
		int v = graph[0].length;
		int[] dist = new int[v];
		Arrays.fill(dist, Integer.MAX_VALUE); //Fill distances array with INFINITE

		boolean[] visited = new boolean[v];
		Arrays.fill(visited, false);

		dist[src] = 0; 

		for(int i=0; i<v-1; i++){
			int min = minDistance(dist, visited); //Pick minimun distance vertex from set of vertices not yet processed

			visited[min] = true;

			//Update the dist value of the adjacent vertices of the picked vertex
			for(int j=0; j<v; j++){

				// Update dist[j] only if is not in visited, 
				//If there is an edge from min to j, 
				// and if total weight of path from src to v through u is smaller than current value of dist[j]
				if(!visited[j] && graph[min][j]!=0 && 
					dist[min]!=Integer.MAX_VALUE && dist[min]+graph[min][j] < dist[j]){

					dist[j] = dist[min] + graph[min][j];
				}
			}
		}

		return dist;
	}

	static int minDistance(int[] dist, boolean[] visited){
		int min = Integer.MAX_VALUE;
		int min_index = -1;

		for(int i=0; i<dist.length; i++){
			if(visited[i] == false && dist[i] < min){
				min = dist[i];
				min_index = i;
			}
		}

		return min_index;
	}

	static void printPath(int[] distances){
		System.out.println("Vertex distace from Source");
		for(int i=0; i<distances.length; i++)
			System.out.println(i + " tt " +distances[i]);
	}

}