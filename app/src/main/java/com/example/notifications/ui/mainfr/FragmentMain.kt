package com.example.notifications.ui.mainfr

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notifications.R
import com.example.notifications.databinding.MainFragmentBinding

class FragmentMain : Fragment(R.layout.main_fragment) {

    private val vBinding: MainFragmentBinding by viewBinding()
    private val vmNotification: ViewModelNotification by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationManagerCompat.from(requireContext()).cancelAll()
        vmNotification.getToken()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVM()
        vBinding.messageNotification.setOnClickListener {
            findNavController().navigate(
                FragmentMainDirections.actionFragmentMainToMessageFragment()
            )
        }
        vBinding.eventNotification.setOnClickListener {
            findNavController().navigate(
                FragmentMainDirections.actionFragmentMainToEventFragment()
            )
        }
        vBinding.syncing.setOnClickListener {
            findNavController().navigate(
                FragmentMainDirections.actionFragmentMainToSyncingFragment()
            )
        }
    }

    private fun observeVM() {
        vmNotification.tokenLD.observe(viewLifecycleOwner) {
            Log.d("MainFragment", "token = $it")
        }
    }
}