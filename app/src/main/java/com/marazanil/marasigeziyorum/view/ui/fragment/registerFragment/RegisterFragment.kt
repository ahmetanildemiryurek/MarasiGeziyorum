package com.marazanil.marasigeziyorum.view.ui.fragment.registerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.marazanil.marasigeziyorum.databinding.FragmentRegisterBinding
import com.marazanil.marasigeziyorum.viewModel.UserViewModel
import com.marazanil.marasigeziyorum.viewModel.UserViewModelFactory
import androidx.navigation.fragment.findNavController
import com.marazanil.marasigeziyorum.R

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels { UserViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            userViewModel.registerUser(firstName, lastName, username, password) { success ->
                if (success) {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
                } else {
                    Toast.makeText(requireContext(), "Registration failed. Username may be taken.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnTurnBackLoginPage.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
