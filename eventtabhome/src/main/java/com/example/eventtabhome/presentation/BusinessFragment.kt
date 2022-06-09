package com.example.eventtabhome.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eventlist.presentation.businesslist.BusinessListFragment
import com.example.eventtabhome.R
import com.example.eventtabhome.widgets.SearchBar

class BusinessFragment : Fragment() {

    private lateinit var fragment: BusinessListFragment
    private lateinit var sbLocations: SearchBar
//    private lateinit var sbCategories: SearchBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragment = BusinessListFragment.newInstance (
            onLocationsLoaded = ::onLocationsLoaded,
//            onCategoriesLoaded = ::onCategoriesLoaded
        )
        parentFragmentManager.beginTransaction()
            .replace(R.id.flBusiness, fragment)
            .commit()
        val view = inflater.inflate(R.layout.fragment_business, container, false)
        sbLocations = view.findViewById(R.id.sbLocations)
//        sbCategories = view.findViewById(R.id.sbCategories)

        sbLocations.setOnSelectedOption { location ->
            search(location = location)
        }
//        sbCategories.setOnSelectedOption { category ->
//            search(category = category)
//        }
        return view

    }

    private fun search(location: String? = null, category: String? = null) {
        fragment.searchBusiness(category, location)
    }

//    private fun onCategoriesLoaded(list: List<String>) {
//        sbCategories.setOptions(list)
//    }

    private fun onLocationsLoaded(list: List<String>) {
        sbLocations.setOptions(list)
    }
}