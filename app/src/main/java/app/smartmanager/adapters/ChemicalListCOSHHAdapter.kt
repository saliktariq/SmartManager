package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.ChemicalListCOSHH
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.ui.setup.ChemicalListCOSHHFragmentDirections
import app.smartmanager.ui.setup.SupplierFragmentDirections

class ChemicalListCOSHHAdapter : RecyclerView.Adapter<ChemicalListCOSHHAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val chemicalName = itemView.findViewById<TextView>(R.id.chemicalName)
        val chemicalPurpose = itemView.findViewById<TextView>(R.id.chemicalPurpose)
        val chemicalConcentration = itemView.findViewById<TextView>(R.id.chemicalConcentration)
        val notes = itemView.findViewById<TextView>(R.id.notes)

        //to implement onclicklistener for a single record
        val chemicalListCOSHHSingleRecord = itemView.findViewById<LinearLayout>(R.id.chemicalListCOSHHSingleRecord)

        fun bind(data: ChemicalListCOSHH) {
            chemicalName.text = data.name
            chemicalPurpose.text = data.purpose
            chemicalConcentration.text = data.concentration
            notes.text = data.notes

            //Setting onClickListener on dataset so user can click it and it will navigate to Update fragment
            chemicalListCOSHHSingleRecord.setOnClickListener {

                val action = ChemicalListCOSHHFragmentDirections.actionChemicalListCOSHHFragmentToUpdateChemicalListCOSHHFragment(data)
                itemView.findNavController().navigate(action)
            }
        }

    }


    //List to contain ChemicalList objects
    private var chemicalList = emptyList<ChemicalListCOSHH>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChemicalListCOSHHAdapter.MyViewHolder {

        //Returning chemical list item view
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chemical_list_coshh_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ChemicalListCOSHHAdapter.MyViewHolder, position: Int) {

        //Setting current record
        val currentRecord = chemicalList[position]

        holder.bind(chemicalList[position])
    }

    fun setDataSet(chemical: List<ChemicalListCOSHH>){
        this.chemicalList = chemical

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return chemicalList.size
    }


}

