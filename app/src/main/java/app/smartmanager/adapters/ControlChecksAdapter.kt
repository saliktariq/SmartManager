package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.ControlChecks
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.ui.setup.ControlChecksFragmentDirections
import app.smartmanager.ui.setup.SupplierFragmentDirections

class ControlChecksAdapter: RecyclerView.Adapter<ControlChecksAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val controlName = itemView.findViewById<TextView>(R.id.controlName)
        val controlDescription = itemView.findViewById<TextView>(R.id.controlDescription)

        //to implement onclicklistener for a single record
        val controlChecksSingleRecord = itemView.findViewById<LinearLayout>(R.id.controlCheckSingleRecord)

        fun bind(data: ControlChecks) {
            controlName.text = data.name
            controlDescription.text = data.description

            //Setting onClickListener on dataset so user can click it and it will navigate to Update fragment
            controlChecksSingleRecord.setOnClickListener {

                val action = ControlChecksFragmentDirections.actionControlChecksFragmentToUpdateControlChecksFragment(data)
                itemView.findNavController().navigate(action)
            }
        }

    }


    //List to contain control checks objects
    private var controlChecksList = emptyList<ControlChecks>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlChecksAdapter.MyViewHolder {

        //Returning controlChecks list item view
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.control_check_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ControlChecksAdapter.MyViewHolder, position: Int) {

        //Setting current record
        val currentRecord = controlChecksList[position]

        holder.bind(controlChecksList[position])
    }

    fun setDataSet(controlChecks: List<ControlChecks>){
        this.controlChecksList = controlChecks

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return controlChecksList.size
    }


}
