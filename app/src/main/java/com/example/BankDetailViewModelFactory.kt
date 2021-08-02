package com.example

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.BankDetailViewModel

class BankDetailViewModelFactory(context:Context) :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BankDetailViewModel::class.java)) {
            return modelClass.cast(BankDetailViewModel())!!
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}