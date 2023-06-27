package com.nsicyber.travelandshare.repositories

import android.media.MediaPlayer
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.database.FirebaseDatabase
import com.nsicyber.travelandshare.models.TravelDataModel


@Singleton
class FirebaseRepository @Inject constructor() {
    var auth = Firebase.auth
    val database = FirebaseDatabase.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val notesRef = database.reference.child("users").child(currentUser?.uid ?: "").child("notes")


    fun login(
        email: String, password: String, completion: (response: Boolean) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                completion(true)
            } else
                completion(false)
        }
    }


    fun signup(
        email: String, password: String, completion: (response: Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                completion(true)
            } else
                completion(false)
        }
    }

    fun saveFromId(id: Int, completion: (response: Boolean) -> Unit) {

    }

    fun saveData(model: TravelDataModel, completion: (response: Boolean) -> Unit) {

    }

    fun deleteNote(id:Int) {
        //search database for id and delete
    }

    fun shareNote(id:Int,anony:Boolean){
        //take note and send id to shared user database, for shared notes create anont user or so
    }


}
