package org.hyperskill.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CalculateExchangeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val defaultMap = mapOf(
            "EUR" to mapOf(
                "GBP" to 0.5,
                "USD" to 2.0
            ),
            "GBP" to mapOf(
                "EUR" to 2.0,
                "USD" to 4.0
            ),
            "USD" to mapOf(
                "EUR" to 0.5,
                "GBP" to 0.25
            )
        )
        val exchangeMap =
            requireActivity().intent?.getSerializableExtra("exchangeMap") as? Map<String, Map<String, Double>>
                ?: defaultMap

        val spinnerFrom = view.findViewById<Spinner>(R.id.calculateExchangeFromSpinner)
        val spinnerTo = view.findViewById<Spinner>(R.id.calculateExchangeToSpinner)

        spinnerFrom.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (spinnerFrom.selectedItem.toString() == spinnerTo.selectedItem.toString()) {
                    Toast.makeText(
                        requireContext(),
                        "Cannot convert to same currency",
                        Toast.LENGTH_SHORT
                    ).show()
                    spinnerTo.setSelection(spinnerTo.selectedItemPosition % 2 + 1)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        spinnerTo.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (spinnerFrom.selectedItem.toString() == spinnerTo.selectedItem.toString()) {
                    Toast.makeText(
                        requireContext(),
                        "Cannot convert to same currency",
                        Toast.LENGTH_SHORT
                    ).show()
                    spinnerTo.setSelection(spinnerTo.selectedItemPosition % 2 + 1)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        view.findViewById<Button>(R.id.calculateExchangeButton).setOnClickListener {
            val from = spinnerFrom.selectedItem.toString()
            val to = spinnerTo.selectedItem.toString()
            val course = exchangeMap[from]?.get(to)

            val fromSymbol = when (from) {
                "EUR" ->  EUR
                "GBP" ->  GBP
                "USD" ->  USD
                else -> ""
            }

            val toSymbol = when (to) {
                "EUR" ->  EUR
                "GBP" ->  GBP
                "USD" ->  USD
                else -> ""
            }

            if (view.findViewById<EditText>(R.id.calculateExchangeAmountEditText).text.isEmpty()) {
                Toast.makeText(context, "Enter amount", Toast.LENGTH_SHORT).show()
            } else {
                val input = view.findViewById<EditText>(R.id.calculateExchangeAmountEditText).text.toString().toDouble()
                view.findViewById<TextView>(R.id.calculateExchangeDisplayTextView).text =
                    "$fromSymbol${"%.2f".format(input)} = $toSymbol${"%.2f".format(input * course!!)}"
            }

        }

    }

    companion object {
        val USD = "$"
        val EUR = "€"
        val GBP = "£"
    }
}