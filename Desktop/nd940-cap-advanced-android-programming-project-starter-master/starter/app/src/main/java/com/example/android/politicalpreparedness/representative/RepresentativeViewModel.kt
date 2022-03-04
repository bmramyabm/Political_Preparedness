package com.example.android.politicalpreparedness.representative

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.network.models.Address
import com.example.android.politicalpreparedness.network.repository.RepresenativesRepository
import com.example.android.politicalpreparedness.representative.model.Representative
import kotlinx.coroutines.launch

class RepresentativeViewModel(): ViewModel() {
    private val repository = RepresenativesRepository()

    val representatives = MutableLiveData<List<Representative>>()
    val addressLine1 = MutableLiveData<String>()
    val addressLine2 = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val zip = MutableLiveData<String>()
    val state = MutableLiveData<String>()
    val loading =MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String?>()

    fun searchRepresentatives(address: Address){
        addressLine1.value = address.line1
        addressLine2.value = address.line2
        city.value = address.city
        zip.value = address.zip
        state.value = address.state
        verifyRepresentatives()
   }

     fun verifyRepresentatives() {
        when {
            addressLine1.value.isNullOrEmpty() -> {
                errorMessage.value = "AddressLine1 Cannot be Empty"
            }
            addressLine2.value.isNullOrBlank() -> {
                errorMessage.value = "Address Line2 Cannot be Empty"
            }
            city.value.isNullOrBlank() -> {
                errorMessage.value = "City Cannot be Empty"
            }
            zip.value.isNullOrBlank() -> {
                errorMessage.value = "Zip Cannot be Empty"
            }
            state.value.isNullOrBlank() -> {
                errorMessage.value = "State Cannot be Empty"
            }

            else -> {
                    getRepresentatives(Address(addressLine1.value!!,addressLine2.value!!,city.value!!,zip.value!!,state.value!!))
            }
        }
    }

    private fun getRepresentatives(address: Address) {
        loading.value = true
        viewModelScope.launch {
            val (offices, officials) = repository.getRepresentatives(address)
            representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }
            loading.value = false
        }
    }

    fun setState(state: String) {
        this.state.value = state
    }

    fun postErrorMsgToast() {
        errorMessage.value = null
    }

}
