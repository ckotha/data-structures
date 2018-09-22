package sortingAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

	void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}
	 
	public int[] printArray(int arr[])
	    {
	        int n = arr.length;
	        for (int i=0; i<n; ++i)
	            System.out.print(arr[i] + " ");
	        System.out.println();
			return arr;
	    }

	public int[] fileToArray(File file) {
		/*Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		List<Integer> lines = new ArrayList<Integer>();
		while (sc.hasNextLine()) {
		  lines.add(Integer.parseInt(sc.nextLine()));
		}
		int[] ret = new int[lines.size()];
		for(int i = 0;i < ret.length;i++)
		    ret[i] = lines.get(i);
		 
		return ret;*/
		
		List<Integer> list = new ArrayList<Integer>();
		BufferedReader reader = null;

		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;

		    while ((text = reader.readLine()) != null) {
		        list.add(Integer.parseInt(text));
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
		int[] ret = new int[list.size()];
		  for(int i = 0;i < ret.length;i++)
		    ret[i] = list.get(i);
		  return ret;
	}

	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void sort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("***********Bubble Sort*****************");
		   File file1 = new File("C:\\test\\inputFile5000Random.txt");
		   main trial1 = new main();
		   int[] arr1 = null;
		   arr1 = trial1.fileToArray(file1);
		   Long b1=System.currentTimeMillis();
		   trial1.bubbleSort(arr1);
		   Long a1=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a1-b1)+" ms"+ " for " + "5000 Random");
		   
		   File file2 = new File("C:\\test\\inputFile5000Ascend.txt");
		   main trial2 = new main();
		   int[] arr2 = null;
		   arr2 = trial2.fileToArray(file2);
		   Long b2=System.currentTimeMillis();
		   trial2.bubbleSort(arr2);
		   Long a2=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a2-b2)+" ms"+ " for " + "5000 Ascending");
		   
		   File file3 = new File("C:\\test\\inputFile5000Descend.txt");
		   main trial3 = new main();
		   int[] arr3 = null;
		   arr3 = trial3.fileToArray(file3);
		   Long b3=System.currentTimeMillis();
		   trial3.bubbleSort(arr3);
		   Long a3=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a3-b3)+" ms"+ " for " + "5000 Descending");
		   
		   File file4 = new File("C:\\test\\inputFile10000Random.txt");
		   main trial4 = new main();
		   int[] arr4 = null;
		   arr4 = trial4.fileToArray(file4);
		   Long b4=System.currentTimeMillis();
		   trial4.bubbleSort(arr4);
		   Long a4=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a4-b4)+" ms"+ " for " + "10000 Random");
		   
		   File file5 = new File("C:\\test\\inputFile10000Ascend.txt");
		   main trial5 = new main();
		   int[] arr5 = null;
		   arr5 = trial5.fileToArray(file5);
		   Long b5=System.currentTimeMillis();
		   trial5.bubbleSort(arr5);
		   Long a5=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a5-b5)+" ms"+ " for " + "10000 Ascending");
		   
		   File file6 = new File("C:\\test\\inputFile10000Descend.txt");
		   main trial6 = new main();
		   int[] arr6 = null;
		   arr6 = trial6.fileToArray(file6);
		   Long b6=System.currentTimeMillis();
		   trial6.bubbleSort(arr6);
		   Long a6=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a6-b6)+" ms"+ " for " + "10000 Descending");
		   
		   File file7 = new File("C:\\test\\inputFile20000Random.txt");
		   main trial7 = new main();
		   int[] arr7 = null;
		   arr7 = trial7.fileToArray(file7);
		   Long b7=System.currentTimeMillis();
		   trial7.bubbleSort(arr7);
		   Long a7=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a7-b7)+" ms"+ " for " + "20000 Random");
		   
		   File file8 = new File("C:\\test\\inputFile20000Ascend.txt");
		   main trial8 = new main();
		   int[] arr8 = null;
		   arr8 = trial8.fileToArray(file8);
		   Long b8=System.currentTimeMillis();
		   trial8.bubbleSort(arr8);
		   Long a8=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a8-b8)+" ms"+ " for " + "20000 Ascending");
		   
		   File file9 = new File("C:\\test\\inputFile20000Descend.txt");
		   main trial9 = new main();
		   int[] arr9 = null;
		   arr9 = trial9.fileToArray(file9);
		   Long b9=System.currentTimeMillis();
		   trial9.bubbleSort(arr9);
		   Long a9=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a9-b9)+" ms"+ " for " + "20000 Descending");

		   
		   File file10 = new File("C:\\test\\inputFile50000Random.txt");
		   main trial10 = new main();
		   int[] arr10 = null;
		   arr10 = trial10.fileToArray(file10);
		   Long b10=System.currentTimeMillis();
		   trial10.bubbleSort(arr10);
		   Long a10=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a10-b10)+" ms"+ " for " + "50000 Random");
		   
		   File file11 = new File("C:\\test\\inputFile50000Ascend.txt");
		   main trial11 = new main();
		   int[] arr11 = null;
		   arr11 = trial11.fileToArray(file11);
		   Long b11=System.currentTimeMillis();
		   trial11.bubbleSort(arr11);
		   Long a11=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a11-b11)+" ms"+ " for " + "50000 Ascending");
		   
		   File file12 = new File("C:\\test\\inputFile50000Descend.txt");
		   main trial12 = new main();
		   int[] arr12 = null;
		   arr12 = trial12.fileToArray(file12);
		   Long b12=System.currentTimeMillis();
		   trial12.bubbleSort(arr12);
		   Long a12=System.currentTimeMillis(); 
		   System.out.println("Bubble sort takes "+(a12-b12)+" ns"+ " for " + "50000 Descending");
		   
		   
		   System.out.println("***********Merge Sort*****************");
		   File file13 = new File("C:\\test\\inputFile5000Random.txt");
		   main trial13 = new main();
		   int[] arr13 = null;
		   arr13 = trial13.fileToArray(file13);
		   Long b13=System.nanoTime();
		   trial13.sort(arr13, 0, arr13.length - 1);
		   Long a13=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a13-b13)+" ns"+ " for " + "5000 Random");
		   
		   File file14 = new File("C:\\test\\inputFile5000Ascend.txt");
		   main trial14 = new main();
		   int[] arr14 = null;
		   arr14 = trial14.fileToArray(file14);
		   Long b14=System.nanoTime();
		   trial14.sort(arr14, 0, arr14.length - 1);
		   Long a14=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a14-b14)+" ns"+ " for " + "5000 Ascending");
		   
		   File file15 = new File("C:\\test\\inputFile5000Descend.txt");
		   main trial15 = new main();
		   int[] arr15 = null;
		   arr15 = trial15.fileToArray(file15);
		   Long b15=System.nanoTime();
		   trial15.sort(arr15, 0, arr15.length - 1);
		   Long a15=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a15-b15)+" ns"+ " for " + "5000 Descending");
		   
		   File file16 = new File("C:\\test\\inputFile10000Random.txt");
		   main trial16 = new main();
		   int[] arr16 = null;
		   arr16 = trial16.fileToArray(file16);
		   Long b16=System.nanoTime();
		   trial16.sort(arr16, 0, arr16.length - 1);
		   Long a16=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a16-b16)+" ns"+ " for " + "10000 Random");
		   
		   File file17 = new File("C:\\test\\inputFile10000Ascend.txt");
		   main trial17 = new main();
		   int[] arr17 = null;
		   arr17 = trial17.fileToArray(file17);
		   Long b17=System.nanoTime();
		   trial17.sort(arr17, 0, arr17.length - 1);
		   Long a17=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a17-b17)+" ns"+ " for " + "10000 Ascending");
		   
		   File file18 = new File("C:\\test\\inputFile10000Descend.txt");
		   main trial18 = new main();
		   int[] arr18 = null;
		   arr18 = trial18.fileToArray(file18);
		   Long b18=System.nanoTime();
		   trial18.sort(arr18, 0, arr18.length - 1);
		   Long a18=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a18-b18)+" ns"+ " for " + "10000 Descending");
		   
		   File file19 = new File("C:\\test\\inputFile20000Random.txt");
		   main trial19 = new main();
		   int[] arr19 = null;
		   arr19 = trial19.fileToArray(file19);
		   Long b19=System.nanoTime();
		   trial19.sort(arr19, 0, arr19.length - 1);
		   Long a19=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a19-b19)+" ns"+ " for " + "20000 Random");
		   
		   File file20 = new File("C:\\test\\inputFile20000Ascend.txt");
		   main trial20 = new main();
		   int[] arr20 = null;
		   arr20 = trial20.fileToArray(file20);
		   Long b20=System.nanoTime();
		   trial20.sort(arr20, 0, arr20.length - 1);
		   Long a20=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a20-b20)+" ns"+ " for " + "20000 Ascending");
		   
		   File file21 = new File("C:\\test\\inputFile20000Descend.txt");
		   main trial21 = new main();
		   int[] arr21 = null;
		   arr21 = trial21.fileToArray(file21);
		   Long b21=System.nanoTime();
		   trial21.sort(arr21, 0, arr21.length - 1);
		   Long a21=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a21-b21)+" ns"+ " for " + "20000 Descending");
		   
		   File file22 = new File("C:\\test\\inputFile50000Random.txt");
		   main trial22 = new main();
		   int[] arr22 = null;
		   arr22 = trial22.fileToArray(file22);
		   Long b22=System.nanoTime();
		   trial22.sort(arr22, 0, arr22.length - 1);
		   Long a22=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a22-b22)+" ns"+ " for " + "50000 Random");
		   
		   File file23 = new File("C:\\test\\inputFile50000Ascend.txt");
		   main trial23 = new main();
		   int[] arr23 = null;
		   arr23 = trial23.fileToArray(file23);
		   Long b23=System.nanoTime();
		   trial23.sort(arr23, 0, arr23.length - 1);
		   Long a23=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a23-b23)+" ns"+ " for " + "50000 Ascending");
		   
		   File file24 = new File("C:\\test\\inputFile50000Descend.txt");
		   main trial24 = new main();
		   int[] arr24 = null;
		   arr24 = trial24.fileToArray(file24);
		   Long b24=System.nanoTime();;
		   trial24.sort(arr24, 0, arr24.length - 1);
		   Long a24=System.nanoTime(); 
		   System.out.println("Merge sort takes "+(a24-b24)+" ns"+ " for " + "50000 Descending");
	}

}
