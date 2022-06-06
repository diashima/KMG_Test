package kz.diashima.kmgtest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "borehole_id") val boreholeId: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "oil_extraction") var oilExtraction: Int,
    @ColumnInfo(name = "liquid_extraction") val liquidExtraction: Int,
    @ColumnInfo(name = "water_cut") val waterCut: String? = null,
    @ColumnInfo(name = "category") val category: String? = null,
    @ColumnInfo(name = "ndin") val ndin: String? = null,
    @ColumnInfo(name = "gas_debit") val gasDebit: String? = null,
    @ColumnInfo(name = "form") val form: String? = null
)
