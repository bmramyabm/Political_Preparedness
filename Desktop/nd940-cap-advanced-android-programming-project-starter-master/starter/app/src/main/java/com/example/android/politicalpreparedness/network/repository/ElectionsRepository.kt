package com.example.android.politicalpreparedness.network.repository

import android.util.Log
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.models.Address
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.models.State
import com.example.android.politicalpreparedness.network.models.VoterInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ElectionsRepository (private val database:ElectionDatabase){
    private val TAG = ElectionsRepository::class.java.simpleName

    suspend fun getUpcomingElections() :  List<Election>{
        return withContext(Dispatchers.IO){
            try{
              CivicsApi.retrofitService.getElections().elections
            }catch(e : Exception){
                Log.e(TAG,"Exception on Upcoming election: %s",e)
                emptyList<Election>()
            }
        }

    }
   suspend fun getVoterInfo(address: String,electionId: Int) : State?{
        return withContext(Dispatchers.IO){
            try{
                CivicsApi.retrofitService.getVoterInfo(address,electionId).state?.first()
            }catch(e: Exception){
               Log.e(TAG,"Exception on voters Info: %s",e)
               null
            }
        }
    }

    /*suspend fun  getVoterInfo(electionId: Int): State?{
        return withContext(Dispatchers.IO){
            try{
                CivicsApi.retrofitService.getVoterInfo(electionId).state?.first()
            }catch (e: Exception){
                Log.e(TAG,"Exception on Voter Info: %s",e)
                null
            }
        }
    }*/

    /*suspend fun getVoterInfo(address: String, electionId : Int): VoterInfoResponse? {
        return withContext(Dispatchers.IO){
            try {
                CivicsApi.retrofitService.getVoterInfo(address, electionId)
            }catch(e: Exception) {
                Log.e(TAG, "Exception at Voter Info %s", e)
                null
            }
        }
    }*/
    suspend fun getSavedElections(): List<Election> {
        return database.electionDao.getSavedElections()
    }

    suspend fun followUpcomingElection(election: Election) {
        database.electionDao.saveElection(election)
    }

    suspend fun unFollowUpcomingElection(id: Int) {
        database.electionDao.deleteElection(id)
    }

}