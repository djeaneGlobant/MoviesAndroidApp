package com.example.eventlist.presentation.businesslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.viewmodel.ViewModelFactory
import com.example.eventlist.R
import com.example.eventlist.domain.model.Business
import com.example.eventlist.presentation.util.UIState
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BusinessListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val model: BusinessListViewModel by viewModels { viewModelFactory }

    private lateinit var rvFlBusiness: RecyclerView
    private lateinit var pbLoading: ProgressBar
    private lateinit var tvEmptyResults: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_business_list, container, false)
        pbLoading = view.findViewById(R.id.pbLoading)
        tvEmptyResults = view.findViewById(R.id.tvEmptyResults)
        rvFlBusiness = view.findViewById(R.id.rvFlBusiness)
        rvFlBusiness.layoutManager = LinearLayoutManager(context)
        onLocationsLoaded?.run { model.setStateEvent(BusinessListStateEvent.LoadLocations(this)) }
        onCategoriesLoaded?.run { model.setStateEvent(BusinessListStateEvent.LoadCategories(this)) }
        searchBusiness()
        model.business.observe(viewLifecycleOwner) {
            val adapter = BusinessListAdapter(
                it,
                onClickFavorite = { id, isFavorite ->
                    model.setStateEvent(BusinessListStateEvent.ToggleFavorite(id, isFavorite))
                    onClickFavorite?.invoke(id, isFavorite)
                }, onClickBusiness = { business ->
                    onClickBusiness?.invoke(business)
                }
            )
            rvFlBusiness.adapter = adapter
        }
        model.uiState.observe(viewLifecycleOwner, ::manageUiState)

        return view
    }

    private fun manageUiState(uiState: UIState) {
        pbLoading.visibility = View.GONE
        tvEmptyResults.visibility = View.GONE
        rvFlBusiness.visibility = View.GONE
        when (uiState) {
            is UIState.Success -> {
                if (uiState.isEmpty) {
                    tvEmptyResults.visibility = View.VISIBLE
                } else {
                    rvFlBusiness.visibility = View.VISIBLE
                }
            }
            is UIState.Loading -> {
                pbLoading.visibility = View.VISIBLE
            }
            is UIState.Error -> {
                tvEmptyResults.visibility = View.VISIBLE
                requireView().resources?.getString(R.string.common_ui_error)?.apply {
                    Snackbar.make(requireView().rootView, this, BaseTransientBottomBar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    fun searchBusiness(term: String? = null, location: String? = null) {
        val currentTerm = term ?: "food"
        val currentLocation = location ?: "New York City"
        model.setStateEvent(BusinessListStateEvent.LoadBusiness(currentTerm, currentLocation))
    }

    companion object {
        private var onLocationsLoaded: ((List<String>) -> Unit)? = null
        private var onCategoriesLoaded: ((List<String>) -> Unit)? = null
        private var onClickFavorite: ((String, Boolean) -> Unit)? = null
        private var onClickBusiness: ((Business) -> Unit)? = null

        @JvmStatic
        fun newInstance(
            onClickFavorite: ((String, Boolean) -> Unit)? = null,
            onClickBusiness: ((Business) -> Unit)? = null,
            onLocationsLoaded: ((List<String>) -> Unit)? = null,
            onCategoriesLoaded: ((List<String>) -> Unit)? = null,
        ) = BusinessListFragment().apply {
            Companion.onCategoriesLoaded = onCategoriesLoaded
            Companion.onLocationsLoaded = onLocationsLoaded
            Companion.onClickBusiness = onClickBusiness
            Companion.onClickFavorite = onClickFavorite
        }
    }

}