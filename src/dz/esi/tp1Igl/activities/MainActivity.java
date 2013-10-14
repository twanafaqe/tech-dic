package dz.esi.tp1Igl.activities;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.example.techDic.R;

import dz.esi.tp1Igl.modal.Word;
import dz.esi.tp1Igl.service.DataBaseHandler;
import dz.esi.tp1Igl.service.StableArrayAdapter;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
   
	
	       private TabHost monTabHost;
	       DataBaseHandler db = new DataBaseHandler(this);
	       Word [] tw;
	       StableArrayAdapter ad;
	       String [] word;
	       String [] def;
	       
		   ListView list1;
		   EditText def1;
		   ListView list2;
		   EditText def2;
			   
		   @Override
		   public void onCreate(Bundle savedInstanceState) {
		      super.onCreate(savedInstanceState);
		      setContentView(R.layout.activity_main);
		
	         
		      /*list2=(ListView) findViewById(R.id.list2);
		      def2=(EditText) findViewById(R.id.def2);*/
		      
		      
		      
		      TabHost tabHost = getTabHost();

				// Tab pour le français
				TabSpec fr = tabHost.newTabSpec("fr");

				Intent i1 = new Intent(this, SearchActivity.class);
				i1.putExtra("idOng", "Fr");

				// non d'onglet
				fr.setIndicator("Français",getResources().getDrawable(R.drawable.fre));
				fr.setContent(i1);


				// Tab pour l'anglais
				TabSpec en = tabHost.newTabSpec("English");

				Intent i2 = new Intent(this, SearchActivity.class);
				i2.putExtra("idOng", "En");
				
				//nom d'onglet
				en.setIndicator("English", getResources().getDrawable(R.drawable.eng));
				en.setContent(i2);
				
				// Ajouter tous les TabSpec to TabHost
				tabHost.addTab(fr);
				tabHost.addTab(en);
				//initTabsAppearance(getTabWidget());
		   }
		  /* private void initTabsAppearance(TabWidget tabWidget) {
				// Change background
				for (int i = 0; i < tabWidget.getChildCount(); i++)
					tabWidget.getChildAt(i).setBackgroundResource(R.drawable.tabbg);
			}*/
		   
   public void fillDB(String filePath,int whatF)
	{
	
		//0 pr englais 1 pr français
		
		Scanner scanner = null;
		try {
			AssetManager am = MainActivity.this.getAssets();
			InputStream is = am.open(filePath);
			
			scanner = new Scanner(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("not", "found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char a; int i,j=0;
		 
		// On boucle sur chaque champ detecté
		while (scanner.hasNextLine() && j<=7292 ) {
			
		
		    String line = scanner.nextLine();
		   
		
		    Log.d("j=", String.valueOf(j));
           // System.out.println(line.trim());
			i=0;
			String w,def;
			
			if (!line.isEmpty()){
				
					if (whatF==0){
						
						while (true){
							
							i++;
							if ((line.charAt(i)==' ') && (line.charAt(i+1)==' ')) break;
						}
						
						
					}
					else{
						 a=0;
						 i=0;
						 w="";def="";
						 
						 while(a !=',')
							{
								i++;
								a=line.charAt(i);
							}
					}
					w= line.substring(0,i);
					if (whatF==0) {
						
						def=line.substring(i+2);//System.out.println(w+" \n "+ line.substring(i+2));
						Log.d("mot", w);
						db.addWord(new Word(w,def), "En");
						 j++;
					}
					else{
						def=line.substring(i+2);
						
						db.addWord(new Word(w,def), "Fr");
					System.out.println(w+" \n "+ line.substring(i+2,line.length()-13).trim());
					}
					//break;
			}
		}

		 
		scanner.close();
}

public TabHost getMonTabHost() {
	return monTabHost;
}

public void setMonTabHost(TabHost monTabHost) {
	this.monTabHost = monTabHost;
}

		}