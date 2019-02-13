package com.example.androidfirstapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var listItemRecyclerView: RecyclerView
    lateinit var addTaskButton: FloatingActionButton

    //region Override Func
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name

        listItemRecyclerView = findViewById<RecyclerView>(R.id.list_item_recyclerView)
        listItemRecyclerView.layoutManager = LinearLayoutManager(this)
        listItemRecyclerView.adapter = ListItemRecyclerViewAdapter(list)

        addTaskButton = findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener{
            showCreateTaskDialog()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
    //endregion

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.task_to_add)
        builder.setView(taskEditText)
        builder.setPositiveButton(R.string.add_task) {dialog, _ ->
            val task = taskEditText.text.toString()
            list.tasks.add(task)
            val recyclerAdapter = listItemRecyclerView.adapter as ListItemRecyclerViewAdapter
            recyclerAdapter.notifyItemInserted(list.tasks.size)
            dialog.dismiss()
        }
        builder.create().show()
    }


}
