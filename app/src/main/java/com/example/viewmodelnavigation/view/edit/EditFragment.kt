package com.example.viewmodelnavigation.view.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewmodelnavigation.databinding.FragmentEditBinding
import com.example.viewmodelnavigation.view.base.BaseFragment
import com.example.viewmodelnavigation.view.base.BaseScreen
import com.example.viewmodelnavigation.view.base.screenViewModel

class EditFragment : BaseFragment() {

    override val viewModel: EditViewModel by screenViewModel()

    class Screen(val initialValue: String) : BaseScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditBinding.inflate(
            layoutInflater,
            container,
            false
        )
        binding.saveButton.setOnClickListener {
            viewModel.onSavePressed(binding.valueEditText.text.toString())
        }
        binding.cancelButton.setOnClickListener {
            viewModel.onCancelPressed()
        }
        viewModel.initialMessageEvent.observe(
            viewLifecycleOwner
        ) {
            binding.valueEditText.setText(it.getValue())
        }
        return binding.root
    }
}