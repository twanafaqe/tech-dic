package dz.esi.tp1Igl.activities;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.example.techDic.R;

import dz.esi.tp1Igl.modal.Word;
import dz.esi.tp1Igl.service.DataBaseHandler;
import dz.esi.tp1Igl.service.StableArrayAdapter;

/**
 * <b>MainActivity c'est la classe qui affiche l'interface de l'application à l'utilisateur</b>
 * 
 * @author Aissani Amina & Tedjar Nour El imane
 * @version 2.0
 *
 */


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
   
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
	     * dans la methode fillDB.
	     * 
	     * @see MainActivity#fillDB(String, int)
	     * 
	     */
	    String [] word;
	       /**
	     *def un tableau de String,utiliser pour contenir des définitions des objets Word
	     *dans la methode fillDB.
	     *
	     * @see MainActivity#fillDB(String, int)
	     * 
	     */
	    String [] def;
	       
	    
	    public TabSpec fr;
	    
	    public TabSpec en;
	    
	    public Intent i2;
	    
	    public static Drawable enIm;
	    /**
	     * Le constructeur de la classe MainActivity par défaut.
	     */
	    public MainActivity()
	    {
	    	
	    }
		  
		 @Override
		   public void onCreate(Bundle savedInstanceState) {
			   
		      super.onCreate(savedInstanceState);
		      setContentView(R.layout.activity_main);

		      TabHost tabHost = getTabHost();

				// Tab pour le français
				fr = tabHost.newTabSpec("fr");

			    Intent i1 = new Intent(this, SearchActivity.class);
				i1.putExtra("idOng", "Fr");

				// non d'onglet
				fr.setIndicator("Français",getResources().getDrawable(R.drawable.ic_launcher));
				fr.setContent(i1);


				// Tab pour l'anglais
				 en = tabHost.newTabSpec("English");

				 i2 = new Intent(this, SearchActivity.class);
				 i2.putExtra("idOng", "En");
				
				 //nom d'onglet
				 en.setIndicator("English", enIm);
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
		   
   /**
    * fillDB cette méthode va nous permettre de remplir la base de données
    * donc elle va etre appellè une seule fois.
    * 
 * @param filePath le nom du fichier qui contient les mots avec leurs définitions(Word).
 * @param whatF un entier qui peut etre: 0 qui
 *  signifié le fichier des Words français
 *  ou 1 le fichier des Words englais.
 * 
 * @see Word
 * 
 */
@SuppressLint("NewApi")
public void fillDB(String filePath,int whatF)
	{
	
		//0 pr englais 1 pr français
		
		Scanner scanner = null;
		try {
			AssetManager am = MainActivity.this.getAssets();
			InputStream is = am.open(filePath);
			
			scanner = new Scanner(is,"UTF-8");
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
		while (scanner.hasNextLine() && j<=5000 ) {
			
		    
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
						Log.d("mot", w);
						j++;
						db.addWord(new Word(w,def), "Fr");
					//System.out.println(w+" \n "+ line.substring(i+2,line.length()-13).trim());
					}
					//break;
			}
		}

		 
		scanner.close();
}

/**
 * Retourner le TabHost c-à-d les onglets de l'interface.
 * 
 * @return TabHost returner le TabHost
 */


		}