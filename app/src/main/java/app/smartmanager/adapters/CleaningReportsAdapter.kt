package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.ChemicalListCOSHH
import app.smartmanager.datalayer.entity.CleaningRecord

class CleaningReportsAdapter: RecyclerView.Adapter<CleaningReportsAdapter.MyViewHolder>() {
    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val taskName: TextView = itemView.findViewById<TextView>(R.id.taskName)
        val cleaningCompletionDate: TextView = itemView.findViewById<TextView>(R.id.cleaningCompletionDate)

        fun bind(data: CleaningRecord){
            taskName.text = data.task_name
            cleaningCompletionDate.text = data.timestamp.toString()
        }
    }
    //List to contain ChemicalList objects
    private var cleaningRecordList = emptyList<CleaningRecord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return CleaningReportsAdapter.MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cleaning_report_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Setting current record
        val currentRecord = cleaningRecordList[position]

        holder.bind(cleaningRecordList[position])    }

    fun setDataSet(cleaningRecord: List<CleaningRecord>){
        this.cleaningRecordList = cleaningRecord

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {

        return cleaningRecordList.size
    }
}