package fastcampus.aop.part5.chapter05.data.db.entity

import androidx.room.*

@Entity
data class StationEntity(
    @PrimaryKey val stationName: String,
    val isFavorited: Boolean = false
)
