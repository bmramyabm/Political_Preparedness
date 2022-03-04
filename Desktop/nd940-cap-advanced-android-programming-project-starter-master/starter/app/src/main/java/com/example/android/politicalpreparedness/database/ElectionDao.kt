package com.example.android.politicalpreparedness.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.politicalpreparedness.network.models.Election

@Dao
interface ElectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveElection(election: Election)

    @Query("Select * from election_table order by electionDay")
    suspend fun  getSavedElections() : List<Election>

    @Query("Select * from election_table where id = :id")
    fun getElectionById(id: Int): LiveData<Election>

    @Query("delete from election_table where id = :id")
    suspend fun deleteElection(id: Int)

    @Query("delete from election_table")
    suspend fun deleteAllElections()



}