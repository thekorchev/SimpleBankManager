package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
  import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var loginUsername: EditText? = null
    private var loginPassword: EditText? = null
    private var loginButton: Button? = null
    private var nameCheck: String? = null
    private var passwordCheck: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginUsername = view.findViewById(R.id.loginUsername)
        loginPassword = view.findViewById(R.id.loginPassword)
        loginButton = view.findViewById(R.id.loginButton)
        nameCheck  = requireActivity().intent.extras?.getString("username") ?: "Lara"
        passwordCheck = requireActivity().intent.extras?.getString("password") ?: "1234"

        loginButton?.setOnClickListener{
            checkData()
        }

    }

    private fun checkData() {
        if (loginUsername?.text?.trim().toString() == nameCheck && loginPassword?.text?.trim().toString() == passwordCheck )  {
            Toast.makeText(requireContext(), "logged in", Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(
                R.id.action_loginFragment_to_userMenuFragment,
                bundleOf("username" to loginUsername?.text?.trim().toString())
            )
        } else {
            Toast.makeText(requireContext(), "invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

}