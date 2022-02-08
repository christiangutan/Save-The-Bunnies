package savethebunniesserver.util;

/**
 * Util class
 * @author christian_gutan
 *
 */
public class Util {
	public static  int [] conversorStringToIntArrayLevels (String chainFromDataBase) {
		String[] chain = chainFromDataBase.split(";");
		int [] intChain = new int[chain.length];
		for (int i = 0; i < chain.length ; i++) {
			if(chain[i].equals("")){
				intChain[i] = 0;
			} else {
				intChain[i] = Integer.parseInt(chain[i]);
			}
			
		}
		return intChain;
	}
}
