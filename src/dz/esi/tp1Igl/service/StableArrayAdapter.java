package dz.esi.tp1Igl.service;


import java.util.HashMap;

import android.content.Context;
import android.widget.ArrayAdapter;



/**
 * StableArrayAdapter cette classe est utilisée pour fixer les Ids des objets
 * manipulés par les items (les listes) sinon il y aura des changements incontrôlables .
 * 
 * @author Aissani Amina & Tedjar Nour El Imane
 * @version 3.0
 *
 */
public class StableArrayAdapter extends ArrayAdapter<String> {

	
	/**
	 * 
	 * midMap instanciation de HashMap
	 * 
	 */
	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	
	/**
	 * @param context  le nom de  l'activité ou la classe est appellée.
	 * @param resource le style de la liste (petite,moyenne,grande) à l'apparence.
	 * @param textViewResourceId le style et le format du texte ou est écrit les élements de la liste.
	 * @param objects  les objets  à fixer leurs Ids. 
	 */
	public StableArrayAdapter (Context context, int resource,
			int textViewResourceId, String[] objects) {
		super(context, resource, textViewResourceId, objects);
		
		 for (int i = 0; i < objects.length; ++i) {
		        mIdMap.put(objects[i], i);
		      }
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * getItemId Retourne l'id de chaque élément inclue dans la lise.
	 * @param position un entier qui identifié la position de l'élément dans la liste. 
	 * @return l'id de l'élément.
	 */
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }
	

	/**
	 * hasStableIds verifié si les éléments de la liste 
	 * leurs ids est fixés.
	 * @return boolean true c'est les éléments si les éléments de la liste 
	 * leurs ids est fixés sinon un false.
	 */
    public boolean hasStableIds() {
      return true;
    }
}
