package info.wondee.example;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import info.wondee.example.SortBenchmak.ListHolder;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations=3)
@Measurement(iterations=3)
@Fork(value=2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SortBenchmak {

	@State(Scope.Benchmark)
	public static class ListHolder {
		List<Integer> list;
		
		@Setup(Level.Trial)
		public void setup() {
			
			list = Arrays.asList(3, 12, 46, 1, 35, 4, 77, 24, 62, 5, 2, 9, 64, 6, 34, 85, 357, 53, 257, 353, 4);
		}
		
		public List<Integer> arrayList() {
			return new ArrayList<>(list);
		}
		
		public List<Integer> linkedList() {
			return new LinkedList<>(list);
		}
	}
	
	@Benchmark
	public List<Integer> testSortJava(ListHolder listHolder) {
		List<Integer> list = listHolder.arrayList();
		Collections.sort(list);
		return list;
	}
	
	@Benchmark
	public List<Integer> testSortJavaLinked(ListHolder listHolder) {
		List<Integer> list = listHolder.linkedList();
		Collections.sort(list);
		return list;
	}
	
	@Benchmark
	public List<Integer> testSortOwn(ListHolder listHolder) {
		List<Integer> list = listHolder.arrayList();
		ownSort(list);
		return list;
	}
	
	
	static <T extends Comparable<T>> void ownSort(List<T> list) {
		if (list.size() < 2) return;
		
		boolean changed = false;
		
		for (int i = 0; i < list.size() || !changed ; i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					T greater = list.set(j, list.get(j + 1));
					list.set(j + 1, greater);
					changed = true;
				}
			}
		}
	}

	@Test
	public void testOwnSort() throws Exception {

		List<Integer> list = Arrays.asList(4, 2, 6, 1, 8);
		
		ownSort(list);
		
		assertEquals(Arrays.asList(1, 2, 4, 6, 8), list);
		
	}
	
}
