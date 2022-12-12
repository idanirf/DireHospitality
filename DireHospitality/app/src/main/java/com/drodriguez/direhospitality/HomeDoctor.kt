package com.drodriguez.direhospitality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeDoctor : Fragment() {

    var lista = ArrayList<Cita>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recycler: RecyclerView
        val vista = inflater.inflate(R.layout.fragment_home_doctor, container, false)
        recycler = vista.findViewById(R.id.idRecyclerViewList)
        recycler.layoutManager = LinearLayoutManager(context)

        recycler.adapter = DataAdapter(lista)
        return vista
    }

    fun llenarLista(list: ArrayList<Cita>){
        lista = list
    }


}