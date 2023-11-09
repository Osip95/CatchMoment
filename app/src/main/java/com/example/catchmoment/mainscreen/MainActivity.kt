package com.example.catchmoment.mainscreen


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.catchmoment.MyService
import com.example.catchmoment.R
import com.example.catchmoment.databinding.ActivityMainBinding
import com.example.catchmoment.listscreen.ListMomentsActivity
import com.example.catchmoment.settingsscreen.SettingsActivity
import org.koin.android.ext.android.inject


const val DEFAULT_NAME_MOMENT = "Безымянный момент"

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val mainPresenter: MainPresenter by inject()
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainPresenter.initView(this, this)



        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            mainPresenter.getPhoto(result)
        }

        with(binding) {

            ivMainScreen.setOnClickListener {

                mainPresenter.clickedOnThePhoto(btnCamera.isVisible)
            }

            btnCamera.setOnClickListener {
                mainPresenter.clickedOnTneCameraButton()
            }

            btnGallery.setOnClickListener {
                mainPresenter.clickedOnTheGalleryButton()
            }

            btnCreateMoment.setOnClickListener {
                mainPresenter.addMoment(
                    name = if (etNameMoment.text.toString()
                            .isEmpty()
                    ) DEFAULT_NAME_MOMENT else etNameMoment.text.toString(),
                    time = System.currentTimeMillis().toString(),

                    bitmap = (ivMainScreen.drawable as BitmapDrawable).bitmap
                )
                goToListMomentsActivity()
            }

            btnSavaMoments.setOnClickListener {
                mainPresenter.clickedExit()
            }

            topAppBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_settings -> {
                        goToSettingsActivity()
                        true
                    }

                    R.id.action_story -> {
                        goToListMomentsActivity()
                        true
                    }

                    else -> false
                }
            }
        }
        startService(Intent(this@MainActivity, MyService::class.java))
    }


    override fun startLaunch(intent: Intent) {
        activityResultLauncher.launch(intent)
    }

    override fun showPhotoFromCamera(bitmap: Bitmap) {
        binding.ivMainScreen.setImageBitmap(bitmap)
    }

    override fun showPhotoFromGallery(uriPhoto: Uri) {
        binding.ivMainScreen.setImageURI(uriPhoto)
    }


    override fun showHideCameraGalleryButtons(visibility: Boolean) {
        binding.btnCamera.isVisible = visibility
        binding.btnGallery.isVisible = visibility
    }

    private fun goToListMomentsActivity() {
        val intent = Intent(this@MainActivity, ListMomentsActivity::class.java)
        startActivity(intent)
    }

    private fun goToSettingsActivity() {
        val intent = Intent(this@MainActivity, SettingsActivity::class.java)
        startActivity(intent)
    }


}


