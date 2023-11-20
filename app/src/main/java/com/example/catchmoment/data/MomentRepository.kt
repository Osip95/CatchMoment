package com.example.catchmoment.data

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.catchmoment.Moment

interface MomentRepository {

    fun addMoment(name: String, time: String, bitmap: Bitmap)
    fun getLiveDataMoments(): MutableLiveData<ArrayList<Moment>>
    fun saveData(context: Context)
    fun loadData(context: Context)
    fun getLiveDataCheckBoxNewFirst(): MutableLiveData<Boolean>






}