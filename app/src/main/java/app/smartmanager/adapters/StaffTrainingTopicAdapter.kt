package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.Probe
import app.smartmanager.datalayer.entity.StaffTrainingTopic


class StaffTrainingTopicAdapter(val listener: StaffTrainingTopicClickListener) :
    RecyclerView.Adapter<StaffTrainingTopicAdapter.MyViewHolder>() {
    var trainingTopics = emptyList<StaffTrainingTopic>()
    fun setTrainingTopicsData(data: ArrayList<StaffTrainingTopic>) {
        this.trainingTopics = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StaffTrainingTopicAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.staff_training_topic_list_item, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(trainingTopics[position])
    }

    override fun getItemCount(): Int {
        return trainingTopics.size
    }

    class MyViewHolder(view: View, val listener: StaffTrainingTopicClickListener) :
        RecyclerView.ViewHolder(view) {

        val topicName = view.findViewById<TextView>(R.id.topicName)
        val deleteTopic = view.findViewById<Button>(R.id.deleteTopic)
        fun bind(data: StaffTrainingTopic) {
            topicName.text = data.topic

            deleteTopic.setOnClickListener {
                listener.onDeleteTopicClickListener(data)
            }
        }
    }

    interface StaffTrainingTopicClickListener {
        fun onDeleteTopicClickListener(staffTrainingTopic: StaffTrainingTopic)
    }


}