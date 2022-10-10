package uz.gita.newsapp.database

import androidx.room.TypeConverter
import uz.gita.newsapp.models.Source

class Convertors {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }
    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name,name)
    }
}