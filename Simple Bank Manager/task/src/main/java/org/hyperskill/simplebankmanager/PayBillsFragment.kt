package org.hyperskill.simplebankmanager

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

lateinit var billInfoMap: Map<String, Triple<String, String, Double>>
@SuppressLint("StaticFieldLeak")
lateinit var billsCodeInputText: EditText

class PayBillsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pay_bills, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val defaultBillInfoMap = mapOf(
            "ELEC" to Triple("Electricity", "ELEC", 45.0),
            "GAS" to Triple("Gas", "GAS", 20.0),
            "WTR" to Triple("Water", "WTR", 25.5)
        )

        billInfoMap = requireActivity().intent?.getSerializableExtra("billInfo") as? Map<String, Triple<String, String, Double>> ?: defaultBillInfoMap
        billsCodeInputText = view.findViewById(R.id.payBillsCodeInputEditText)
        val billsCodeButton = view.findViewById<Button>(R.id.payBillsShowBillInfoButton)

        billsCodeButton.setOnClickListener {
            if (billsCodeInputText.text.toString().isEmpty()) {
                wrongCodeOrEmpty()
            } else if (billsCodeInputText.text.toString() !in billInfoMap) {
                wrongCodeOrEmpty()
            } else if (billsCodeInputText.text.toString() in billInfoMap) {
                correctCode()
            }
        }

    }

    private fun wrongCodeOrEmpty() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage("Wrong code")
            .setPositiveButton("Ok") { _, _ -> }
            .show()
    }

    private fun correctCode() {
        AlertDialog.Builder(context)
            .setTitle("Bill info")
            .setMessage("Name: ${billInfoMap[billsCodeInputText.text.toString()]?.first}" +
                    "\nBillCode: ${billInfoMap[billsCodeInputText.text.toString()]?.second}" +
                    "\nAmount: $${"%.2f".format(billInfoMap[billsCodeInputText.text.toString()]?.third)}")
            .setPositiveButton("Confirm") { _, _ ->
                if (Balance.balance >= billInfoMap[billsCodeInputText.text.toString()]?.third!!.toDouble()) {
                    Balance.balance -= billInfoMap[billsCodeInputText.text.toString()]?.third!!.toDouble()
                    Toast.makeText(context, "Payment for bill ${billInfoMap[billsCodeInputText.text.toString()]?.first}, was successful", Toast.LENGTH_SHORT).show()
                } else {
                    notEnoughFunds()
                }
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .show()
    }

    private fun notEnoughFunds() {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage("Not enough funds")
            .setPositiveButton("Ok") { _, _ -> }
            .show()
    }

}