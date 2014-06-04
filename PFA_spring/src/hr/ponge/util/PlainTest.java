package hr.ponge.util;


public class PlainTest {

	public static void main(String[] args) {
		PlainTest pt = new PlainTest();
		pt.test();
	}

	public void test() {
		String a = "cb9f8757753afdbdb08485b280dea68e";
		if (a.matches("[a-fA-F0-9]{32}")) {
			System.err.println("OK");
		} else {
			System.err.println("NOK");
		}
	}
}
