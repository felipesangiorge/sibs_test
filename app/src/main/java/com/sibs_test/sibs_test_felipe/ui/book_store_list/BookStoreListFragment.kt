package com.sibs_test.sibs_test_felipe.ui.book_store_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.databinding.FragmentBookStoreListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookStoreListFragment : Fragment() {
    private var _binding: FragmentBookStoreListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: BookStoreListViewModel by viewModels()

    private val controller by lazy {
        BookStoreListPagedController(viewModel::bookItemClicked)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookStoreListBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.rvBookList.setController(controller)
        binding.rvBookList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.message.orEmpty(), Toast.LENGTH_SHORT).show()
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BookStoreListContract.ViewInstructions.NavigateToBookDetails -> {
                    val bundle = Bundle()
                    bundle.putParcelable("book", it.book)

                    findNavController().navigate(R.id.action_BookStoreList_to_BookStoreDetail, bundle)
                }
                else -> {}
            }
        })

        viewModel.pagedBooks.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
        })

        return root
    }
}