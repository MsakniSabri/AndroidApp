package com.example.projectfnackiller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.example.projectfnackiller.API.Book;
import com.example.projectfnackiller.API.Facets.Facet;
import com.example.projectfnackiller.API.JSONReaderBooks;
import com.example.projectfnackiller.API.LibrariesList;
import com.example.projectfnackiller.API.Library;
import com.example.projectfnackiller.drawer.DataModel;
import com.example.projectfnackiller.drawer.DrawerItemCustomAdapter;
import com.example.projectfnackiller.libraryview.LibraryViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private RecyclerView recyclerView;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private CharSequence mTitle;
    private static LibraryViewAdapter mAdapter ;
    androidx.appcompat.app.ActionBarDrawerToggle  mDrawerToggle;
    private int startRow = 0;
    private int numberOfResults = 6;
    public static HashMap<String, ArrayList<String>> filtersList = new HashMap<>();
    public static ArrayList<Facet> facetsList  = new ArrayList<Facet>();
    public static String booktitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        mAdapter = new LibraryViewAdapter( new ArrayList(),this);

        recyclerView.setAdapter(mAdapter);



        setupToolbar();
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));



        facetsList = setUpFacets();

        DataModel[] drawerItem = new DataModel[facetsList.size()];

        int i = 0;
        for (Facet f: facetsList) {
            ArrayList<KeyPairBoolData> keysList = new ArrayList<>();
            int j = 0;
            for (String s: f.getAllValues().keySet()) {
                KeyPairBoolData key = new KeyPairBoolData(s, false);
                keysList.add(j, key);
                j++;
            }
            int icon = f.getIcon();
            drawerItem[i] = new DataModel(icon, f.getName(), keysList, f.getFacetName());
            i++;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem, this);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        ImageView im =(ImageView) findViewById(R.id.imageView3);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchPage(true);
            }
        });
        ImageView im2 =(ImageView) findViewById(R.id.imageView4);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchPage(false);
            }

        });




        //TEST
        System.out.println("List of libraries:");
        for (Library library: LibrariesList.getInstance().getLibraries()) {
            System.out.println(library.getName() + ": " + library.getFacetName());
        }

        EditText editText = findViewById(R.id.text_input);
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                booktitle = editText.getText().toString();
                executeSearch(booktitle);
                Log.d("SEARCH", " gofast ");
            }
        });

        executeSearch("");


        //TEST END

        //JSONReaderFacets Search2 = new JSONReaderFacets(this, "langue");
        //Search2.execute();
    }

    public void executeSearch(String s) {
        String apiLink = "https://opendata.paris.fr/api/records/1.0/search/?dataset=tous-les-documents-des-bibliotheques-de-pret&q=";
        JSONReaderBooks Search = new JSONReaderBooks(this, apiLink);
        Search.setTitle(booktitle);
        Search.setFiltersList(filtersList);
        Search.setStartResult(startRow);
        Search.setNumberOfResults(numberOfResults);
        Search.execute();
    }



    public void switchPage(boolean previous){
        if (previous & startRow > 0) {
            startRow = startRow - numberOfResults;
        } else {
            startRow = startRow + numberOfResults;
        }
        executeSearch(booktitle);

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;


        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        switch(item.getItemId()){


        case R.id.action_settings:
        Log.d("SORT", " | ");
        default:
            ;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new androidx.appcompat.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }




    ArrayList<Facet> setUpFacets() {
        ArrayList<Facet> result= new ArrayList<Facet>();
        result.add(new Facet("Language", R.drawable.language, "langue"));
        result.get(0).setUpValues();
        result.add(new Facet("Genre",R.drawable.sword, "categorie_statistique_1"));
        result.get(1).setUpValues();
        //result.add(new Facet("Author", R.drawable.library, "auteur_nom"));
        //result.get(2).setUpValues();
        //result.add(new Facet("Libraries", R.drawable.library, "auteur_nom"));
        //result.get(3).setUpValues();
        result.add(new Facet("Type", R.drawable.book, "libelle_v_smart_et_webopac"));
        result.get(2).setUpValues();
        return result;
    }

    public static void displayBooks(JSONReaderBooks JSON) {
        System.out.println("Display books. List size: " + JSON.getBooksList().size());
        refreshRecyclerView(new ArrayList<Book>(JSON.getBooksList()));
        //
        //refreshRecyclerView(new ArrayList<Book>(JSON.getBooksList()));
        for (Book book: JSON.getBooksList()) {
            //TODO:
            System.out.println(book.getTitle() + " / " + book.getLanguage() + " / " + book.getDate() + " / " + book.getType());
            System.out.print("Auteurs: ");
            for (String author: book.getAuthors()) {
                System.out.print(author + " | ");
            }
            System.out.println("");
            System.out.print("Categories: ");
            for (String category: book.getCategories()) {
                System.out.print(category + " | ");
            }
            System.out.println("");
            System.out.print("Disponible dans: ");
            for (Library library: book.getLibraries().keySet()) {
                System.out.print(library.getName() + " | ");
            }
            System.out.println("");
        }

    }

    private static void refreshRecyclerView(ArrayList<Book> library){
        Log.d("SEARCH", " refresh ");
        mAdapter.changeLibrary(library);
        mAdapter.notifyDataSetChanged();
    }
}