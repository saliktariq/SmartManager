package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.Equipment
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.ui.setup.EquipmentFragmentDirections
import app.smartmanager.ui.setup.SupplierFragmentDirections

class EquipmentAdapter: RecyclerView.Adapter<EquipmentAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val equipmentName = itemView.findViewById<TextView>(R.id.equipmentName)

        //to implement onclicklistener for a single record
        val equipmentSingleRecord = itemView.findViewById<LinearLayout>(R.id.equipmentSingleRecord)

        fun bind(data: Equipment) {
            equipmentName.text = data.name


            //Setting onClickListener on dataset so user can click it and it will navigate to Update fragment
            equipmentSingleRecord.setOnClickListener {

                val action = EquipmentFragmentDirections.actionEquipmentFragmentToUpdateEquipmentFragment(data)
                itemView.findNavController().navigate(action)
            }
        }

    }


    //List to contain equipment objects
    private var equipmentList = emptyList<Equipment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentAdapter.MyViewHolder {

        //Returning equipment list item view
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.equipment_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: EquipmentAdapter.MyViewHolder, position: Int) {

        //Setting current record
        val currentRecord = equipmentList[position]

        holder.bind(equipmentList[position])
    }

    fun setDataSet(equipment: List<Equipment>){
        this.equipmentList = equipment

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return equipmentList.size
    }


}


