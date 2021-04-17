package com.fd.kso.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fd.kso.MyApplication
import com.fd.kso.R
import com.fd.kso.data.model.MyItem
import com.fd.kso.databinding.ActivityMainBinding
import com.fd.kso.databinding.FragmentMainBinding
import com.fd.kso.ui.adapters.MItemAdapter
import com.fd.kso.utils.Status
import com.fd.kso.utils.Utils
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewmodel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = MItemAdapter(arrayListOf())
        adapter.onItemClick = { item ->
            val bundle = bundleOf(Utils.ITEM_BUNDLE_ARG to item)
            Navigation.findNavController(binding.root)
                    .navigate(R.id.action_listFragment_to_detailInfoFragment,bundle)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.allItems.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        displayProgressBar(false)
                        resource.data?.let { bookObj -> retrieveList(bookObj) }
                    }
                    Status.ERROR -> {
                        displayProgressBar(false)
                        it.message?.let { message -> displayError(message) }
                    }
                    Status.LOADING -> displayProgressBar(true)
                }
            }
        })
    }

    private fun retrieveList(items: List<MyItem>) {
        adapter.apply {
            addItems(items)
            notifyDataSetChanged()
        }
    }

    private fun displayError(errorMessage: String) {
        Toast.makeText(activity?.applicationContext, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun displayProgressBar(visible: Boolean) {
        if (visible) binding.listProgressBar.visibility = View.VISIBLE
        else binding.listProgressBar.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}