package kz.diashima.kmgtest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producer(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)
