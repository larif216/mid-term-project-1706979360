package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.JuzDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.databases.TesJuzDatabase
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import java.lang.IllegalArgumentException

class JuzContentProvider: ContentProvider() {

    companion object {
        const val TAG = "id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.provider.JuzContentProvider"
        const val AUTHORITY = "id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.provider"
        const val JUZ_TABLE_NAME = "juz_table"
        const val ID_JUZ_DATA = 1
        const val ID_JUZ_DATA_ITEM = 2
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private lateinit var juzDao: JuzDao

    init {
        uriMatcher.addURI(AUTHORITY, JUZ_TABLE_NAME, ID_JUZ_DATA)
        uriMatcher.addURI(AUTHORITY, "$JUZ_TABLE_NAME/*", ID_JUZ_DATA_ITEM)
    }

    override fun onCreate(): Boolean {
        juzDao = TesJuzDatabase.getTesJuzDatabase(context!!).juzDao()
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when (uriMatcher.match(uri)) {
            ID_JUZ_DATA -> {
                cursor = juzDao.getJuzData()
                if (context != null) {
                    cursor.setNotificationUri(context!!.contentResolver, uri)
                    return cursor
                }
                return null
            }
            else -> {
                throw IllegalArgumentException("Unknown URI: $uri")
            }
        }
    }

    override fun getType(p0: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return -1
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return -1
    }
}