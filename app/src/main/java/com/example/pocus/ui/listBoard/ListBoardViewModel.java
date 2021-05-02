package com.example.pocus.ui.listBoard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListBoardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListBoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("POCUS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}