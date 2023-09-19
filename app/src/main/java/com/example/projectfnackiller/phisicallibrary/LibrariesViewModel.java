package com.example.projectfnackiller.phisicallibrary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class LibrariesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LibrariesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("List of song");
    }


    public LiveData<String> getText() {
        return mText;
    }
}