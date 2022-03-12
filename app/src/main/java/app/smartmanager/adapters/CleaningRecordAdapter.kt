package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CleaningRecord


// https://www.youtube.com/watch?v=aK9tOipNm0o

class CleaningRecordAdapter(val listener: CleaningRecordClickListenter) : RecyclerView.Adapter<CleaningRecordAdapter.MyViewHolder>() {

    var cleaningRecord = ArrayList<CleaningRecord>()

    fun setCleaningRecordData(data: ArrayList<CleaningRecord>){
        this.cleaningRecord = data

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleaningRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cleaning_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(cleaningRecord[position])
    }

    override fun getItemCount(): Int {
        return cleaningRecord.size
    }


    class MyViewHolder(view: View, val listener: CleaningRecordClickListenter) : RecyclerView.ViewHolder(view) {

        val taskName = view.findViewById<TextView>(R.id.taskName)
        val cleaningCompletionDate = view.findViewById<TextView>(R.id.cleaningCompletionDate)
        val deleteCleaningTask = view.findViewById<Button>(R.id.deleteCleaningTask)
        fun bind(data: CleaningRecord) {
            taskName.text = data.task_name
            cleaningCompletionDate.text = data.timestamp.toString()

            deleteCleaningTask.setOnClickListener {
                listener.onDeleteProbeClickListener(data)

            }


        }

    }


    interface CleaningRecordClickListenter{
        fun onDeleteProbeClickListener(cleaningRecord: CleaningRecord)

    }


}