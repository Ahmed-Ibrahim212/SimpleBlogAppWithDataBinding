package com.olamachia.simpleblogappwithdatabinding.cacheo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostCacheEntity::class, CommentCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao

    companion object {
        const val DATABASE_NAME: String = "blog_db"
    }

}