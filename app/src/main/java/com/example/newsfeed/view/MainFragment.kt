package com.example.newsfeed.view

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.R
import com.example.newsfeed.databinding.FragmentMainBinding
import com.example.newsfeed.repository.NewsRepository
import com.example.newsfeed.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment(): Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    private val newsAdapter: NewsListAdapter by lazy {
        NewsListAdapter(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container,false)
        return binding.root

        //return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = activity
        }
        viewModel.initParameters()

        observeViewModel()

        searchButton.setOnClickListener {
            viewModel.getNews(inputSearchWord.text.toString())
        }

        with(newsList){
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = newsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.newsList.observe(requireActivity(), Observer { list ->
            txtSearchWord.text = viewModel.searchWord.value
            list.let {
                newsAdapter.newsList = it
            }
            newsAdapter.notifyDataSetChanged()
        })
    }
}
