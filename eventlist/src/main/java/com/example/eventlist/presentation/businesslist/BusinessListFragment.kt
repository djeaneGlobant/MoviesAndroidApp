package com.example.eventlist.presentation.businesslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.viewmodel.ViewModelFactory
import com.example.eventlist.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BusinessListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val model: BusinessListViewModel by viewModels { viewModelFactory }

    private lateinit var rvFlBusiness: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_list, container, false)
        rvFlBusiness = view.findViewById(R.id.rvFlBusiness)
        rvFlBusiness.layoutManager = LinearLayoutManager(context)

        model.loadBusiness()
        model.business.observe(viewLifecycleOwner) {
            val adapter = BusinessListAdapter(it)
            rvFlBusiness.adapter = adapter
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BusinessListFragment().apply {
            }
    }
}