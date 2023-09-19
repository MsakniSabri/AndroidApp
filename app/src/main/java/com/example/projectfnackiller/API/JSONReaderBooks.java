package com.example.projectfnackiller.API;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.projectfnackiller.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class JSONReaderBooks extends AsyncTask<String, Void, Void> {
    String api;
    String title;
    Integer numberOfResults = 10;
    Integer startResult = 0;
    String error = "", apiResponse = "";
    ProgressDialog dialog;

    Context contextMain;
    HashMap<String, ArrayList<String>> filtersList = new HashMap<>();



    ArrayList<Book> booksList = new ArrayList<>();


    public List<Book> getBooksList() {
        return booksList;
    }

    public void setFiltersList(HashMap<String, ArrayList<String>> a) {
        filtersList = a;
    }

    public void setNumberOfResults(Integer numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    public void setStartResult(Integer startResult) {
        this.startResult = startResult;
    }

    public void setTitle(String s) {
        this.title = s;
    }

    public JSONReaderBooks(Context context, String link) {
        contextMain = context;
        api = link;
        dialog = new ProgressDialog(contextMain);
    }

    @Override
    protected Void doInBackground(String... amounts) {
        OkHttpClient client = new OkHttpClient();

        //set API link based on how many books we want to display
        api = api + "&rows=" + numberOfResults + "&start=" + startResult+ "&q=(titre:" + title+ ")";

        //set API link based on filters
        for(Map.Entry<String, ArrayList<String>> entry : filtersList.entrySet()) {
            ArrayList<String> value = entry.getValue();
            String key = entry.getKey();



            api = api  +"&facet=" + key;
            for (String s: value) {
                api = api + "&refine." + key + "=" + s;
            }
            if (value.size() > 1) {
                api = api + "&disjunctive." + key + "=true";
            }

        }
        System.out.println("DEBUG: Fetching from " + api);

        //fetch values from API
        Request request = new Request.Builder().url(api).build();

        try {
            Response response = client.newCall(request).execute();
            apiResponse = response.body().string();
        } catch (Exception e) {
            error = e.toString();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Please wait...");
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void v) {
        dialog.dismiss();

        try {
            JSONObject JSON = new JSONObject(apiResponse);
            populateBooksList(JSON.getJSONArray("records"), JSON.getJSONObject("parameters"), JSON.getInt("nhits"));
            MainActivity.displayBooks(this);

        } catch (Exception e) {
            //showWarning("Error: " + e.toString());
        }


        super.onPostExecute(v);
    }

    void showWarning(String message) {
        //Toast.makeText(contextMain, message, Toast.LENGTH_SHORT).show();

        //Debug
        System.out.println(message);
    }



    void populateBooksList(JSONArray records, JSONObject parameters, int nhits) {
        // populates booksList with the books found in the current search
        booksList.clear();
        try {
            //int startResult = parameters.getInt("start");
            //int numberOfRows = parameters.getInt("rows");
            }
        catch (Exception e) {
            showWarning("Error: " + e.toString());
        }
        //int numberOfResults = nhits;
        for (int i=0; i<records.length(); i++) {
            try {
                JSONObject bookJSON = records.getJSONObject(i).getJSONObject("fields");
                Book book = new Book((bookJSON.get("titre").toString()));
                if (bookJSON.has("langue")) {
                    book.setLanguage(bookJSON.get("langue").toString());
                }
                if (bookJSON.has("libelle_v_smart_et_webopac")) {
                    book.setType(bookJSON.get("libelle_v_smart_et_webopac").toString());
                }
                if (bookJSON.has("categorie_statistique_1")) {
                    book.addCategory(bookJSON.get("categorie_statistique_1").toString());
                }
                if (bookJSON.has("categorie_statistique_2")) {
                    book.addCategory(bookJSON.get("categorie_statistique_2").toString());
                }
                if (bookJSON.has("date")) {
                    book.setDate(bookJSON.get("date").toString());
                }
                if (bookJSON.has("date")) {
                    book.setDate(bookJSON.get("date").toString());
                }
                //adding authors
                if (bookJSON.has("auteur")) {
                    book.addAuthor(bookJSON.get("auteur").toString());
                }
                if (bookJSON.has("auteur_secondaire")) {
                    book.addAuthor(bookJSON.get("auteur_secondaire").toString());
                }
                if (bookJSON.has("auteur_collectivite")) {
                    book.addAuthor(bookJSON.get("auteur_collectivite").toString());
                }
                if (bookJSON.has("collectivite_auteur_secondaire_")) {
                    book.addAuthor(bookJSON.get("collectivite_auteur_secondaire_").toString());
                }
                if (bookJSON.has("co_auteur")) {
                    book.addAuthor(bookJSON.get("co_auteur").toString());
                }
                for (Library library: LibrariesList.getInstance().getLibraries()) {
                    if (bookJSON.has(library.getFacetName())) {
                        String numberOfCopies = bookJSON.get(library.getFacetName()).toString();
                        book.addToLibrary(library, Integer.parseInt(numberOfCopies));
                    }
                }
                booksList.add(book);
            } catch (Exception e) {
                showWarning("Error: " + e.toString());
            }
        }
    }

}
