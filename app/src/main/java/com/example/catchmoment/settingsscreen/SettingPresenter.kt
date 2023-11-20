package com.example.catchmoment.settingsscreen

import com.example.catchmoment.data.MomentRepository

class SettingPresenter(private val repository: MomentRepository) {
   fun clickedCheckBoxNewFirst(isChecked:Boolean){
       repository.getLiveDataCheckBoxNewFirst().value=isChecked
   }
}