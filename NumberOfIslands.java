import java.util.*;

public class NumberOfIslands{

	public static void main(String[] args){
		char[][] map = {
							{'1', '1', '1', '1', '0'},
							{'1', '1', '1', '1', '0'},
							{'1', '1', '0', '0', '0'},
							{'0', '0', '1', '0', '0'}
						};

		System.out.println(numIslands(map));
	}

	public static int numIslands(char[][] map){
		int m = map.length;
		int n = map[0].length;

		int count = 0;

		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(map[i][j] == '1'){
					count++;
					dfs(map, i, j);
					System.out.println("i:"+ i + " - j:"+ j);
				}
			}
		}

		/*for(char[] c : map){
			for(char elem : c){
				System.out.println(elem);
			}
		}*/

		return count;

	}

	public static void dfs(char[][] map, int i, int j){
		int m = map.length;
		int n = map[0].length;

		if( i<0 || i>=m || j<0 || j>=n || map[i][j]!='1' )
			return;

		map[i][j] = 'x';

		dfs(map, i+1, j);
		dfs(map, i-1, j);
		dfs(map, i, j+1);
		dfs(map, i, j-1);
	}
}