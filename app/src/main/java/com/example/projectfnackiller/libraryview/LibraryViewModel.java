package com.example.projectfnackiller.libraryview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LibraryViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public LibraryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("List of song");
    }


    public LiveData<String> getText() {
        return mText;
    }
}