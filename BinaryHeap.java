
public class BinaryHeap {
	
	private int[] arr;
	private int size;
	
	public BinaryHeap() {
		arr = new int[10];
		size = 0;
	}

	public void add(int i) {
		// if the array is full
		if (arr.length==size) {
			// increase the size of the array
			growHeap();
		}
		// add i to end of array
		arr[size] = i;
		// increment size
		size++;
		
		int index = size-1;
		// if parent of i is larger then swap and keep going
		while (index>0 && arr[index]<arr[parent(index)]) {
			swap(index, parent(index));
			index = parent(index);
		}
		
	}
	
	public int remove() {
		int temp = arr[0];
		// set the first value to the last value
		// and decrement size
		arr[0] = arr[--size];
		// swap the new first value with values
		// that are lower than it by bubbling
		// down the "tree"
		bubbleDown(0);
		// return the original value that was removed
		return temp;
	}

	private void growHeap() {
		// create a new heap that is twice the current size
		int[] temp = new int[arr.length*2];
		// copy old data to the temp array
		System.arraycopy(arr, 0, temp, 0, arr.length);
		// set arr to temp (larger array)
		arr = temp;
	}
	
	private void swap(int a, int b) {
		// swap values at index a and index b
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private void bubbleDown(int index) {
		// Base Case: if there is a left child to index
		// check left and not right because in a priority
		// heap/queue we fill left before right
		if (leftChild(index)<size) {
			// set child to the left child
			int child = leftChild(index);
		
			// if there is a right child to index
			if (rightChild(index)<size) {
				// compare the left and right child
				// and if right is less than left,
				// then set child to right child
				if (arr[rightChild(index)]<arr[child]) {
					child = rightChild(index);
				}
			}
			
			// if the index is greater than child then 
			// swap the index and child
			if (arr[index]>arr[child]) {
				swap(index, child);
			}
			// recursively call bubbleDown on
			// the child (now contains value that was 
			// at index)
			bubbleDown(child);
		}
	}
	
	private int parent(int index) {
		// return the parent of index 
		// (when index != 0 because if index == 0 then
		// the parent will return as 0)
		// also assuming that index<size && index>0
		return (index-1)/2;
	}
	
	private int leftChild(int index) {
		// return the left child of index
		// (does not check if the return value
		// is greater or equal to size, so the index
		// returned may not exist)
		// assuming that index<size && index>=0
		return (index*2)+1;
	}
	
	private int rightChild(int index) {
		// return the right child of index
		// (does not check if the return value
		// is greater or equal to size, so the index
		// returned may not exist)
		// assuming that index<size && index>=0
		return (index*2)+2;
	}
	
}
