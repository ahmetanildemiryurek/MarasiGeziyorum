package com.marazanil.marasigeziyorum.view.ui.fragment.registerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marazanil.marasigeziyorum.databinding.FragmentRegisterBinding
import com.marazanil.marasigeziyorum.data.db.AppDatabase
import com.marazanil.marasigeziyorum.viewmodel.UserViewModel
import com.marazanil.marasigeziyorum.viewmodel.UserViewModelFactory
import android.widget.Toast
import com.marazanil.marasigeziyorum.data.repo.UserRepository
import com.marazanil.marasigeziyorum.data.db.entity.User

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        val userDao = AppDatabase.getDatabase(requireContext()).userDao()
        UserViewModelFactory(UserRepository(userDao))
    }

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
            registerUser()
        }
        binding.btnTurnBackLoginPage.setOnClickListener{
            navigateToTurnBackLoginPage()
        }
    }

    private fun registerUser() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
            userViewModel.isUsernameTaken(username) { isTaken ->
                if (isTaken) {
                    Toast.makeText(requireContext(), "Kullanıcı adı tanımlı !", Toast.LENGTH_SHORT).show()
                } else {
                    val user = User(firstName = firstName, lastName = lastName, username = username, password = password)
                    userViewModel.insertUser(user)
                    Toast.makeText(requireContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Tüm alanları düzgünce doldurunuz!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin() {
        val action = RegisterFragmentDirections.actionFragmentRegisterToFragmentLogin()
        findNavController().navigate(action)
    }

    private fun navigateToTurnBackLoginPage() {
        val action = RegisterFragmentDirections.actionFragmentRegisterToFragmentLoginPage()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
