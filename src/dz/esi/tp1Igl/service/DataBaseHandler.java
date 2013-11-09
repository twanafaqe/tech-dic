package dz.esi.tp1Igl.service;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import dz.esi.tp1Igl.modal.Word;


/**
 * <b>DataBaseHandler permet de cr�er les tables dans la base de donn�es (BD).</b>
 * <b>Les tables cr�es dans la BD sont les suivants : </b>
 * <lu>
 * <li>TABLE_FRENCH </li>
 * <li>TABLE_ENGLISH</li>
 * <li>TABLE_COUNT</li>
 * </lu>
 *  
 * @author Aissani Amina & Tedjar Nour El Imane
 * 
 * @version 3.0
 *
 */

@SuppressLint("NewApi")
public class DataBaseHandler extends SQLiteOpenHelper {

        
        /**
         * DATABASE_VERSION contient le num�ro de la version de la BD.
         */
        private static final int DATABASE_VERSION = 1;

        
        /**
         * DATABASE_NAME par lequelle on attribue un nom � la BD.
         */
        private static final String DATABASE_NAME = "dB1";

        /**
         * <p>TABLE_FRENCH La table qui va contenir les Words en fran�ais.</p>
         * <p>Cette table est caract�ris�e par: </p>
         * <lu>
         * <li> KEY_ID_WORD 
         * @see DataBaseHandler#KEY_ID_WORD </li>
         * <li> KEY_WORD 
         * @see DataBaseHandler#KEY_WORD </li>
         * <li> KEY_DEF
         * @see DataBaseHandler#KEY_DEF </li>
         * @see Word
         */
        private static final String TABLE_FRENCH = "Fr";
        /**
         * TABLE_ENGLISH La table qui va contenir les Words en englais.
         * <p>Cette table est caract�ris�e par: </p>
         * <lu>
         * <li> KEY_ID_WORD 
         * @see DataBaseHandler#KEY_ID_WORD </li>
         * <li> KEY_WORD 
         * @see DataBaseHandler#KEY_WORD </li>
         * <li> KEY_DEF
         * @see DataBaseHandler#KEY_DEF </li>
         * @see Word
         */
        private static final String TABLE_ENGLISH = "En";
        /**
         * TABLE_COUNT La table qui associ� � chaque lettre de l'alphabets ses mots existants dans 
         * la BD en r�cup�rant leurs Ids de la  TABLE_ENGLISH et TABLE_FRENCH.
         *  
         */
        private static final String TABLE_COUNT = "Count";

        /**
         * KEY_WORD c'est le nom de la colonne qui va contenir les words des objets 
         * Words dans la BD (le mot qu'on cherche d�finir).
         * 
         * 
         * @see Word
         */
        
        private static final String KEY_WORD = "word";
        /**
         * KEY_ID_WORD c'est le nom de la colonne qui va contenir les Ids des
         * objets Words dans la BD(identifiant unique pour chaque mot dans la table).
         * 
         * @see Word
         */
        private static final String KEY_ID_WORD = "idWord";
        /**
         * KEY_DEF  c'est le nom de la colonne qui va contenir les d�finitions des
         * objets Words dans la BD.
         * 
         * @see Word
         */
        private static final String KEY_DEF = "def";
        
        /**
         * KEY_ID_LETTER c'est le nom de la colonne dans la BD qui va contenir 
         * les Ids des lettres de l'alphabets.
         * 
         * @see Word
         */
        private static final String KEY_ID_LETTER = "idLetter";
        /**
         * KEY_LETTER c'est le nom de la colonne dans la BD qui va contenir 
         * les lettres de l'alphabets.
         * 
         * 
         */
        private static final String KEY_LETTER = "letter";
        /**
         * KEY_FRE c'est le nom de la colonne dans la BD qui va contenir 
         * les Ids de tous les words (mots) fran�ais qui commencent par la
         * lettre qui est d�finit dans KEY_LETTER.
         * 
         * 
         */
        private static final String KEY_FRE = "fre";
        /**
         * KEY_ENGc'est le nom de la colonne dans la BD qui va contenir 
         * les Ids de tous les words (mots) englais qui commencent par la
         * lettre qui est d�finit dans KEY_LETTER.
         * 
         *
         */
        private static final String KEY_ENG = "eng";

        
        /**
         * DataBaseHandler le constructeur de la classe
         * @param context pour indiquer dans quelle activity est appell�.
         */
        
        public DataBaseHandler(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                // TODO Auto-generated constructor stub
        }
	
        @Override
        public void onCreate(SQLiteDatabase db) {
                // TODO Auto-generated method stub
                String CREATE_FRENCH_TABLE = "CREATE TABLE " + TABLE_FRENCH + "("
                                + KEY_ID_WORD + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                                + KEY_DEF + " TEXT " + ")";
                db.execSQL(CREATE_FRENCH_TABLE);

                String CREATE_ENGLISH_TABLE = "CREATE TABLE " + TABLE_ENGLISH + "("
                                + KEY_ID_WORD + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                                + KEY_DEF + " TEXT " + ")";
                db.execSQL(CREATE_ENGLISH_TABLE);
                
                String CREATE_COUNT_TABLE = "CREATE TABLE " + TABLE_COUNT + "("
                                + KEY_ID_LETTER + " INTEGER PRIMARY KEY," + KEY_LETTER + " TEXT,"
                                + KEY_FRE + " INTEGER," + KEY_ENG + " INTEGER "+ ")";
                db.execSQL(CREATE_COUNT_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO Auto-generated method stub
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRENCH);
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH);
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNT);

                // Create tables again
                onCreate(db);

        }

        // recuperer ts les mots

        /**
         * getAllWords la methode qui parcourt toute la table 
         * que son nom est indiqu� en param�tres(tabName) dans la BD
         * et r�cup�re tous les Words dont l'identifiant est compris 
         * entre begin et end,cela se fait sous forme d'une liste. 
         * @param tabName le nom de la table qu'on veut extraire ses Words.
         * @param begin  l'identifiant du premier mot
         * @param end  l'identifiant du mot dernier
         *            
         * @return une liste de Word
         * @see Word
         */
        public List<Word> getAllWordsBetweenTwoLetters(String tabName,int begin,int end) {
                
                List<Word> wordList = new ArrayList<Word>();
                // Select All Query
                String selectQuery = "SELECT * FROM "+tabName+
                                " WHERE "+KEY_ID_WORD +" >= "+String.valueOf(begin) +" AND " +KEY_ID_WORD+" < "
                                +String.valueOf(end);

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                        do {
                                Word word = new Word();
                                word.setIdWord(Integer.parseInt(cursor.getString(0)));
                                word.setWord(cursor.getString(1));
                                word.setDef(cursor.getString(2));

                                // Adding projet to list
                                wordList.add(word);
                        } while (cursor.moveToNext());
                }

                // return project list
                return wordList;
        }
        
        /**
         * addWord permt d'ajouter un Word dans la table tabName.
         * @param w  indique un Word      
         * @param tabName le nom de la table ou on veut ajouter le Word w.
         * 
         * @see Word
         */
        public void addWord(Word w, String tabName) {
                
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                
                values.put(KEY_WORD, w.getWord());
                values.put(KEY_DEF, w.getDef());

                // Inserting Row
                db.insert(tabName, null, values);
                // Closing database connection*$
                db.close();
        }

        

        /**
         * getWord permet de r�cup�rer un Word de la table tabName.
         * @param idWord le Id du Word qu'on veut r�cup�rer.
         * @param tabName le nom de la table dans lequelle on va r�cup�rer le Word.
         * @return Word  le Word 
         * 
         * @see Word
         */
        public Word getWord(int idWord, String tabName) {
                SQLiteDatabase db = this.getReadableDatabase();
                Word w = null;
                Cursor cursor = db.query(tabName, new String[] { KEY_ID_WORD,
                                KEY_WORD, KEY_DEF },
                                KEY_ID_WORD + "=?", new String[] { String.valueOf(idWord) }, null,
                                null, null, null);
                if (cursor != null){
                        cursor.moveToFirst();
                 w = new Word(
                                cursor.getString(1), cursor.getString(2));}
                // return projet
                return w;
        }
        
 
        /**
         * getWords nous permet de r�cup�rer une liste de tous les Words  
         * qui commence par la lettre (lettre) indiqu�e en param�tre  de la table 
         * tabName ainsi indiqu� en param�tre.
         * @param tabName le nom de la table ou on veut r�cup�rer les Words.
         * @param letter  une des lettres de l'alphabets.
         * @return list<Word>  le r�sultats sous forme d'une liste de Word.
         * 
         * @see Word
         */
		public List<Word> getWords(String tabName, char letter){
                
                int beg=1,end=1; List<Word> wordsList=new ArrayList<Word>();
                      try{          
                if(letter!='a') beg=getCountByLetter(letter-1, tabName);
                end=getCountByLetter(letter, tabName);
                
                Log.d("end", String.valueOf(end));
                
                wordsList=getAllWordsBetweenTwoLetters(tabName, beg, end);
                
                }
                      catch(Exception e){e.printStackTrace();}
                      return wordsList;
        }
        
        
        
        /**
         * getCountByLetter compte le nombre de Word dans la table tabName indiqu� en param�tre
         * qui commence par une lettre sp�cifi�e ainsi  en param�tre.
         * @param letter la lettre est pr�sent�e par son code Ascci sous forme d'un entier.
         * @param tabName la table le nom de la table qu'on va parcourir.
         * @return un entier qui repr�sente le nombre de mot dans la table tabName 
         *         qui commence par la lettre lettre.
         */
        public int getCountByLetter(int letter, String tabName){
                
                int i,k=0,ret=0;
                
                for (i=97;i<=122;i++){
                        
                        k++;
                        if (i==letter) break;
                }
                        SQLiteDatabase db = this.getReadableDatabase();
                        Cursor cursor = db.query(TABLE_COUNT, new String[] {  KEY_ENG,KEY_ID_LETTER,
                                        KEY_LETTER,KEY_FRE },
                                        KEY_ID_LETTER + "=?", new String[] { String.valueOf(k) }, null,
                                        null, null, null);
                        if (cursor != null){
                                cursor.moveToFirst();
                                if (tabName.equals("Fr")) 
                                        {
                                        ret=Integer.parseInt(cursor.getString(3));
                                        Log.d("ret", String.valueOf(ret));
                                        }
                                else ret= Integer.parseInt(cursor.getString(0));}
                        
                   
                return ret;

}
	
	
	/**
	 * @param tabName
	 * @return le nombre de mots dans une table
	 */
	public int getTableCount(String tabName) {
		String countQuery = "SELECT  * FROM " + tabName;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		   int count = 0;
		   try {
		      if (cursor.moveToFirst()) {
		         count = cursor.getCount();
		      }
		      return count;
		   }
		   finally {
		      if (cursor != null) {
		         cursor.close();
		      }
	}
	}

}
