package com.marazanil.marasigeziyorum.view.ui.fragment.splashFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.marazanil.marasigeziyorum.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val action = SplashFragmentDirections.actionSplashFragmentToFragmentLogin()
        findNavController().navigate(action)
        //Log.d("Navigation", "Navigating from SplashFragment to LoginFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
