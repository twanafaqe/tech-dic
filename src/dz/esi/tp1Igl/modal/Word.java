package dz.esi.tp1Igl.modal;

public class Word {
	
	private int idWord;
	private String word;
	private String def;
	
	

	public Word(int idWord, String word, String def) {
		super();
		this.idWord = idWord;
		this.word = word;
		this.def = def;
	}
	
	public Word(String word, String def) {
		super();
		this.word = word;
		this.def = def;
	}
	
	public Word(){
		
		
	}

	public int getIdWord() {
		return idWord;
	}


	public void setIdWord(int idWord) {
		this.idWord = idWord;
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public String getDef() {
		return def;
	}


	public void setDef(String def) {
		this.def = def;
	}


}
