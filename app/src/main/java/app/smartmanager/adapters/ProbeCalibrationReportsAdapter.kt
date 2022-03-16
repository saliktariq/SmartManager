

package app.smartmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import app.smartmanager.R
import app.smartmanager.datalayer.entity.ProbeCalibrationRecord
import app.smartmanager.datalayer.entity.Supplier
import app.smartmanager.ui.foodsafetymanagement.proberecord.ProbeCalibrationRecordFragmentDirections
import app.smartmanager.ui.setup.SupplierFragmentDirections

class ProbeCalibrationReportsAdapter :
    RecyclerView.Adapter<ProbeCalibrationReportsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val probeNamePCR = itemView.findViewById<TextView>(R.id.probeNamePCR)
        val probeTemperature = itemView.findViewById<TextView>(R.id.probeTemperature)
        val probeCalibrationMethod = itemView.findViewById<TextView>(R.id.probeCalibrationMethod)
        val probeCalibrationDate = itemView.findViewById<TextView>(R.id.probeCalibrationDate)



        fun bind(data: ProbeCalibrationRecord) {
            probeNamePCR.text = data.probe
            probeTemperature.text = data.temperature.toString()
            probeCalibrationMethod.text = data.calibrationMethod
            probeCalibrationDate.text = data.date.toString()


        }

    }


    //List to contain ProbeCalibrationRecord objects
    private var probeCalibrationRecordList = emptyList<ProbeCalibrationRecord>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProbeCalibrationReportsAdapter.MyViewHolder {

        //Returning ProbeCalibrationRecord list item view
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.probe_calibration_report_list_item, parent, false)
        )
    }


    fun setDataSet(probeCalibrationRecord: List<ProbeCalibrationRecord>) {
        this.probeCalibrationRecordList = probeCalibrationRecord

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return probeCalibrationRecordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Setting current record
        val currentRecord = probeCalibrationRecordList[position]

        holder.bind(probeCalibrationRecordList[position])    }

}



