package com.example.testingproject.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testingproject.adapter.UserWithCityAdapter
import com.example.testingproject.databinding.FragmentSecondBinding
import com.example.testingproject.repository.UserRepository
import com.example.testingproject.ui.UserViewModel
import com.example.testingproject.ui.UserViewModelFactory
import com.example.testingproject.utils.Resource

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var viewModel: UserViewModel

    private lateinit var userAdapter: UserWithCityAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        initializeViewModel()
        return binding.root

    }

    private fun initializeViewModel() {
        val repository = UserRepository()
        val userViewModelFactory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getUserWithCityData()
        apiObserver()
    }

    private fun apiObserver() {
        viewModel.userCityData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    hideProgressBar()
                    result.data?.let {
                        userAdapter.differ.submitList(result.data)
                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        userAdapter = UserWithCityAdapter()
        binding.recyclerView.apply {
            adapter = userAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}