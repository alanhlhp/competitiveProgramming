import java.util.*;

class City{
	int id;
	int neighbor;

	public City(int id){
		this.id = id;
	}

	public City(int id, int neighbor){
		this.id = id;
		this.neighbor = neighbor;
	}
}

public class CitiesAround{

	private static Vector<Vector<City>> graph = new Vector();
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args){

		int[] T = new int[10];

		T[0] = 9;
		T[3] = 9;
		T[6] = 8;  
		T[9] = 1;
		T[1] = 1;
		T[4] = 0;
		T[7] = 9;
		T[2] = 4;
		T[5] = 4;
		T[8] = 0; 

		//solutionDFS(T);

		printArray(solutionBFS(T));


	}

	public static int[] solutionBFS(int[] T){
		int capital = 0;

		//Fill main Vector with sub-vectors
		for(int i=0; i<T.length; i++)
			graph.add(new Vector());

		for(int i=0; i<T.length; i++){
			if(T[i] != i){
				//adding the graph structure with WEIGHT instead of NEIGHBOR in class City (ALWAYS EQUALS 1)
				graph.get(T[i]).add(new City(i,1));
				graph.get(i).add(new City(T[i],1));
			} else {
				capital = i;
			}
		}

		return bfs(capital);
	}

	public static int[] bfs(int capital){
		int[] distances = new int[graph.size()];
		Arrays.fill(distances, INF);//Inizialice with INF distances array
		distances [capital] = 0; //allow to avoid visied boolean arr, trash AND inizialice distances array with capital

		LinkedList<Integer> queue = new LinkedList();
		queue.add(capital);

		while(!queue.isEmpty()){
			int v = queue.poll();
			//System.out.println("vertex: "+v);

			for(int i=0; i<graph.get(v).size(); i++){
				City neighbor = graph.get(v).get(i);

				if(distances[neighbor.id] == INF){
					distances[neighbor.id] = distances[v] + neighbor.neighbor;// neighbor weigh is always 1
					queue.add(neighbor.id);

					//System.out.println("*"+neighbor.id+" ->"+distances[neighbor.id]);
				}
			}
		}

		return calculateCities(distances);
	}

	public static int[] calculateCities(int[] distances){
		int[] R = new int[distances.length];
		for(int i=0; i<distances.length; i++){
			R[distances[i]]++;
		}

		return R;
	}

	public static void solutionDFS(int[] T){
		int[] Rdfs = new int[T.length - 1];

		for(int i=0; i<T.length; i++){
			if(T[i] == i){
				T[i] = -1;

				//DFS
				int capital = i;
				int indexR = 0;
				ArrayList<City> cities = counter(T, capital);

				Rdfs[indexR] = Rdfs[indexR] + cities.size();
				indexR++;

				for(int j=0; j<cities.size(); j++){
					ArrayList<City> adjCities = counter(T, cities.get(j).neighbor);

					if(adjCities.size() > 0){
						dfs(T, Rdfs, indexR, adjCities);
					}
				}

			}
		}

		System.out.println("DFS solution:");
		for(int c : Rdfs)
			System.out.print(c+" ");

	}

	public static void dfs(int[] T, int[] Rdfs, int indexR, ArrayList<City> cities){
		Rdfs[indexR] = Rdfs[indexR] + cities.size();

		for(int i=0; i<cities.size(); i++){
			ArrayList<City> adjCities = counter(T, cities.get(i).neighbor);

			if(adjCities.size() > 0)
				dfs(T, Rdfs, indexR+1, adjCities);
			else
				continue;
		}

	}

	public static ArrayList<City> counter(int[] T, int city){
		ArrayList<City> list = new ArrayList();

		for(int i=0; i<T.length; i++){
			if(T[i] == city){
				list.add(new City(city, i));
			}
		}

		return list;
	}

	public static void printArray(int[] arr){
		System.out.println("BFS solution:");
		for(int i=1; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}
}