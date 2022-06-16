package com.example.eventtabhome.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.detail.business.presentation.BusinessDetailActivity
import com.example.eventlist.domain.model.Business
import com.example.eventlist.presentation.businesslist.BusinessListFragment
import com.example.eventtabhome.R
import com.example.eventtabhome.widgets.SearchBar
import com.google.gson.Gson

class BusinessFragment : Fragment() {

    private lateinit var fragment: BusinessListFragment
    private lateinit var sbLocations: SearchBar
//    private lateinit var sbCategories: SearchBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_business, container, false)
        sbLocations = view.findViewById(R.id.sbLocations)
        sbLocations.setOnSelectedOption { location ->
            search(location = location)
        }
        fragment = BusinessListFragment.newInstance(
            onLocationsLoaded = ::onLocationsLoaded,
            onClickBusiness = ::onClickBusiness,
//            onCategoriesLoaded = ::onCategoriesLoaded
        )
        BusinessDetailActivity.onFavoriteSelected  = { id, isFavorite ->
            fragment.toggleFavorite(id, isFavorite)
        }

//        sbCategories = view.findViewById(R.id.sbCategories)

//        sbCategories.setOnSelectedOption { category ->
//            search(category = category)
//        }
        return view

    }

    override fun onResume() {
        super.onResume()
        parentFragmentManager.beginTransaction()
            .replace(R.id.flBusiness, fragment)
            .commit()
    }

    private fun onClickBusiness(business: Business) {
        val businessString = Gson().toJson(business)
        val intent = Intent(requireActivity(), BusinessDetailActivity::class.java)
        intent.putExtra(BusinessDetailActivity.ARG_NAME, businessString)
        requireActivity().startActivity(intent)
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