package info.wondee.example;

@SuppressWarnings("unused")
public class BadBenchmark {

	private static final int ITERATIONS = 1000000;

	public static void main(String[] args) {
		
		long before = System.currentTimeMillis();
		
		for (int i = 0; i < ITERATIONS; i ++) {
			methodToBenchmark();
		}
		
		System.out.println(ITERATIONS + " iterations took " + (System.currentTimeMillis() - before) + " ms ");
	}
	
	private static void methodToBenchmark() {
		
		int a = 5;
		int b = 6;
		
		int sum = a + b;
	}
	
}
