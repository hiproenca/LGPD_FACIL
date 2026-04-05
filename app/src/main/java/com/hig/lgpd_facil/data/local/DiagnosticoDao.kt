package com.hig.lgpd_facil.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiagnosticoDao {
    @Query("SELECT * FROM empresa_diagnostico WHERE id = 1")
    suspend fun getDiagnostico(): DiagnosticoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarDiagnostico(diagnostico: DiagnosticoEntity)
}