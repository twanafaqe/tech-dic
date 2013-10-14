package dz.esi.tp1Igl.activities;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.techDic.R;

import dz.esi.tp1Igl.modal.Word;
import dz.esi.tp1Igl.service.DataBaseHandler;
import dz.esi.tp1Igl.service.StableArrayAdapter;

public class SearchActivity extends Activity {
	
	DataBaseHandler db = new DataBaseHandler(this);
    Word [] tw;
    StableArrayAdapter ad;
    String [] word;
    String [] def;
    String idOng;
    boolean first=true;
    
	ListView list1;
	EditText def1;
	int oldPos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		//recuperer l'idp
	    Bundle b= this.getIntent().getExtras();
	    idOng= b.getString("idOng");
	    
	    
	  /*  int i;
	      List<Word> lw = db.getWords("En", 'c');
	      tw = lw.toArray(new Word[lw.size()]);
	      word= new String[lw.size()];
	      def= new String[lw.size()];
      
	      
       for (i=0;i<lw.size();i++){
           word[i]=tw[i].getWord();
           def[i]=tw[i].getDef();
	      }*/
	      
	      
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

	      @Override
	      public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	      	
	      	
	    	  if ((cs.length()>0) && (first))
	      	{
		      		int i;
		      		first=false;
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
          builder.setTitle(" Definition "); 
        
          oldPos =  (int) SearchActivity.this.ad.getItemId(position);
          builder.setMessage( def[oldPos]); 
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
