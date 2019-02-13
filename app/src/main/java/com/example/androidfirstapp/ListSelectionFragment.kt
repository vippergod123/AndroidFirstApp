package com.example.androidfirstapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListSelectionFragment : Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {


    private var listener: OnFragmentInteractionListener? = null
    lateinit var listRecyclerView: RecyclerView
    lateinit var listDataManager: ListDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_selection, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let{
            val lists = listDataManager.readList()
            listRecyclerView = it.findViewById<RecyclerView>(R.id.listRecyclerView)
            listRecyclerView.layoutManager = LinearLayoutManager(activity)
            listRecyclerView.adapter =  ListSelectionRecyclerViewAdapter(lists,this)
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onListItemClicked(list)
    }

    interface OnFragmentInteractionListener {
        fun onListItemClicked(list: TaskList)
    }

    fun addList (list:TaskList) {
        listDataManager.saveList(list)
        val recyclerViewAdapter = listRecyclerView.adapter as ListSelectionRecyclerViewAdapter
        recyclerViewAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateList()
    }

    private fun updateList() {
        val lists = listDataManager.readList()
        listRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists,this)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String): ListSelectionFragment
        {
            return ListSelectionFragment()
        }
    }
}
