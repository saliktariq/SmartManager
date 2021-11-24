package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.Supplier

class SupplierAdapter: RecyclerView.Adapter<SupplierAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val supplierName = itemView.findViewById<TextView>(R.id.supplierName)
        val supplierEmail = itemView.findViewById<TextView>(R.id.supplierEmail)
        val supplierPhone = itemView.findViewById<TextView>(R.id.supplierPhone)
        val supplierAddress = itemView.findViewById<TextView>(R.id.supplierAddress)

        //to implement onclicklistener for a single record
        val supplierSingleRecord = itemView.findViewById<LinearLayout>(R.id.supplierSingleRecord)

        fun bind(data: Supplier) {
            supplierName.text = data.name
            supplierEmail.text = data.email
            supplierPhone.text = data.phone
            supplierAddress.text = data.address

        }

    }


    //List to contain supplier objects
    private var supplierList = emptyList<Supplier>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierAdapter.MyViewHolder {

        //Returning supplier list item view
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.supplier_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: SupplierAdapter.MyViewHolder, position: Int) {

        //Setting current record
        val currentRecord = supplierList[position]

        holder.bind(supplierList[position])
    }

    fun setDataSet(supplier: List<Supplier>){
        this.supplierList = supplier

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return supplierList.size
    }


}

