package com.example.androidfirstapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.FrameLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListSelectionFragment.OnFragmentInteractionListener {

    private var listSelectionFragment: ListSelectionFragment = ListSelectionFragment()
    private var fragmentcontainer: FrameLayout? = null
    companion object
    {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }

    private fun showCreateDialog()
    {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)
        builder.setCancelable(true)
        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->

            val list = TaskList(listTitleEditText.text.toString())
            listSelectionFragment.addList(list)
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun showListDetail ( list : TaskList)
    {
        val listDetailIntent = Intent(this, ListDetailActivity:: class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
    }

    //region Override Func
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            showCreateDialog()
        }

        fragmentcontainer = findViewById(R.id.fragment_container)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,listSelectionFragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListItemClicked(list: TaskList) {
        showListDetail(list)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LIST_DETAIL_REQUEST_CODE)
        {
            data?.let{
                listSelectionFragment.saveList(data.getParcelableExtra(INTENT_LIST_KEY))

            }
        }
    }


    //endregion

}
