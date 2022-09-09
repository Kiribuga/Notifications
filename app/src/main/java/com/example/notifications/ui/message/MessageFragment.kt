package com.example.notifications.ui.message

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notifications.R
import com.example.notifications.databinding.FragmentMessageBinding

class MessageFragment : Fragment(R.layout.fragment_message) {

    private val vBinding: FragmentMessageBinding by viewBinding()
    private val vmMessage: ViewModelMessage by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.sendButton.setOnClickListener {
            val userId: Long = vBinding.idUser.text.toString().toLong()
            val userName: String = vBinding.userName.text.toString()
            val message: String = vBinding.textMessage.text.toString()
            vmMessage.messageNotification(userId, userName, message)
        }

        vBinding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}