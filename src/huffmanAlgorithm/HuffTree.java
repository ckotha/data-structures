
package huffmanAlgorithm;

class HuffTree implements Comparable<HuffTree> {

	HuffTree left;
	HuffTree right;
	HuffTree parent;
	char text;
	int frequency;
// constructor for HuffTree
	public HuffTree(char textIn, int frequencyIn, HuffTree leftChild, HuffTree rightChild) {
		text = textIn;
		frequency = frequencyIn;
		left = leftChild;
		right = rightChild;

	}

	public HuffTree(char textIn, int frequencyIn) {
		text = textIn;
		frequency = frequencyIn;
	}

	public boolean isLeaf(HuffTree ht) {
		return (ht.right == null && ht.left == null);
	}

	public HuffTree(int frequencyIn) {
		text = '\0';
		frequency = frequencyIn;
	}

	public HuffTree() {

	}

	public int compareTo(HuffTree hft) {
		return frequency - hft.frequency;
	}

	// getters and setters for text, frequency, left and right
	public HuffTree getLeft() {
		return left;
	}

	public void setLeft(HuffTree left) {
		this.left = left;
	}

	public HuffTree getRight() {
		return right;
	}

	public void setRight(HuffTree right) {
		this.right = right;
	}

	public HuffTree getParent() {
		return parent;
	}

	public void setParent(HuffTree parent) {
		this.parent = parent;
	}

	public char getText() {
		return text;
	}

	public void setText(char text) {
		this.text = text;
	}

	public int getFrequency() {
		return frequency;
	}

	public String toString(HuffTree ht) {
		String s = "";
		if (ht.isLeaf(ht)) {
			System.out.println(ht.text + "\n");
			s += ht.getText() + "\n";
		} else if (ht.getLeft() != null) {
			s += ht.getLeft().toString(ht);
		} else if (ht.getRight() != null) {
			s += ht.getRight().toString(ht);
		}
		return s;

	}

}