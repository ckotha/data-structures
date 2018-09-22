package sparseMatrix;

import java.util.*;

public class SparseMatrix implements SparseInterface {
	ArrayList<arrList> sparseMatrix = new ArrayList<arrList>(); // The arrayList of arrList elements is declared
	int rowSize; // The max size of the array is set here
	int colSize;

	SparseMatrix() {

	}

	SparseMatrix(int rowSize1, int colSize1) {// the constructor initializes the arrayList and the maxSize
												// variable

		rowSize = rowSize1;
		colSize = colSize1;
	}

	public void clear() { // clears the arrayList
		sparseMatrix.clear();
	}

	public void addElement(int row, int col, int data) {
		if (data != 0) {
			if (row < rowSize && col < colSize) {// checks if the row and column are in bounds
				for (int i = 0; i < sparseMatrix.size(); i++) {// runs through the array

					arrList cur = sparseMatrix.get(i);// stores the current element

					if (cur.getRow() > row) {// if the cur row is greater than the target row, we add into the index
												// before
						sparseMatrix.add(i, new arrList(data, row, col));
						return;
					} else if (cur.getRow() == row && cur.getCol() > col) {// if the cur row is equal to the target row,
																			// and the cur column is greater than the
																			// target column
						sparseMatrix.add(i, new arrList(data, row, col));
						return;
					} else if (cur.getRow() == row && cur.getCol() == col) {// if the cur row is equal to the target
																			// row, and the cur column is equal to the
																			// // target column
						cur.setData(data);
						return;
					}
				}
				sparseMatrix.add(new arrList(data, row, col));// add the new element to the sparseMatrix
			} else {
				throw new IndexOutOfBoundsException();// throw an exception
			}
		} else {
			removeElement(row, col);
		}
	}

	public void removeElement(int row, int col) {
		if (row < rowSize && col < colSize) {// checks if the row and column are in bounds
			for (int i = 0; i < sparseMatrix.size(); i++) {// loops through array
				if (sparseMatrix.get(i).getRow() == row && sparseMatrix.get(i).getCol() == col) {// if the target row
																									// and column are
																									// found, then
																									// remove the
																									// element
					sparseMatrix.remove(i); //
				}
			}
		} else {
			throw new IndexOutOfBoundsException();// throw exception
		}
	}

	public int getElement(int row, int col) {
		int x = 0;
		if (row < rowSize && col < colSize) {// checks if the row and column are in bounds
			for (int i = 0; i < sparseMatrix.size(); i++) {// loops through array
				if (sparseMatrix.get(i).getRow() == row && sparseMatrix.get(i).getCol() == col) {// if the target row
																									// and column are
																									// found, then set
																									// element equal to
																									// x
					x = sparseMatrix.get(i).getData();
				}
			}
		} else {
			throw new IndexOutOfBoundsException();// throw exception
		}
		return x;// return x
	}

	public String toString() {// prints to the console
		String printOut = "";
		for (int i = 0; i < sparseMatrix.size(); i++) {// loops through the array
			arrList temp = sparseMatrix.get(i);// make s a temp of the elements in the arrayList
			printOut += (temp.getRow() + " " + temp.getCol() + " " + temp.getData() + "\n");// saves the output to
																							// printOut
		}
		return printOut;// returns the string of info
	}

	@Override
	public void setSize(int numRows, int numCols) {// sets the size of the matrix using the row and column sizes
		rowSize = numRows;
		colSize = numCols;
	}

	@Override
	public int getNumRows() {// returns the number of rows in a matrix
		return rowSize;
	}

	@Override
	public int getNumCols() {// returns the number of columns in a matrix
		return colSize;
	}

	@Override
	public SparseInterface addMatrices(SparseInterface matrixToAdd) {
		SparseInterface newMatrix = new SparseMatrix(rowSize, colSize); // creates a new sparseInterface object to store the added values
		if (matrixToAdd.getNumCols() == colSize && matrixToAdd.getNumRows() == rowSize) {// checks if the matrix are the same sizes 
			for (int i = 0; i < rowSize; i++) {// iterates over the loop
				for (int j = 0; j < colSize; j++) {
					newMatrix.addElement(i, j, ((getElement(i, j) + matrixToAdd.getElement(i, j))));// adds the element in the ijth position of each matrix into the newMatrix
				}
			}
			return newMatrix;//returns the newMatrix
		} else {
			return null;
		}
	}

	@Override
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
		if (getNumCols() == matrixToMultiply.getNumRows()) {// checks if the the number of columns of the first matrix equal the number of rows of the second matrix
			SparseInterface newMatrix = new SparseMatrix(getNumRows(), matrixToMultiply.getNumCols());// creates a sparseInterface object to store the resulting matrix
			for (int i = 0; i < rowSize; i++) {// iterates through the rows of the first matrix
				for (int j = 0; j < matrixToMultiply.getNumCols(); j++) {// iterates through the columns of the second matrix
					for (int k = 0; k < rowSize; k++) {// Iterates through the individual elements of each matrix
						int sum = getElement(i, k) * matrixToMultiply.getElement(k, j);// multiplies the 2 values from each matrix
						newMatrix.addElement(i, j, newMatrix.getElement(i, j) + sum);// sums the values to complete the dot product
					}
				}
			}
			return newMatrix;//returns the matrix
		} else {
			return null;
		}
	}
}
