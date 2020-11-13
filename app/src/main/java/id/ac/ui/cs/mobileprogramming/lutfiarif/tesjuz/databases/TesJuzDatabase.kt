package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.UserDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel

@Database(entities = [
    UserModel::class
],
    version = 1
)
abstract class TesJuzDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object {
        private var instance: TesJuzDatabase? = null

        fun getTesJuzDatabase(context: Context): TesJuzDatabase {
            if (instance == null) {
                synchronized(TesJuzDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TesJuzDatabase::class.java,
                        "tes_juz_database"
                    ).build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}