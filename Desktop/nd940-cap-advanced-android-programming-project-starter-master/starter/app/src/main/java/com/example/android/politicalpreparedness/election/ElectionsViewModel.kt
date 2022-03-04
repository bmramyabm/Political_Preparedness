package com.example.android.politicalpreparedness.election

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.network.models.Election
import com.example.android.politicalpreparedness.network.repository.ElectionsRepository
import kotlinx.coroutines.launch

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(application: Application): ViewModel() {
    val loading = MutableLiveData<Boolean>(false)

    private val database = ElectionDatabase.getInstance(application)
    private val repository = ElectionsRepository(database)




    private var _upcomingElections = MutableLiveData<List<Election>>()
    val upcomingElections : LiveData<List<Election>>
    get() = _upcomingElections

    private var _savedElections = MutableLiveData<List<Election>>()
    val savedElections :LiveData<List<Election>>
    get() = _savedElections

    private val _navigateToSelectedElections = MutableLiveData<Election?>()
    val navigateToSelectedElections : LiveData<Election?>
    get() = _navigateToSelectedElections

    init{
        loading.value = true
        viewModelScope.launch{
            _upcomingElections.value = repository.getUpcomingElections()
            _savedElections.value = repository.getSavedElections()
            loading.value = false
        }

    }
    fun displayElectionDetails(election: Election) {
        _navigateToSelectedElections.value = election
    }

    fun displayElectionDetailsComplete() {
        _navigateToSelectedElections.value = null
    }

    fun isElectionSaved(id: Int): Boolean {
        return _savedElections.value?.map { it.id }?.contains(id) ?: false
    }


}