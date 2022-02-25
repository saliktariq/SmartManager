package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.CleaningTask
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.ui.setup.CleaningTaskFragmentDirections
import app.smartmanager.ui.setup.SupplierFragmentDirections

class CleaningTaskAdapter: RecyclerView.Adapter<CleaningTaskAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val cleaningTaskName = itemView.findViewById<TextView>(R.id.cleaningTaskName)
        val cleaningTaskDescription = itemView.findViewById<TextView>(R.id.cleaningTaskDescription)
        val taskFrequency = itemView.findViewById<TextView>(R.id.taskFrequency)


        //to implement onclicklistener for a single record
        val cleaningTaskSingleRecord = itemView.findViewById<LinearLayout>(R.id.cleaningTaskSingleRecord)

        fun bind(data: CleaningTask) {
            cleaningTaskName.text = data.name
            cleaningTaskDescription.text = data.description
            taskFrequency.text = data.frequency


            //Setting onClickListener on dataset so user can click it and it will navigate to Update fragment
            cleaningTaskSingleRecord.setOnClickListener {
                val action = CleaningTaskFragmentDirections.actionCleaningTaskFragmentToUpdateCleaningTaskFragment(data)
                itemView.findNavController().navigate(action)
            }
        }

    }


    //List to contain cleaning task objects
    private var cleaningTasksList = emptyList<CleaningTask>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleaningTaskAdapter.MyViewHolder {

        //Returning cleaningTasks list item view
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cleaning_task_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CleaningTaskAdapter.MyViewHolder, position: Int) {

        //Setting current record
        val currentRecord = cleaningTasksList[position]

        holder.bind(cleaningTasksList[position])
    }

    fun setDataSet(cleaningTask: List<CleaningTask>){
        this.cleaningTasksList = cleaningTask

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return cleaningTasksList.size
    }


}

