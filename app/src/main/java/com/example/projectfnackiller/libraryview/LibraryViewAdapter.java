package com.example.projectfnackiller.libraryview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfnackiller.API.Book;
import com.example.projectfnackiller.R;

import java.util.ArrayList;

public class LibraryViewAdapter extends RecyclerView.Adapter<RecyclerViewHolderLibrary>{

    private static ClickListener clickListener;
    private ArrayList<Book> library;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name;
        TextView name2;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            name = (TextView) itemView.findViewById(R.id.randomText);
            name2 = (TextView) itemView.findViewById(R.id.randomText2);

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
        LibraryViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }


    public LibraryViewAdapter( ArrayList<Book> library , Context current) {
        ArrayList<Book> librarys = new ArrayList();
        Book losda = new Book("LSDA");
        losda.setDate("1957");
        losda.setLanguage("French");
        librarys.add(losda);
        this.library = librarys;
        this.context = current;

    }






    public void addBook(Book book){
        this.library.add(book);
    }

    public void changeLibrary(ArrayList<Book> library){
        this.library= library;
    }



    @Override
    public int getItemCount() {
        return library.size() ;
    }

    @NonNull
    @Override
    public RecyclerViewHolderLibrary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.libraryrecyclerelement, parent, false);
        return new RecyclerViewHolderLibrary(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderLibrary holder, int position) {

        holder.getView().setText(String.valueOf(library.get(position).getTitle()));
        holder.getView2().setText(String.valueOf(library.get(position).getLanguage()));
        holder.getView3().setText(String.valueOf(library.get(position).getType()));
        holder.setTitle(library.get(position).getTitle());
        holder.setGender(context.getResources().getIdentifier("sword", "drawable", context.getPackageName()));
        holder.setBook(library.get(position));

    }










}
