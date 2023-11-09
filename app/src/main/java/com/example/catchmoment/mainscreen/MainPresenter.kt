package com.example.catchmoment.mainscreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import com.example.catchmoment.data.MomentRepository


class MainPresenter(private val repository: MomentRepository) {

    private var mainView: MainView? = null
    private lateinit var context: Context

    fun initView(mainView: MainView, context: Context) {
        this.mainView = mainView
        this.context = context
        repository.loadData(context)
    }

    fun addMoment(name: String, time: String, bitmap: Bitmap) {
        repository.addMoment(name = name, time = time, bitmap = bitmap)
    }

    fun getPhoto(result: ActivityResult) {

        if (result.resultCode == Activity.RESULT_OK) {
            val uriPhoto = result.data?.data
            if (uriPhoto != null) {
                mainView?.showPhotoFromGallery(uriPhoto)
            } else {
                val bitmap = result.data?.extras!!.get("data") as Bitmap
                mainView?.showPhotoFromCamera(bitmap)
            }
        }

    }

    fun clickedOnThePhoto(visibility: Boolean) {
        mainView?.showHideCameraGalleryButtons(!visibility)
    }

    fun clickedOnTheGalleryButton() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        mainView?.startLaunch(intent)
    }

    fun clickedOnTneCameraButton() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        mainView?.startLaunch(intent)
    }

    fun clickedExit() {
        repository.saveData(context)
    }



}



