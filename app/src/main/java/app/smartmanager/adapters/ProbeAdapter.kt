package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.Probe


// https://www.youtube.com/watch?v=aK9tOipNm0o

class ProbeAdapter(val listener: ProbeClickListenter) : RecyclerView.Adapter<ProbeAdapter.MyViewHolder>() {

    var probes = ArrayList<Probe>()

    fun setProbeData(data: ArrayList<Probe>){
        this.probes = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProbeAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.probe_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        holder.itemView.setOnClickListener{
//            listener.onProbeClickListener(probes[position])
//        }
        holder.bind(probes[position])
    }

    override fun getItemCount(): Int {
        return probes.size
    }


    class MyViewHolder(view: View, val listener: ProbeClickListenter) : RecyclerView.ViewHolder(view) {

        val probeName = view.findViewById<TextView>(R.id.probeName)
        val deleteProbe = view.findViewById<Button>(R.id.deleteProbe)
        fun bind(data: Probe) {
            probeName.text = data.probeName

            deleteProbe.setOnClickListener {
                listener.onDeleteProbeClickListener(data)

            }


        }

    }


    interface ProbeClickListenter{
        fun onDeleteProbeClickListener(probe: Probe)
//        fun onProbeClickListener(probe: Probe)
    }


}