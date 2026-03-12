package Operations;

public class SequenceGenerator {
	private static SequenceGenerator instance = new SequenceGenerator();
	private int counter = 0;
	private SequenceGenerator() {}
	public static SequenceGenerator getInstance() {
		return instance;
	}
	public String getNextSequence() {
		counter++;
		return String.valueOf(counter);
	}
}