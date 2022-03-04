package com.example.android.politicalpreparedness.network.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.android.politicalpreparedness.network.CivicsApi
import com.example.android.politicalpreparedness.network.CivicsApi.retrofitService
import com.example.android.politicalpreparedness.network.models.Address
import com.example.android.politicalpreparedness.network.models.RepresentativeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepresenativesRepository() {

    private val TAG = RepresenativesRepository::class.java.simpleName

    suspend fun getRepresentatives(address: Address): RepresentativeResponse {
        return withContext(Dispatchers.IO) {
            retrofitService.getRepresentatives(address.toFormattedString())
        }
    }

}