package com.example.catchmoment.mainscreen


import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri


interface MainView  {

    fun showHideCameraGalleryButtons(visibility:Boolean)
    fun startLaunch(intent: Intent)
    fun showPhotoFromCamera(bitmap: Bitmap)
    fun showPhotoFromGallery(uriPhoto: Uri)



}