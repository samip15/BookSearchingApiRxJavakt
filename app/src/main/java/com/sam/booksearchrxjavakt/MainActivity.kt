package com.sam.booksearchrxjavakt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sam.booksearchrxjavakt.adapter.BookListAdapter
import com.sam.booksearchrxjavakt.network.BookListModel
import com.sam.booksearchrxjavakt.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var bookListAdapter: BookListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSearchBox()
        initRecyclerView()
    }

    fun initSearchBox(){
        val editSearch = findViewById<EditText>(R.id.inputBookName)
        editSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadApiData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        } )
    }

    private fun loadApiData(input: String) {
      viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
      viewModel.getBookListObserver().observe(this, Observer<BookListModel> {
          if (it != null) {
                // update adapter
              bookListAdapter.bookListData = it.items
              bookListAdapter.notifyDataSetChanged()

          } else {
              Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
          }
      })
        viewModel.makeApiCall(input)
    }

    private fun initRecyclerView() {
        val recyclerViewItem = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewItem.apply {
            val layoutManagerVal = LinearLayoutManager(this@MainActivity)
            recyclerViewItem.layoutManager = layoutManagerVal
            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter

        }
    }
}