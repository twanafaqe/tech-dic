package dz.esi.tp1Igl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dz.esi.tp1Igl.modal.Word;

public class DataBaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Base1";

	// tables name
	private static final String TABLE_FRENCH = "Fr";
	private static final String TABLE_ENGLISH = "En";

	// Table Columns names
	private static final String KEY_WORD = "word";
	private static final String KEY_ID_WORD = "idWord";
	private static final String KEY_DEF = "def";

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

	// Getting single project

	public Word getWord(int idWord, String tabName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(tabName, new String[] { KEY_ID_WORD,
				KEY_WORD, KEY_DEF },
				KEY_ID_WORD + "=?", new String[] { String.valueOf(idWord) }, null,
				null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Word w = new Word(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return projet
		return w;
	}
	
	public void fillDB(String fileName) throws IOException
	{
	
	FileInputStream file=null;
	
	
	try{
	 file= new FileInputStream(new File(fileName));
	
	char a =' ';
    String str="";
		
	
    while((a=(char)file.read())>= -1) 
    {
	if(a!=' ') str=str+a;	

	while((a=(char)file.read())!='\n') // Le code décimal 10 c'est le retour chariot 	
	{   
		str= str+a;		
	
	}
	System.out.println(str);
	a=' ';
	str ="";
	}

	   } catch(FileNotFoundException e){ e.printStackTrace();}
	
	finally{
		try{
		
			if (file!=null) { file.close();}
			}
		catch(IOException e){e.printStackTrace();}
	}

}

private static Word rempMot(String str) {
	// TODO Auto-generated method stub
	char a=0;
	  int i=0;
	  Word m = null;
	  
		while(a !=',')
		{
			i++;
			a=str.charAt(i);
		}
		System.out.println(str);
		m.setWord(str);
		return m;
}

}
