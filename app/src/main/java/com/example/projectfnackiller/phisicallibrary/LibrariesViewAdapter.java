package com.example.projectfnackiller.phisicallibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfnackiller.API.Book;
import com.example.projectfnackiller.API.Library;
import com.example.projectfnackiller.R;
import com.example.projectfnackiller.libraryview.LibraryViewAdapter;
import com.example.projectfnackiller.libraryview.RecyclerViewHolderLibrary;

import java.util.ArrayList;




public class LibrariesViewAdapter extends RecyclerView.Adapter<LibrariesViewHolderLibrary>{

    private static ClickListener clickListener;
    private Book book;
    private ArrayList<Library> libraries;
    private Context context;
    private double lat;
    private double longi;
    private ArrayList<Library> allLibraries;




    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            name = (TextView) itemView.findViewById(R.id.randomText);
        }




        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }



        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }



    public void setOnItemClickListener(ClickListener clickListener) {
        LibrariesViewAdapter.clickListener = clickListener;
    }



    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }


    public LibrariesViewAdapter( Context current, ArrayList<Library> allLibraries , Book book) {

        this.context = current;
        this.allLibraries = allLibraries;
        this.book = book;



    }

    public void addLibraries(Library library){
        this.libraries.add(library);
    }

    public void changeLocation(double lat, double longi){

        this.lat = lat;
        this.longi = longi;


    }
    public void changeLibrary(ArrayList<Library> allLibraries){
        this.allLibraries= allLibraries;
    }




    @Override
    public int getItemCount() {
        return  allLibraries.size();
    }

    @NonNull
    @Override
    public LibrariesViewHolderLibrary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locationrecycler, parent, false);
        return new LibrariesViewHolderLibrary(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrariesViewHolderLibrary holder, int position) {

        holder.getView().setText(String.valueOf(allLibraries.get(position).getName()));
        holder.getView2().setText(String.valueOf(book.getLibraries().get(allLibraries.get(position))));
        holder.setTitle(allLibraries.get(position).getName());
        holder.setLib(allLibraries.get(position));
        holder.setDistance(lat,longi);
        holder.setGender(context.getResources().getIdentifier("sword", "drawable", context.getPackageName()));

    }



}
