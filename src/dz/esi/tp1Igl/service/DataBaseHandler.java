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


@SuppressLint("NewApi")
public class DataBaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Base3Fin";

	// tables name
	private static final String TABLE_FRENCH = "Fr";
	private static final String TABLE_ENGLISH = "En";
	private static final String TABLE_COUNT = "Count";

	// Table Columns names
	private static final String KEY_WORD = "word";
	private static final String KEY_ID_WORD = "idWord";
	private static final String KEY_DEF = "def";
	
	private static final String KEY_ID_LETTER = "idLetter";
	private static final String KEY_LETTER = "letter";
	private static final String KEY_FRE = "fre";
	private static final String KEY_ENG = "eng";

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
				+ KEY_FRE + " INTEGER " + KEY_ENG + " INTEGER "+ ")";
		db.execSQL(CREATE_COUNT_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRENCH);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH);

		// Create tables again
		onCreate(db);

	}

	// recuperer ts les mots

	public List<Word> getAllWords(String tabName) {
		
		List<Word> wordList = new ArrayList<Word>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + tabName;

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

	// Getting single word

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
	
    //getting all words beginnig with a letter
	
	
	public List<Word> getWords(String tabName, char letter){
		
		int i,beg=1,end=1; List<Word> wordsList=new ArrayList<Word>();
				
		if(letter!='a') beg=getCountByLetter(letter-1, tabName);
		end=getCountByLetter(letter, tabName);
		
		Log.d("end", String.valueOf(end));
		
		for(i=beg;i<end;i++){
			wordsList.add( getWord(i,tabName));
		}
		
		return wordsList;
	}
	
	//getting the word's number of a letter
	
	public int getCountByLetter(int letter, String tabName){
		
		int i,k=0,ret=0;
		
		for (i=97;i<=122;i++){
			
			k++;
			if (i==letter) break;
		}
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_COUNT, new String[] { KEY_ID_LETTER,KEY_LETTER,
					KEY_FRE, KEY_ENG },
					KEY_ID_LETTER + "=?", new String[] { String.valueOf(k) }, null,
					null, null, null);
			if (cursor != null){
				cursor.moveToFirst();
				if (tabName.equals("Fr")) 
					{
					ret=Integer.parseInt(cursor.getString(2));
					Log.d("ret", String.valueOf(ret));
					}
				else ret= Integer.parseInt(cursor.getString(3));}
			
	           
		return ret;

}
}
