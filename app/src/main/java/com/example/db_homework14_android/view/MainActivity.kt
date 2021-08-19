package com.example.db_homework14_android.view

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.db_homework14_android.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var videoList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<LinearLayoutCompat>(R.id.mainLayout)
        val viewModel = VideoModelView(application)
        val searchYear = findViewById<EditText>(R.id.year)
        searchYear.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val yearString = searchYear.text.toString()
                if (yearString.matches(("^([1][9]\\d\\d|2[0-9][0-9][0-9])\$").toRegex())) {
                    viewModel.getVideoList(yearString)
                } else {
                    Snackbar.make(
                        mainLayout,
                        "Wrong year",
                        Snackbar.LENGTH_LONG
                    ).show();
                }
            }
            true
        }

        videoList = findViewById(R.id.videoList)
        val layoutManager = LinearLayoutManager(this)
        videoList.layoutManager = layoutManager
        videoList.adapter = VideoListAdapter(this)
        viewModel.listItem.observe(this) {
            val adapter = videoList.adapter as VideoListAdapter
            adapter.results = it
            adapter.notifyDataSetChanged()
        }
    }
}