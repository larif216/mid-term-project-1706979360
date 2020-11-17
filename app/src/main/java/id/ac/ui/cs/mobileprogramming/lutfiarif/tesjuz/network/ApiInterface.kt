package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.network

import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("{juzNumber}/quran-uthmani")
    fun getJuzData(@Path(value = "juzNumber", encoded = true) juzNumber: Int): Call<JuzResponse>
}