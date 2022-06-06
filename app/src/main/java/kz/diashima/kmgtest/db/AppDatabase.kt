package kz.diashima.kmgtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.diashima.kmgtest.db.dao.ProducerDao
import kz.diashima.kmgtest.db.entity.Accumulation
import kz.diashima.kmgtest.db.entity.BoreHole
import kz.diashima.kmgtest.db.entity.Note
import kz.diashima.kmgtest.db.entity.Producer

@Database(entities = [Producer::class, Accumulation::class, BoreHole::class, Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun producerDao(): ProducerDao
}