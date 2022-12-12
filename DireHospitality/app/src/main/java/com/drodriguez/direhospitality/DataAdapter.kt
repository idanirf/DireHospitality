package com.drodriguez.direhospitality

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(
    private var dataList: List<Cita>,
): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var paciente: TextView = view.findViewById(R.id.pacienteView)
        var fecha: TextView = view.findViewById(R.id.fechaView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.paciente.setText("Paciente: ${item.paciente.toString()}")
        holder.fecha.setText("Fecha: ${item.fechaCita.toString()}")
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(list:List<Cita>){
        this.dataList = list
    }
}