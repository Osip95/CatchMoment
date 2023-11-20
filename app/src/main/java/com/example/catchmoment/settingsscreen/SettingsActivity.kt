package com.example.catchmoment.settingsscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import com.example.catchmoment.R
import org.koin.android.ext.android.inject

class SettingsActivity : AppCompatActivity() {

    private val settingPresenter: SettingPresenter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val checkBox: CheckBox = findViewById(R.id.checkBox)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            settingPresenter.clickedCheckBoxNewFirst(isChecked)
        }


    }
}