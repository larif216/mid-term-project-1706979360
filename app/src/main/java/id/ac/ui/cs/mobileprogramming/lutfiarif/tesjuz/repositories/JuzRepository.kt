package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.AyahDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.JuzDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.AyahModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzResponse
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.network.ApiClient
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.network.ApiInterface
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.network.ConnectivityHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JuzRepository private constructor(private val juzDao: JuzDao, private val ayahDao: AyahDao, private val context: Context){
    fun getJuzWithAyah(juzNumber: Int): JuzWithAyah {
        val juzData = juzDao.getJuzWithAyahByNumber(juzNumber)
        if (juzData.ayahs.isEmpty() && ConnectivityHelper().isConnectedToNetwork(context)) {
            val apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
            val call = apiInterface.getJuzData(juzNumber)
            val response = call.execute()
            val data = response.body()?.data
            for (ayah in data!!.ayahs) {
                ayahDao.insert(
                    AyahModel(
                        text = ayah.text,
                        surah = ayah.surah.englishName,
                        number = ayah.numberInSurah,
                        juzNumber = juzNumber
                    )
                )
            }
//            call.enqueue(object: Callback<JuzResponse> {
//                override fun onResponse(call: Call<JuzResponse>, response: Response<JuzResponse>) {
//                    val data = response.body()?.data
//                    for (ayah in data!!.ayahs) {
//                        ayahDao.insert(
//                            AyahModel(
//                                text = ayah.text,
//                                surah = ayah.surah.englishName,
//                                number = ayah.numberInSurah,
//                                juzNumber = juzNumber
//                            )
//                        )
//                    }
//                }
//
//                override fun onFailure(call: Call<JuzResponse>, t: Throwable) {
//                    Toast.makeText(context, "Fail to fetch data from server", Toast.LENGTH_SHORT).show()
//                }
//            })
        }
        return juzDao.getJuzWithAyahByNumber(juzNumber)
    }

    fun getAllJuzWithAyah(): List<JuzWithAyah> {
        return juzDao.getAllJuzWithAyah()
    }

    companion object {
        private var instance: JuzRepository? = null

        fun getInstance(juzDao: JuzDao, ayahDao: AyahDao, context: Context): JuzRepository {
            if (instance == null) {
                instance = JuzRepository(juzDao, ayahDao, context)
            }
            return instance!!
        }
    }
}