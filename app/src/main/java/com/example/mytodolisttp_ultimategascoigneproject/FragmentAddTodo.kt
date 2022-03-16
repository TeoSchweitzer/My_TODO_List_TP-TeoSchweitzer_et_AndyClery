package com.example.mytodolisttp_ultimategascoigneproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class FragmentAddTodo: Fragment() {


    var title: EditText? = null
    var description: EditText? = null
    var addButton: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View {
        return inflater.inflate(R.layout.fragment_add_element, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = requireView().findViewById(R.id.titleAddElement)
        description = requireView().findViewById(R.id.descriptionAddElement)
        addButton = requireView().findViewById(R.id.buttonAddElement)

        addButton?.setOnClickListener {
            saveList()
            val action = FragmentAddTodoDirections.actionFragmentAddTodoToFragmentListTodo()
            addButton!!.findNavController().navigate(action)
        }
    }

    private fun saveList() {
        val sharedPreferences = requireContext().getSharedPreferences("list", AppCompatActivity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putString(title!!.text.toString(), description!!.text.toString())
        editor.apply()
    }
}