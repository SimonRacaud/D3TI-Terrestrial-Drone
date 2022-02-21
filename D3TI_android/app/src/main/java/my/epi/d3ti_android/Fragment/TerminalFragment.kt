package my.epi.d3ti_android.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import my.epi.d3ti_android.Adapter.TerminalAdapter
import my.epi.d3ti_android.R

class TerminalFragment : Fragment() {
    private val itemList: MutableList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_terminal, container, false)
        // Inflate the layout for this fragment
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = TerminalAdapter()

        val clearButton = view.findViewById<Button>(R.id.clear_button)
        clearButton.setOnClickListener {
            itemList.clear()
            this.pushElement("Cleared !")
        }
        return view
    }

    fun pushElement(text: String) {
        if (view != null) {
            val recyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view)

            itemList.add("> $text")
            recyclerView.adapter = TerminalAdapter(itemList)
            recyclerView.scrollToPosition(itemList.size - 1)
        }
    }
}