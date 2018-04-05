import java.util.*;

public class Search2DMatrix{

	public static void main(String[] args){
		int[][] matrix = {
							{1, 3, 5, 7},
							{10, 11, 16, 20},
							{23, 30, 34, 50}
						 };

		//System.out.println("Sorted: "+ searchSortedMatrix(matrix, 3));

		int[][] partial = {
							{1,   4,  7, 11, 15},
							{2,   5,  8, 12, 19},
							{3,   6,  9, 16, 22},
							{10, 13, 14, 17, 24},
							{18, 21, 23, 26, 30}
						  };

		//System.out.println("Partial: "+ searchPartialMatrix(partial, 20));

		int[] unsorted = {5, 1, 7, 3};

		int low = 0;
		int high = unsorted.length-1;

		quickSort(unsorted, low, high);

	}

	//Binary search with sorted 2d array
	public static boolean searchSortedMatrix(int[][] matrix, int target){
		if(matrix==null || matrix.length==0 || matrix[0].length==0)
			return false;

		int m = matrix.length;
		int n = matrix[0].length;

		//System.out.println("m:"+m+" - n:"+n);

		int start = 0;
		int end = m*n-1;

		while(start <= end){
			int mid = (start+end)/2;
			int midX = mid/n;
			int midY = mid%n;

			//System.out.println("Mid:"+mid+" - X:"+midX+" - Y:"+midY+" - VALUE:"+matrix[midX][midY]);

			if(matrix[midX][midY] == target)
				return true;

			if(matrix[midX][midY] < target)
				start = mid + 1;
			else
				end = mid - 1;
		}

		return false;
	}

	//Binary search with partial sorted 2d array
	public static boolean searchPartialMatrix(int[][] matrix, int target){
		int m = matrix.length-1;
		int n = matrix[0].length-1;

		int i = m;
		int j = 0;

		while(i>=0 && j<=n){
			if(target < matrix[i][j])
				i--;
			else if(target > matrix[i][j])
				j++;
			else
				return true;	
		}

		return false;
	}

	public static void quickSort(int[] array, int low, int high){
		if(array == null || array.length == 0)
			return;

		if(low >= high)
			return;

		//Choose pivot
		int middle = low + (high - low) / 2;
		int pivot = array[middle];

		int i = low;
		int j = high;

		while(i <= j){
			while(array[i] < pivot){
				i++;
			}

			while(array[i] > pivot){
				j--;
			}

			if(i <= j){
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}

		if(low < j)
			quickSort(array, low, j);
		if(high > i)
			quickSort(array, i, high);
	}

}