package com.nsicyber.travelandshare.viewmodel

import androidx.lifecycle.ViewModel
import com.nsicyber.travelandshare.repositories.BottomSheetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BottomSheetViewModel @Inject constructor(private var bottomSheetRepository: BottomSheetRepository) : ViewModel(){


    fun openMaps(gps:String?){

    }





}