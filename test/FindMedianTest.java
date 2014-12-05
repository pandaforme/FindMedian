
import java.util.Comparator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindMedianTest {

	@Test
	public void testPeekMedian() throws Exception {
		FindMedian<Integer> queue = new FindMedian<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		queue.enqueue(7);
		queue.enqueue(13);
		queue.enqueue(2);
		queue.enqueue(6);
		queue.enqueue(9);

		int median = queue.peekMedian();
		Assert.assertEquals(7, median);
	}

}
