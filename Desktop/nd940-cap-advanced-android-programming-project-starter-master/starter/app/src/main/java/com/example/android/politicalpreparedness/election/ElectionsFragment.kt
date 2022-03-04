package com.example.android.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.election.adapter.ElectionListAdapter

class ElectionsFragment: Fragment() {

    private val viewModel: ElectionsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,
            ElectionsViewModelFactory(activity.application))[ElectionsViewModel::class.java]
    }
    private val TAG = ElectionsFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentElectionBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = ElectionListAdapter(ElectionListAdapter.ElectionClickListener{
            viewModel.displayElectionDetails(it)
        })
        val savedElectionsAdapter = ElectionListAdapter(ElectionListAdapter.ElectionClickListener {
            viewModel.displayElectionDetails(it)
        })

        binding.upcomingElectionRecycler.adapter = adapter
        binding.savedElectionRecycler.adapter = savedElectionsAdapter

        viewModel.upcomingElections.observe(viewLifecycleOwner){
            it.let{
                adapter.submitList(it)
            }
        }

        viewModel.savedElections.observe(viewLifecycleOwner){
            it.let{
                savedElectionsAdapter.submitList(it)
            }
        }

        viewModel.navigateToSelectedElections.observe(this){
            if(null != it){
                this.findNavController().navigate(ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragement(it,viewModel.isElectionSaved(it.id)))
                viewModel.displayElectionDetailsComplete()
            }

        }

        return binding.root
    }



}