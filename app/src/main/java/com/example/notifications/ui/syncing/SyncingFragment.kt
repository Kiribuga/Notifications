package com.example.notifications.ui.syncing

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notifications.R
import com.example.notifications.data.NetworkBroadcastReceiver
import com.example.notifications.databinding.FragmentSyncingBinding

class SyncingFragment : Fragment(R.layout.fragment_syncing) {

    private val vBinding: FragmentSyncingBinding by viewBinding()
    private val vmSyncing: ViewModelSyncing by viewModels()
    private val receiver = NetworkBroadcastReceiver()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVM()
        vBinding.syncingButton.setOnClickListener {
            if (receiver.isOnline(requireContext())) {
                vmSyncing.progressNotification()
            } else {
                Toast.makeText(requireContext(), "Connect to the Internet", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun observeVM() {
        vmSyncing.loadLD.observe(viewLifecycleOwner, ::loadProgress)
    }

    private fun loadProgress(result: Boolean) {
        vBinding.syncingButton.isEnabled = !result
    }

    override fun onResume() {
        super.onResume()
        requireContext().registerReceiver(
            receiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(receiver)
    }
}