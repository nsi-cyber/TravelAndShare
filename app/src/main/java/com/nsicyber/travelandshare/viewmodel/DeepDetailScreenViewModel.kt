package com.nsicyber.travelandshare.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsicyber.travelandshare.models.TravelDataModel
import com.nsicyber.travelandshare.repositories.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DeepDetailScreenViewModel @Inject constructor(private var firebaseRepository: FirebaseRepository) :
    ViewModel() {


    fun getDetail(id:Int) {
        viewModelScope.launch {

        }
    }




}