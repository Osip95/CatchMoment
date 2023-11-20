package com.example.catchmoment.data


import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.catchmoment.Moment
import kotlin.collections.ArrayList


const val PREF_NAME_MOMENTS = "PREF_NAME_MOMENTS"
const val PREF_NAMES_FILE = "PREF_NAMES_FILE"
const val PREF_TIME = "PREF_TIME"
const val SIZE_LIST_MOMENT = "SIZE_LIST_MOMENT"


class MomentRepositoryImpl() : MomentRepository {
    private val listMoments: MutableLiveData<ArrayList<Moment>> = MutableLiveData()
   private val newFirstLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        listMoments.value = arrayListOf()
        newFirstLiveData.value = false
    }

    override fun addMoment(name: String, time: String, bitmap: Bitmap) {
        listMoments.value?.add(Moment(name = name, time = time, bitmap = bitmap))
    }

    override fun getLiveDataMoments(): MutableLiveData<ArrayList<Moment>> {
        return listMoments
    }

    override fun saveData(context: Context) {
        if (listMoments.value?.isEmpty() == true) return

        val spNameMoments: SharedPreferences = context.getSharedPreferences(
            PREF_NAME_MOMENTS,
            AppCompatActivity.MODE_PRIVATE
        )
        val spNameFile: SharedPreferences = context.getSharedPreferences(
            PREF_NAMES_FILE,
            AppCompatActivity.MODE_PRIVATE
        )
        val spTime: SharedPreferences = context.getSharedPreferences(
            PREF_TIME,
            AppCompatActivity.MODE_PRIVATE
        )
        spNameMoments.edit().putInt(SIZE_LIST_MOMENT, listMoments.value?.size ?: 0).apply()

        listMoments.value?.forEachIndexed { index, moment ->
            spNameFile.edit().putString(index.toString(), "moment$index").apply()
            spNameMoments.edit().putString(index.toString(), moment.name).apply()
            spTime.edit().putString(index.toString(), moment.time).apply()

            val out = context.openFileOutput("moment$index.png", AppCompatActivity.MODE_PRIVATE)
            moment.bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
        }

    }

    override fun loadData(context: Context) {
        val spNameMoments: SharedPreferences = context.getSharedPreferences(
            PREF_NAME_MOMENTS,
            AppCompatActivity.MODE_PRIVATE
        )
        if (spNameMoments.getInt(SIZE_LIST_MOMENT, 0) == 0) return
        val spNameFile: SharedPreferences = context.getSharedPreferences(
            PREF_NAMES_FILE,
            AppCompatActivity.MODE_PRIVATE
        )
        val spTime: SharedPreferences = context.getSharedPreferences(
            PREF_TIME,
            AppCompatActivity.MODE_PRIVATE
        )


        for (i in 0 until spNameMoments.getInt(SIZE_LIST_MOMENT, 0)) {

            val patchFile = spNameFile.getString(i.toString(), "") + ".png"
            val nameMoment = spNameMoments.getString(i.toString(), "")
            val time = spTime.getString(i.toString(), "")
            listMoments.value?.add(
                Moment(
                    bitmap = BitmapFactory.decodeFile(
                        context.getFileStreamPath(patchFile).toString()
                    ),
                    name = nameMoment ?: "",
                    time = time ?: ""
                )
            )
        }

    }

    override fun getLiveDataCheckBoxNewFirst(): MutableLiveData<Boolean> {
        return newFirstLiveData
    }


}











