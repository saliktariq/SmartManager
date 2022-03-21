package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.DeliveryRecord
import app.smartmanager.datalayer.entity.StaffTrainingRecord


class StaffTrainingRecordAdapter(val listener: StaffTrainingRecordClickListenter) : RecyclerView.Adapter<StaffTrainingRecordAdapter.MyViewHolder>() {

    var staffTrainingRecord = ArrayList<StaffTrainingRecord>()

    fun setStaffTrainingRecordData(data: ArrayList<StaffTrainingRecord>){
        this.staffTrainingRecord = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffTrainingRecordAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.staff_training_record_list_item,parent,false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(staffTrainingRecord[position])
    }

    override fun getItemCount(): Int {
        return staffTrainingRecord.size
    }


    class MyViewHolder(view: View, val listener: StaffTrainingRecordClickListenter) : RecyclerView.ViewHolder(view) {

        val topicName: TextView = view.findViewById<TextView>(R.id.topicName)
        val staffName: TextView = view.findViewById<TextView>(R.id.staffName)
        val date: TextView = view.findViewById<TextView>(R.id.date)
        val deleteTrainingRecord: Button = view.findViewById<Button>(R.id.deleteTrainingRecord)
        fun bind(data: StaffTrainingRecord) {
            topicName.text = data.training_topic
            staffName.text = data.staff_name
            date.text = data.timestamp.toString()


            deleteTrainingRecord.setOnClickListener {
                listener.onDeleteStaffTrainingRecordClickListener(data)
            }
        }
    }


    interface StaffTrainingRecordClickListenter{
        fun onDeleteStaffTrainingRecordClickListener(staffTrainingRecord: StaffTrainingRecord)
    }
}