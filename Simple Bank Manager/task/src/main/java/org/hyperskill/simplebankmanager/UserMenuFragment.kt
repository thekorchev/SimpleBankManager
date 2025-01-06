package org.hyperskill.simplebankmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

private const val USER_NAME_KEY = "username"

class UserMenuFragment : Fragment() {
    private var userNameValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userNameValue = it.getString(USER_NAME_KEY)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_menu, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.userMenuWelcomeTextView).text = "Welcome $userNameValue"
        view.findViewById<Button>(R.id.userMenuViewBalanceButton).setOnClickListener {
            view.findNavController().navigate(
                R.id.action_userMenuFragment_to_viewBalanceFragment,
                bundleOf("balance" to Balance.balance.toString())
            )
        }

        view.findViewById<Button>(R.id.userMenuTransferFundsButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_userMenuFragment_to_transferFundsFragment)
        }

        view.findViewById<Button>(R.id.userMenuExchangeCalculatorButton).setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_userMenuFragment_to_calculateExchangeFragment)
        }

        view.findViewById<Button>(R.id.userMenuPayBillsButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_userMenuFragment_to_payBillsFragment)
        }
    }
}