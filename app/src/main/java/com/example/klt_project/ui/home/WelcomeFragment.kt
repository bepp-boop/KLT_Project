package com.example.klt_project.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.klt_project.R
import com.example.klt_project.databinding.FragmentWelcomeBinding


@Suppress("DEPRECATION")
class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        Handler().postDelayed({
            binding.continueButton.isEnabled = true
            binding.continueButton.setOnClickListener {
                requireFragmentManager().popBackStack()
                Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_nav_home)
            }
        }, 2000)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}