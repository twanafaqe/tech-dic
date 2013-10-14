package dz.esi.tp1Igl.service;


import java.util.HashMap;

import android.content.Context;
import android.widget.ArrayAdapter;



public class StableArrayAdapter extends ArrayAdapter<String> {

	
	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	
	public StableArrayAdapter (Context context, int resource,
			int textViewResourceId, String[] objects) {
		super(context, resource, textViewResourceId, objects);
		
		 for (int i = 0; i < objects.length; ++i) {
		        mIdMap.put(objects[i], i);
		      }
		// TODO Auto-generated constructor stub
	}

	@Override
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }
	
	@Override
    public boolean hasStableIds() {
      return true;
    }

	


	


}
