package array;

import java.lang.management.ManagementFactory;
import java.util.Random;

import com.sun.management.ThreadMXBean;

public class RandomData {
	public static int[] generate1d(int nbVals, int min, int max) {
		int[] res = new int[nbVals];
		Random generator = new Random();
		for (int i = 0; i != nbVals; ++i) {
			res[i] = (int) (Math.abs((generator.nextLong())
					% ((long) max - min)) + min);
		}
		return res;
	}

	public static int bubbleMin(int[] a) {
		int temp;
		for (int i = 0; i < a.length; i++) {

			for (int j = i + 1; j < a.length; j++) {

				if (a[i] > a[j]) {

					temp = a[i];

					a[i] = a[j];

					a[j] = temp;

				}

			}

		}
		return a[0];
	}

	public static int Min(int[] a) {
		int temp;
		int min = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min > a[i]) {
				temp = a[i];
				a[i] = min;
				min = temp;
			}

		}
		return min;
	}

	public static int[] Selection(int[] a) {
		int[] res = new int[a.length];

		int temp;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {

				if (a[i] > a[j]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}

		}
		return res;
	}

	public static int minimumIndex(int[] data, int begin, int end) {
		int res = begin;
		for (int i = begin + 1; i != end; ++i) {
			if (data[i] < data[res]) {
				res = i;
			}
		}
		return res;
	}
	 public static void swap(int[] data, int i, int j){
		 int tmp= data[i];
		 data[i]= data[j];
		 data[j]= tmp;
		 }

	public static void sort(int[] data) {
		if (data.length < 2) {
			return;
		}
		for (int i = 0; i != data.length - 1; ++i) {
			swap(data, i, minimumIndex(data, i, data.length));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// random array
		// ThreadMXBean bean=(ThreadMXBean) ManagementFactory.getThreadMXBean();

		int[] tab = generate1d(10, 0, 50);
		// long startTime=System.nanoTime();
		// long userTime=bean.getCurrentThreadCpuTime();
		for (int i = 0; i < 10; i++) {
			System.out.println(tab[i]);
		}
		 RandomData.sort(tab);
		System.out.println("after:");
		// long endTime=System.nanoTime();
		for (int j = 0; j < tab.length; j++) {
			System.out.println(tab[j]);
		}

	}

}
