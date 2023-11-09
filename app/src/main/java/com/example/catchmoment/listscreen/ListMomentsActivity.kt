package com.example.catchmoment.listscreen


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catchmoment.R
import com.example.catchmoment.databinding.ActivityListBinding
import com.example.catchmoment.settingsscreen.SettingsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListMomentsActivity : AppCompatActivity() {

    lateinit var binding:ActivityListBinding
    private val viewModel: ViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val timeNow = System.currentTimeMillis()
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MomentsAdapter(timeNow)




        viewModel.allMoments.observe(this) {
            adapter.setData(it)
        }

        viewModel.newFirstLiveData.observe(this){
            adapter.setReversRV(it)
        }

       binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_settings -> {
                    val intent = Intent(this@ListMomentsActivity, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }




        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)

    }

}