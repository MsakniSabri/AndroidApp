package com.example.projectfnackiller.drawer;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.projectfnackiller.API.Facets.Facet;
import com.example.projectfnackiller.API.LibrariesList;
import com.example.projectfnackiller.MainActivity;
import com.example.projectfnackiller.R;
import com.example.projectfnackiller.drawer.DataModel;

import java.security.KeyPairGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;
    MainActivity mainactivity;
    String title = "";

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data, MainActivity mainactivity) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.mainactivity = mainactivity;
    }

    public void ChangeTitle(String s){
        this.title = s;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        DataModel folder = data[position];


        imageViewIcon.setImageResource(folder.icon);


        MultiSpinnerSearch multiSelectSpinnerWithSearch = listItem.findViewById(R.id.multipleItemSelectionSpinner);

        // Pass true If you want searchView above the list. Otherwise false. default = true.
        multiSelectSpinnerWithSearch.setSearchEnabled(false);

        List<KeyPairBoolData> listArray1 = folder.filters;



        multiSelectSpinnerWithSearch.dispatchSetSelected(false);


        // Set text that will display when search result not found...
        multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");

        // If you will set the limit, this button will not display automatically.
        multiSelectSpinnerWithSearch.setShowSelectAllButton(false);

        //A text that will display in clear text button
        multiSelectSpinnerWithSearch.setClearText("");

        // Removed second parameter, position. Its not required now..
        // If you want to pass preselected items, you can do it while making listArray,
        // Pass true in setSelected of any item that you want to preselect



        multiSelectSpinnerWithSearch.setItems(listArray1, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                Facet facet = MainActivity.facetsList.get(position);
                if (MainActivity.filtersList.containsKey(facet.getFacetName())) {
                    MainActivity.filtersList.remove(facet.getFacetName());
                }
                ArrayList<String> listFilters = new ArrayList<>();
                ArrayList<String> listFiltersExceptions = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i("Selected", i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                        Log.i("Debug", "Current filter name: " + facet.getName() + " | associated facet: " + facet.getFacetName());
                        HashMap<String, ArrayList<String>> values = MainActivity.facetsList.get(position).getAllValues();
                        for(Map.Entry<String, ArrayList<String>> entry : values.entrySet()) {
                            ArrayList<String> value = entry.getValue();
                            String key = entry.getKey();
                            Log.i("key", key);
                            if (key == items.get(i).getName()) {
                                Log.i("key happened", "yes");
                                for (String s: value) {
                                    Log.i("Value", s);
                                    if (LibrariesList.getInstance().getCategoriesException().contains(s)){
                                        listFiltersExceptions.add(s);
                                    } else {
                                        listFilters.add(s);
                                    }

                                }
                            }
                        }
                        MainActivity.filtersList.put(facet.getFacetName(), listFilters);
                        if (!listFiltersExceptions.isEmpty()){
                            Log.i("Exception List", "NOT EMPTY");
                            MainActivity.filtersList.put("categorie_statistique_2", listFiltersExceptions);
                        }
                    }
                };
                if(multiSelectSpinnerWithSearch.getSelectedItems().size() > 0){
                    textViewName.setText("");
                }
                else{
                    textViewName.setText(folder.name);
                };


                mainactivity.executeSearch(title);
            }
        });

        if(multiSelectSpinnerWithSearch.getSelectedItems().size() > 0){
            textViewName.setText("");
        }
        else{

            textViewName.setText(folder.name);
        };


        /**
         * If you want to set limit as maximum item should be selected is 2.
         * For No limit -1 or do not call this method.
         *
         */
        multiSelectSpinnerWithSearch.setLimit(-1, new MultiSpinnerSearch.LimitExceedListener() {
            @Override
            public void onLimitListener(KeyPairBoolData data) {
            }
        });


        return listItem;
    }
}