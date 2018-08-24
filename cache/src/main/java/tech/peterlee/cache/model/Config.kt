package tech.peterlee.cache.model

import android.arch.persistence.room.Entity
import tech.peterlee.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
class Config(val lastCacheTime: Long)
