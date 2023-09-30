package com.example.etrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView


class Note_Activity : AppCompatActivity() {
    private lateinit var taskEditText: EditText
    private lateinit var addButton: Button
    private lateinit var taskListView: ListView
    private lateinit var taskAdapter: ArrayAdapter<String>

    private val taskList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        taskEditText = findViewById(R.id.editTask)
        addButton = findViewById(R.id.btnAdd)
        taskListView = findViewById(R.id.taskList)

        taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        taskListView.adapter = taskAdapter

        addButton.setOnClickListener {
            val task = taskEditText.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                taskAdapter.notifyDataSetChanged()
                taskEditText.text.clear()
            }
        }

        taskListView.setOnItemClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            taskAdapter.notifyDataSetChanged()
        }
    }
}