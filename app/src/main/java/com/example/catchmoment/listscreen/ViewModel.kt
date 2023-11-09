package com.example.catchmoment.listscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catchmoment.Moment
import com.example.catchmoment.data.MomentRepository

class ViewModel(private val momentsRepository: MomentRepository) : ViewModel() {

    val newFirstLiveData: MutableLiveData<Boolean>
        get() = momentsRepository.getLiveDataCheckBoxNewFirst()

    val allMoments: MutableLiveData<ArrayList<Moment>>
        get() = momentsRepository.getLiveDataMoments()
}
