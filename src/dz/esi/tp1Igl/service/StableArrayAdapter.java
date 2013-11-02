package dz.esi.tp1Igl.service;


import java.util.HashMap;

import android.content.Context;
import android.widget.ArrayAdapter;



/**
 * StableArrayAdapter cette classe est utilis�e pour fixer les Ids des objets
 * manipul�s par les items (les listes) sinon il y aura des changements incontr�lables .
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
	 * @param context  le nom de  l'activit� ou la classe est appell�e.
	 * @param resource le style de la liste (petite,moyenne,grande) � l'apparence.
	 * @param textViewResourceId le style et le format du texte ou est �crit les �lements de la liste.
	 * @param objects  les objets  � fixer leurs Ids. 
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
	 * getItemId Retourne l'id de chaque �l�ment inclue dans la lise.
	 * @param position un entier qui identifi� la position de l'�l�ment dans la liste. 
	 * @return l'id de l'�l�ment.
	 */
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }
	

	/**
	 * hasStableIds verifi� si les �l�ments de la liste 
	 * leurs ids est fix�s.
	 * @return boolean true c'est les �l�ments si les �l�ments de la liste 
	 * leurs ids est fix�s sinon un false.
	 */
    public boolean hasStableIds() {
      return true;
    }
}
