
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedian<E> {
	private Queue<E>		queue;
	private Queue<E>		maxHeap;
	private Queue<E>		minHeap;
	private Comparator<E>	comparator;

	public FindMedian(Comparator<E> comparator) {
		queue = new LinkedList<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder(comparator));
		minHeap = new PriorityQueue<>(comparator);
		this.comparator = comparator;
	}

	public void enqueue(E e) {
		if (e == null)
			throw new IllegalArgumentException();

		this.queue.add(e);

		addElementToHeap(e);

		rebalance();
	}

	public E dequeue() {
		E removeObject = this.queue.remove();
		this.maxHeap.remove(removeObject);
		this.minHeap.remove(removeObject);

		return removeObject;
	}

	public E peekMedian() {
		if (maxHeap.size() == minHeap.size()) {
			// Depends on definition
			return maxHeap.peek();
		}
		else if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		}
		else {
			return minHeap.peek();
		}
	}

	private void addElementToHeap(E e) {
		E tmp = maxHeap.peek();
		if (tmp == null) {
			minHeap.add(e);
		}
		else if (comparator.compare(tmp, e) > 0) {
			maxHeap.add(e);
		}
		else {
			minHeap.add(e);
		}
	}

	private void rebalance() {
		int sizeDiff = maxHeap.size() - minHeap.size();
		if (sizeDiff > 1) {
			minHeap.add(maxHeap.remove());
		}
		else if (sizeDiff < -1) {
			maxHeap.add(minHeap.remove());
		}
	}
}
