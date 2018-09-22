package huffmanAlgorithm;

import java.io.*;
import java.util.*;



public class HuffmanEncoder implements HuffmanCoding {

	ArrayList<Character> chars = new ArrayList<Character>();
	ArrayList<Integer> frequency = new ArrayList<Integer>();

	public static void main(String[] args) {
		HuffmanEncoder test = new HuffmanEncoder();
		HuffTree ht = new HuffTree();

		File inputFile = new File("C:\\Users\\bhanu\\Desktop\\inputFile.txt");
		//test.getFrequencies(inputFile);
		ht = test.buildTree(inputFile);
		//test.encodeFile(inputFile, ht);
		//test.decodeFile("011011000010110111111110011101010000010011000000011110011111111101101100110010111101011001011010011000000110010001011011100010000110000110100010010000110000101101001011010011011110110011011101000010000011101111100001110100110001000100111110111010111101001101100111111011010101010001000101100010011010001100100101011001010001111110000100100010000011110101101101010001110110000010010010100010011011010011001101110111111001011000111100101101111111101000000110111011100100011001010101010010001010111001111", ht);
		test.traverseHuffmanTree(ht);
		//test.decodeFile("00", ht);
		//test.encodeFile(inputFile, ht);
		//test.traverseHuffmanTree(ht);
	}

	// take a file as input and create a table with characters and frequencies
	// print the characters and frequencies

	public String getFrequencies(File inputFile) {
		HashMap<Integer, Integer> hash = new LinkedHashMap<>();// created a hashmap that is used to store the characters and their frequencies. used linked hashmap to preserve the order of keys and values

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));// reads in file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (true) {

			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line == null) {
				break;
			}

			for (int i = 0; i < line.length(); i++) {// adds the chars to the hashtable based on the ascii value of the chars
				char c = line.charAt(i);
				if (c != ' ') {
					int value = hash.getOrDefault((int) c, 0);
					hash.put((int) c, value + 1);
				} else if (c == ' ') {
					int value = hash.getOrDefault((int) c, 0);
					hash.put((int) c, value + 1);
				}
			}
		}

		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>(hash); // used to sort the hashmap for ascii values
		String s = "";
		Integer[] key = map.keySet().toArray(new Integer[0]);// creates an array of keys from hash
		Integer[] value = map.values().toArray(new Integer[0]);// creates an array of values from hash
		
		for(int i = 0; i < hash.size(); i++) {
			int d = key[i];
			int e = value[i];
			frequency.add(i, e); //add frequnecies to an arraylist
			chars.add(i,(char) d);// add chars to another arrylist
			s += chars.get(i) + " " + frequency.get(i) + "\n";// save to a string for output
			
		}
		
        System.out.println(s);// print
		return s;

	}
	
	// take a file as input and create a Huffman Tree
	// hopefully works
	public HuffTree buildTree(File inputFile) {
		String s = "";
		s = getFrequencies(inputFile);// gets the frequencies via a string
		
		PriorityQueue<HuffTree> queue = new PriorityQueue<HuffTree>();// creates a priorityqueue to add the nodes of the tree
		for (int i = 0; i < chars.size(); i++) {// adds the nodes of the tree to the queue
			HuffTree n = new HuffTree(chars.get(i), frequency.get(i), null, null);
			queue.add(n);
		}
		
		HuffTree ht = null;
		while (queue.size() > 1) {// performs huffman algorithm where 2 nodes are removed from the queue and combined and then added back into the queue until only a tree is left
			HuffTree least1 = queue.poll();
			HuffTree least2 = queue.poll();
			HuffTree combined = new HuffTree();
			combined.frequency = least1.frequency + least2.frequency;
			combined.text = '-';
			combined.left = least1;
			combined.right = least2;
			least1.parent = combined;
			least2.parent = combined;
			queue.add(combined);
			ht = combined;
		}
		return ht;// returns the huffman tree
	}

	// take a file and a HuffTree and encode the file.
	// output a string of 1's and 0's representing the file
	public String encodeFile(File inputFile, HuffTree huffTree) {
		
		Scanner scan = null;
		try {
			scan = new Scanner(inputFile);// reads in file
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String code = "";
		scan.useDelimiter("");
		Hashtable<Character, String> hash = getTable(huffTree);// creates a hashtable that has the values
		encodeRecur(huffTree, hash, "");// calls encodeRecur class
		while (scan.hasNext()) {
			code += hash.get(scan.next().charAt(0));// adds the binary code  to code for each character
		}
		
		System.out.println(code);// prints code
		return code;

	}
	
	public Hashtable<Character, String> getTable(HuffTree ht) {
		String e = "";
		Hashtable<Character, String> hash = new Hashtable<Character,String>();// creates a hashtable
		encodeRecur(ht, hash,e);// calls encodeRecur
		return hash;
		
	}
	public void encodeRecur(HuffTree ht, Hashtable<Character, String> hash, String c) {

		if (ht.left == null && ht.right == null) {// if the node is a leaf, it inserts into the hashtable
			hash.put(ht.text, c);
		} else {
			encodeRecur(ht.left, hash, c + "0");// recurs for the left branch
			encodeRecur(ht.right, hash, c + "1");// recurs for the right branch
		}
			
	}

	// take a String and HuffTree and output the decoded words
	public String decodeFile(String code, HuffTree huffTree) {
		StringBuilder output = new StringBuilder();// creates a string to add the characters to
	    HuffTree hft = huffTree;// creates a new huffTree
	    for (int i = 0; i < code.length(); i++) {// adds the character to output from the node based on the binary code
	        hft = code.charAt(i) == '1' ? hft.right : hft.left;
	        if (hft.isLeaf(hft)) {
	            output.append(hft.text);
	            hft = huffTree;
	        }
	    }
	    System.out.print(output);// prints the output
		String s = "";
		s =  output.toString();
		return s;
	
	}

	// print the characters and their codes
	public String traverseHuffmanTree(HuffTree huffTree) {
		String s = "";
		s = printTree(huffTree, s);// calls printTree class
		return s;
	}

	public static String printTree(HuffTree ht, String s) {
		ArrayList <Character> arr = new ArrayList<Character>(); 
		char e;
		if (ht.isLeaf(ht)) {// if the node is a leaf the string is added to an arraylist 
			//System.out.println(ht.text + " " + s);
			e = ht.text ;
			arr.add(e);
		}else {
		printTree(ht.left, s + "0");// recurs to the left branch
		printTree(ht.right, s + "1");// recurs to the right branch
		}
		
		Collections.sort(arr);// supposed to sort the array
		for(int i = 0; i< arr.size();i++) {
			
			System.out.println(arr.get(i));// prints each element of the array
		}
		return s;
	}

}
