import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigo.licence.licenceQData1

class licenceQAdapter1(val nameList: ArrayList<licenceQData1>, val ctx: Context) :
    RecyclerView.Adapter<licenceQAdapter1.ViewHolder>() {


    private var answer =  Array<String>(20,{""})

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(com.example.bigo.R.layout.recycle_licenceq, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = nameList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val packageModel: licenceQData1 = nameList[position]
        holder.Q.setText(packageModel.getPackageName())
        var id = (position + 1) * 100
        for (price in packageModel.getPriceList()!!) {
            val rb = RadioButton(ctx)
            rb.id = id++
            rb.text = price
            holder.ex.addView(rb)
        }

        holder.ex.setOnCheckedChangeListener(null)
        holder.ex.setOnCheckedChangeListener { radioGroup, i ->
            when(i - ((position+1)*100)){
                0 -> answer[position] = "1"
                1 -> answer[position] = "2"
                2 -> answer[position] = "3"
                3 -> answer[position] = "4"
            }

        }
    }

    fun getAnswer() : Array<String>{
        return answer
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var Q: TextView = view.findViewById(com.example.bigo.R.id.Question)
        var ex: RadioGroup = view.findViewById(com.example.bigo.R.id.example)

        init {
        }
    }

}


