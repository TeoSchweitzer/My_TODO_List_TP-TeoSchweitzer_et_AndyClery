package com.example.mytodolisttp_ultimategascoigneproject

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentListTodo : Fragment() {

    var listText: RecyclerView? = null
    var addButton: Button? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButton = requireView().findViewById(R.id.addButton)
        addButton?.setOnClickListener {
            val action = FragmentListTodoDirections.actionFragmentListTodoToFragmentAddTodo()
            addButton!!.findNavController().navigate(action)
        }
        getPreferences()
    }

    private fun getPreferences() {
        sharedPreferences = requireContext().getSharedPreferences("list", AppCompatActivity.MODE_PRIVATE)
        val myDataset: ArrayList<String> = arrayListOf()
        for (i: MutableMap.MutableEntry<String, out Any?> in sharedPreferences!!.all){
            myDataset.add(i.key+": "+i.value)
        }
        listText = requireView().findViewById(R.id.main_recycler)
        listText?.layoutManager = LinearLayoutManager(requireContext())
        listText?.adapter = MainAdapter(myDataset)
        val dividerItemDecoration = DividerItemDecoration(
            listText?.context,
            (listText?.layoutManager as LinearLayoutManager).orientation
        )
        listText?.addItemDecoration(dividerItemDecoration)
        Log.println(Log.ASSERT, "MYLOGS", "in main: "+myDataset.joinToString(", "))
    }
}