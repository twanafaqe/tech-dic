package dz.esi.tp1Igl.modal;

/**
 *  
 * <b>Word est la classe représentant un mot du dictionnaire</b>
 * <p>
 * Un mot du dictionnaire est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un identifiant unique attribué définitivement.</li>
 * <li>Un mot le mot au sens du mot!.</li>
 * <li>Une définition qui sert à déterminer la signification du mot.</li>
 * </ul>
 * </p>
 * 
 * @author Aissani Amina & Tedjar Nour El imane
 * @version 3.0
 *
 */

public class Word {
	
	
	/**
	 * L'idWord du Word chaque mot à un identifiant unique n'est pas modifiable.
	 * @see Word#getIdWord()
	 * @see Word#Word(int, String, String)
	 */
	private int idWord;
	/**
	 * Word est un mot qu'on cherche à définir.
	 * 
	 * @see Word#getWord()
	 * @see Word#setWord(String)
	 * @see Word#Word(int, String, String)
	 * 
	 */
	private String word;
	/**
	 * def c'est la définition du mot qui est dans word.
	 * 
	 * @see Word#getDef()
	 * @see Word#setDef(String)
	 * @see Word#Word(int, String, String)
	 */
	private String def;

	/**
	 * Constructeur de la classe Word
	 * <p> A la construction d'un objet Word on introduit en paramètres ses caractéristiques. <p>
	 * @param idWord
	 *               L'identifient unique de Word
	 * @param word
	 *               Le mot
	 * @param def
	 *               La définition du mot
	 *
	 *@see Word#idWord
	 *@see Word#word
	 *@see Word#def
	 *   
	 *@since 1.0
	 */
	public Word(int idWord, String word, String def) {
		super();
		this.idWord = idWord;
		this.word = word;
		this.def = def;
	}
	
	/**
	 * Un constructeur de la classe Word à 2 paramètres.
	 * @param word le mot de l'objet Word
	 * @param def  la définition de l'objet Word
	 * 
	 * @see Word#word
	 * @see Word#def
	 */
	public Word(String word, String def) {
		super();
		this.word = word;
		this.def = def;
	}
	
	

	/**
	 * Un constructeur de la classe Word sans paramètres.
	 */
	public Word() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retourne l'id de l'objet Word
	 * 
	 * @return l'id de l'objet Word,sous forme d'un entier.
	 */
	public int getIdWord() {
		return idWord;
	}


	/**
	 * Met à jour l'id  de l'objet Word
	 * 
	 * @param idWord   l'id de l'objet Word.
	 */
	public void setIdWord(int idWord) {
		this.idWord = idWord;
	}


	/**
	 * Retourne le mot de l'objet Word
	 * 
	 * @return le mot de l'objet Word,sous forme d'une chaine de caractères.
	 */
	public String getWord() {
		return word;
	}


	/**
	 * Met à jour du mot de l'objet Word
	 * 
	 * @param word  le mot du Word
	 */
	public void setWord(String word) {
		this.word = word;
	}


	/**
	 * Retourne la définition du word de l'objet Word
	 * 
	 * @return la définition du word de l'objet Word,sous forme d'une chaine de caractères. 
	 */
	public String getDef() {
		return def;
	}


	/**
	 * Met à jour de la définition du word de l'objet Word
	 * 
	 * @param def la définition du word de l'objet Word
	 */
	public void setDef(String def) {
		this.def = def;
	}


}
