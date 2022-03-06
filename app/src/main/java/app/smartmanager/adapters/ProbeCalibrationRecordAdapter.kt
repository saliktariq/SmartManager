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

class ProbeCalibrationRecordAdapter :
    RecyclerView.Adapter<ProbeCalibrationRecordAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val probeNamePCR = itemView.findViewById<TextView>(R.id.probeNamePCR)
        val probeTemperature = itemView.findViewById<TextView>(R.id.probeTemperature)
        val probeCalibrationMethod = itemView.findViewById<TextView>(R.id.probeCalibrationMethod)
        val probeCalibrationDate = itemView.findViewById<TextView>(R.id.probeCalibrationDate)

        //to implement onclicklistener for a single record
        val probeCalibrationRecordSingleRecord =
            itemView.findViewById<LinearLayout>(R.id.probeCalibrationRecordSingleRecord)

        fun bind(data: ProbeCalibrationRecord) {
            probeNamePCR.text = data.probe
            probeTemperature.text = data.temperature.toString()
            probeCalibrationMethod.text = data.calibrationMethod
            probeCalibrationDate.text = data.date.toString()

            //Setting onClickListener on dataset so user can click it and it will navigate to Update fragment
            probeCalibrationRecordSingleRecord.setOnClickListener {
                val action =
                    ProbeCalibrationRecordFragmentDirections.actionProbeCalibrationRecordFragment2ToUpdateProbeCalibrationRecordFragment(
                        data
                    )
                itemView.findNavController().navigate(action)
            }
        }

    }


    //List to contain ProbeCalibrationRecord objects
    private var probeCalibrationRecordList = emptyList<ProbeCalibrationRecord>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProbeCalibrationRecordAdapter.MyViewHolder {

        //Returning ProbeCalibrationRecord list item view
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.probe_calibration_record_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ProbeCalibrationRecordAdapter.MyViewHolder,
        position: Int
    ) {

        //Setting current record
        val currentRecord = probeCalibrationRecordList[position]

        holder.bind(probeCalibrationRecordList[position])
    }

    fun setDataSet(probeCalibrationRecord: List<ProbeCalibrationRecord>) {
        this.probeCalibrationRecordList = probeCalibrationRecord

        //Notify UI about dataset change
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return probeCalibrationRecordList.size
    }

}



