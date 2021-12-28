package savethebunniesclient.model.game;

/** 
 * Symbol enumeration.
 * sAn enumeration representing the pair "ASCII symbol - image source" for each piece (and status).
 * @author David García Solórzano
 * @version 1.0  
 */
public enum Symbol {

	BUNNY_WHITE('w',"bunny-white.png"),
	BUNNY_WHITE_HOLE('W',"bunny-white-hole.png"),
	BUNNY_GRAY('g',"bunny-gray.png"),
	BUNNY_GRAY_HOLE('G',"bunny-gray-hole.png"),
	BUNNY_BROWN('b',"bunny-brown.png"),
	BUNNY_BROWN_HOLE('B',"bunny-brown-hole.png"),
	FOX_HEAD_UP('^',"fox-head-up.png"),
	FOX_TAIL_UP('⊥',"fox-tail-up.png"),
	FOX_HEAD_RIGHT('>',"fox-head-right.png"),
	FOX_TAIL_RIGHT('⊢',"fox-tail-right.png"),
	FOX_HEAD_DOWN('V',"fox-head-down.png"),
	FOX_TAIL_DOWN('T',"fox-tail-down.png"),
	FOX_HEAD_LEFT('<',"fox-head-left.png"),
	FOX_TAIL_LEFT('⊣',"fox-tail-left.png"),
	MUSHROOM('M',"mushroom.png"),
	HOLE('H',"hole.png"),
	GRASS('#',"grass.png");
	
	/**
	 * ASCII character of the symbol
	 */
	private char ascii;
	/**
	 * Image source of the symbol
	 */
	private String imageSrc;
	
	/**
	 * Constructor
	 * @param ascii The ASCII character of the symbol.
	 * @param imageSrc Name/Source of the image of the symbol.
	 */
	private Symbol(char ascii, String imageSrc){
		this.ascii = ascii;
		this.imageSrc = imageSrc;
	}
	
	/**
	 * Given an ASCII character, it returns the name of the symbol. 
	 * @param ascii ASCII character of the symbol.
	 * @return It returns the name of the symbol from an ASCII character. If the ASCII character does not exist, then it returns null.
	 */
	public static Symbol getName(char ascii) {
		for(var s : Symbol.values()) {			
			if(s.getAscii() == ascii) {				
				return s;
			}
		}
		return null;
	}
	
	/**
	 * The ASCII character of the symbol (field "ascii").
	 * @return The ASCII character of the symbol.
	 */
	public char getAscii() {
		return ascii;
	}
	
	/**
	 * The image of the symbol (field "image").
	 * @return The image source of the symbol.
	 */
	public String getImageSrc() {
		return imageSrc;
	}
	
	/**
	 * It returns the ASCII character of the symbol.
	 * @return the ASCII character of the symbol.
	 */
	@Override
	public String toString() {
		return String.valueOf(ascii);
	}
}