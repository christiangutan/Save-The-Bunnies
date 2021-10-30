package savethebunniesserver.util;

public class Util {
	public static  int [] conversorStringToIntArrayLevels (String chainFromDataBase) {
		String[] chain = chainFromDataBase.split(";");
		int [] intChain = new int[chain.length];
		for (int i = 0; i < chain.length ; i++) {
			intChain[i] = Integer.parseInt(chain[i]);
		}
		return intChain;
	}
}
