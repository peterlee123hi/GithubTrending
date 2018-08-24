package tech.peterlee.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import tech.peterlee.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        val lastCacheTime: Long
)
