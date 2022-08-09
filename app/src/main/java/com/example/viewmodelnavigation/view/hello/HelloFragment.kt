package com.example.viewmodelnavigation.view.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewmodelnavigation.databinding.FragmentHelloBinding
import com.example.viewmodelnavigation.view.base.BaseFragment
import com.example.viewmodelnavigation.view.base.BaseScreen
import com.example.viewmodelnavigation.view.base.screenViewModel

class HelloFragment : BaseFragment() {

    override val viewModel: HelloViewModel by screenViewModel()

    class Screen : BaseScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHelloBinding.inflate(inflater, container, false)
        binding.editButton.setOnClickListener {
            viewModel.onEditPressed()
        }
        viewModel.currentMessageLiveData.observe(
            viewLifecycleOwner
        ) {
            binding.valueTextView.text = it
        }
        return binding.root
    }
}