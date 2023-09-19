package com.example.projectfnackiller.libraryview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfnackiller.API.Book;
import com.example.projectfnackiller.BookActivity;
import com.example.projectfnackiller.R;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




public class RecyclerViewHolderLibrary extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView view;
    TextView view2;
    TextView view3;
    ImageView distanceView;
    String bookTitle;
    Book currentBook;
    int gender;

    public RecyclerViewHolderLibrary(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        view = itemView.findViewById(R.id.randomText);
        view2 = itemView.findViewById(R.id.randomText2);
        view3 = itemView.findViewById(R.id.randomText3);
        //view.setTextColor(Color.rgb(200,200,0));
        distanceView = itemView.findViewById(R.id.distanceText);



        Log.i("COMPARE",gender + " : to : " + R.drawable.sword);


    }

    @Override
    public void onClick(View view) {

        Context context = view.getContext();
        Intent intent = new Intent(context, BookActivity.class);

        Bundle b = new Bundle();

        Gson gson = new Gson();
        String myJson = gson.toJson(currentBook);


        b.putInt("libraryID", getLayoutPosition());
        b.putString("bookTitle", bookTitle);
        intent.putExtras(b);
        intent.putExtra("BOOK",currentBook);
        context.startActivity(intent);

    }



    public TextView getView(){
        return view;
    }

    public TextView getView2(){
        return view2;
    }
    public TextView getView3(){
        return view3;
    }

    public void setTitle(String s){
        this.bookTitle = s;
    }

    public void setBook(Book b){
        this.currentBook = b;
    }

    public void setGender(int s){
        this.gender= s;
    }

}