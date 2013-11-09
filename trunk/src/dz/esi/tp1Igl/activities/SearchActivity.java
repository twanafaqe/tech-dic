package dz.esi.tp1Igl.activities;

import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.example.techDic.R;

import dz.esi.tp1Igl.modal.Word;
import dz.esi.tp1Igl.service.DataBaseHandler;
import dz.esi.tp1Igl.service.StableArrayAdapter;

/**
 * La classe qui controle la saisie de l'utilisateur
 * leur de sa recherche d'un mot dans le dictionnaire.
 * 
 * @author Aissani Amina & Tedjar Nour El Imane
 * @version 3.0
 * 
 *
 */


public class SearchActivity extends Activity {
	
	/**
     *  db instanciation de DataBaseHandler
     *  
     *  @see DataBaseHandler
     */
	DataBaseHandler db = new DataBaseHandler(this);
	/**
     * tw Tableau de Word 
     * 
     * @see Word
     */
    Word [] tw;
    /**
     * ad instanciation de StableArrayAdapter
     * 
     * @see StableArrayAdapter
     */
    StableArrayAdapter ad;
    /**
     * word un tableau de String,utiliser pour contenir des words des objets Word
     * dans la methode onTextChanged.
     * 
     */
    String [] word;
    /**
     *def un tableau de String,utiliser pour contenir des définitions des objets Word
     * dans la methode onTextChanged.
     * 
     */
    String [] def;
    /**
     * idOng une chaine de carctère utilisé temporairement.
     */
    String idOng;
    /**
     * first une variable booléenne utiliser pour le conditionnement. 
     */
    boolean first=true;
    
	/**
	 * list1 de type listView pour qu'elle affiche les mots à l'interface .
	 */
	ListView list1;
	/**
	 * def1 de type EditText c'est une case blanche qui aparrait à l'interface pour 
	 * saisir le mot à définir.
	 */
	EditText def1;
	/**
	 * oldPos un entier utilisé comme indice.
	 */
	int oldPos;
	

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		//recuperer l'idp
	    Bundle b= this.getIntent().getExtras();
	    if(b==null) idOng="Fr";
	    else idOng= b.getString("idOng");
	    
	      
	      
	      list1=(ListView) findViewById(R.id.list1);
	      def1=(EditText) findViewById(R.id.def1);
	     
	      
	    
	       
	      
	      /********************************************** filtrage de recherche ****************************************************/
	      def1.addTextChangedListener(new TextWatcher(){

	      @Override
	      public void afterTextChanged(Editable arg0) {
	  	// TODO Auto-generated method stub
	  	
	      }

	      @Override
	      public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	  		int arg3) {
	  	// TODO Auto-generated method stub
	  	
	      }
	      
	      /**
	  	 * onTextChanged filtre les mots du dictionnaire au fur et à mesure que l'utilisateur
	  	 * introduit son mot recherché, a la fin c'est le mot existe il va lui etre afficher 
	  	 * sinon si vide
	  	 */

	      @Override
	      public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	      	
	      	
	    	  if ((cs.length()>0) && (first))
	      	{
		      		int i;
		      		first=false;
		      		if(cs.length()==1){}
		  	      List<Word> lw = db.getWords(idOng, cs.charAt(0));
		  	      tw = lw.toArray(new Word[lw.size()]);
		  	      word= new String[lw.size()];
		  	      def= new String[lw.size()];
		        
		  	      
		         for (i=0;i<lw.size();i++){
		             word[i]=tw[i].getWord();
		             def[i]=tw[i].getDef();
		      	  }
		         Log.d("cpt= ", String.valueOf(first));
		       //instancier un objet ArrayAdapter pour pouvoir reccuperer les taches du tableau
		         
		         ad = new StableArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1, word);
		       //affichage
			       list1.setAdapter(ad);
		       
	      	}
	    	  else{
	    		  if (cs.length()==0) first=true;
	    		  Log.d("cpt= 0....", String.valueOf(first));
	    	  }
	    	
	      	
	      	SearchActivity.this.ad.getFilter().filter(cs);
	  	
	      }

	      });
	      
  /*********************************affecter les évènements lors du click *******************************************/
  OnItemClickListener itemClickListener = new OnItemClickListener() {
  	
  @Override
      public void onItemClick (AdapterView<?> arg0, View arg1, int position, long id) {
      	
      	Builder builder = new AlertDialog.Builder(SearchActivity.this); 
          
          
          oldPos =  (int) SearchActivity.this.ad.getItemId(position);
          builder.setMessage( def[oldPos]); 
          builder.setTitle(word[oldPos]);
          builder.setPositiveButton("Valider", null);
          
          builder.show(); 
       }
 

	
  };
   
  list1.setOnItemClickListener(itemClickListener);

	 }

	@Override
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search, menu);
		return true;
	}


}
