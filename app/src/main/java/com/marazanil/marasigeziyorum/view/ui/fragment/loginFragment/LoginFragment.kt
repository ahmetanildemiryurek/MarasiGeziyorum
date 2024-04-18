package com.marazanil.marasigeziyorum.view.ui.fragment.loginFragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.marazanil.marasigeziyorum.databinding.FragmentLoginBinding
import com.marazanil.marasigeziyorum.view.ui.fragment.splashFragment.SplashFragmentDirections

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Log.d("LoginFragment", "LoginFragment is now visible")

        binding.btnToRegisterScreen.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun navigateToRegister() {
        val action = LoginFragmentDirections.actionFragmentLoginToFragmentRegister()
        findNavController().navigate(action)
        //Log.d("Navigation", "Navigating from SplashFragment to LoginFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
