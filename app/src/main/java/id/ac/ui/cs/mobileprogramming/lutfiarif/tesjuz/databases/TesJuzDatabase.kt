package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.databases

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.AyahDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.JuzDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.UserDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.AyahModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel
import java.util.concurrent.Executors

@Database(entities = [
    UserModel::class,
    JuzModel::class,
    AyahModel::class
],
    version = 1
)
abstract class TesJuzDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun juzDao(): JuzDao
    abstract fun ayahDao(): AyahDao

    companion object {
        private var instance: TesJuzDatabase? = null

        fun getTesJuzDatabase(context: Context): TesJuzDatabase {
            if (instance == null) {
                synchronized(TesJuzDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TesJuzDatabase::class.java,
                        "tes_juz_database"
                    ).addCallback(
                        object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    for (i in 1..30) {
                                        getTesJuzDatabase(context).juzDao().insert(JuzModel(i))
                                    }
                                }
                            }
                        }
                    ).allowMainThreadQueries().build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}