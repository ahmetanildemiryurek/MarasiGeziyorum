package com.marazanil.marasigeziyorum.view.ui.fragment.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marazanil.marasigeziyorum.R
import com.marazanil.marasigeziyorum.databinding.FragmentLoginBinding
import com.marazanil.marasigeziyorum.viewModel.UserViewModel
import com.marazanil.marasigeziyorum.viewModel.UserViewModelFactory


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels { UserViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            userViewModel.loginUser(username, password) { success ->
                if (success) {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.actionLoginFragmentToMainActivity)
                } else {
                    Toast.makeText(requireContext(), "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnToRegisterScreen.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

