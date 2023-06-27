package com.nsicyber.travelandshare.viewmodel

import android.util.Log
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
class ListScreenViewModel @Inject constructor(private var firebaseRepository: FirebaseRepository) :
    ViewModel() {
    var travelList = mutableStateOf<List<TravelDataModel>>(listOf())


    fun getList() {
        viewModelScope.launch {
            firebaseRepository.notesRef.get()
                .addOnSuccessListener { snapshot ->
                    val noteList = mutableListOf<TravelDataModel>()
                    for (childSnapshot in snapshot.children) {
                        val note = childSnapshot.getValue(TravelDataModel::class.java)
                        note?.let {
                            noteList.add(it)
                        }
                    }
                    travelList.value = noteList
                }
                .addOnFailureListener {
                    Timber.tag("Error firebase").d("Error while getting notes")
                }
        }
    }

    fun openMaps(gps: String?) {

    }


}