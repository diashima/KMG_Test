package kz.diashima.kmgtest.db.dao

import androidx.room.*
import kz.diashima.kmgtest.db.entity.Accumulation
import kz.diashima.kmgtest.db.entity.BoreHole
import kz.diashima.kmgtest.db.entity.Note
import kz.diashima.kmgtest.db.entity.Producer

@Dao
interface ProducerDao {
    @Query("SELECT * FROM Producer")
    fun getAllProducers() : List<Producer>

    @Query("SELECT * FROM Accumulation WHERE producer_id == :producerId")
    fun getAccumulationByProducerId(producerId: Int) : List<Accumulation>

    @Query("SELECT * FROM BoreHole WHERE accumulation_id == :accumulationId")
    fun getBoreholeByAccumulationId(accumulationId: Int) : List<BoreHole>

    @Query("SELECT * FROM Note WHERE borehole_id == :boreholeId")
    fun getNotes(boreholeId: Int) : List<Note>

    @Insert
    fun insertNotes(notes: List<Note>)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)
}