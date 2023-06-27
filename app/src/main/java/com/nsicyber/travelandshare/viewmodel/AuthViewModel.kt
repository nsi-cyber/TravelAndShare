package com.nsicyber.travelandshare.viewmodel


import androidx.lifecycle.ViewModel
import com.nsicyber.travelandshare.repositories.BottomSheetRepository
import com.nsicyber.travelandshare.repositories.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private var firebaseRepository: FirebaseRepository) : ViewModel(){

    fun login(email:String,password:String,completion: (response: Boolean) -> Unit
    ){
        firebaseRepository.login(email,password){
            completion(it)
        }

    }

    fun signup(email:String,password:String,completion: (response: Boolean) -> Unit
    ){
        firebaseRepository.signup(email,password){
            completion(it)
        }

    }

    fun openMaps(gps:String?){

    }





}