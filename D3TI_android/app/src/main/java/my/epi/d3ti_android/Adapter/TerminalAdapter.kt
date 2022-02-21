package my.epi.d3ti_android.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.epi.d3ti_android.R

class TerminalAdapter(
    private val itemList: MutableList<String> = arrayListOf()
) : RecyclerView.Adapter<TerminalAdapter.ViewHolder>() {

    // Post Item
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var content = view.findViewById<TextView>(R.id.content)
    }

    override fun getItemCount(): Int = itemList.size

    /**
     * Initialize a Subreddit item
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = itemList[position]

        holder.content.text = current
    }

    fun append(item: String) {
        val list: List<String> = listOf(item)
        this.append(list)
    }

    fun append(list: List<String>) {
        val oldSize = itemList.size
        itemList.addAll(list)
        this.notifyItemRangeInserted(oldSize, list.size)
    }

    fun clear() {
        val size = itemList.size
        itemList.clear()
        this.notifyItemRangeRemoved(0, size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerminalAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.terminal_item, parent, false)
        return ViewHolder(view)
    }
}