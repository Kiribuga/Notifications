package com.example.notifications.ui.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notifications.R
import com.example.notifications.databinding.FragmentEventBinding

class EventFragment : Fragment(R.layout.fragment_event) {

    private val vBinding: FragmentEventBinding by viewBinding()
    private val vmEvent: ViewModelEvent by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.sendEventButton.setOnClickListener {
            val title: String = vBinding.titleEvent.text.toString()
            val description: String = vBinding.descriptionEvent.text.toString()
            val image: String = vBinding.imageEvent.text.toString()
            vmEvent.eventNotification(title, description, image)

        }
        vBinding.cancelEventButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}