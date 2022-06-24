package com.sibs_test.sibs_test_felipe.ui.book_store_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.databinding.FragmentBookStoreListBinding
import com.sibs_test.sibs_test_felipe.extensions.EventObserver
import com.sibs_test.sibs_test_felipe.extensions.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookStoreListFragment : Fragment() {
    private var _binding: FragmentBookStoreListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: BookStoreListViewModel by viewModels()

    private val controller by lazy {
        BookStoreListPagedController(
            viewModel::bookItemClicked
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookStoreListBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.rvBookList.setController(controller)
        binding.rvBookList.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.ivFavorite.setOnClickListener {
            viewModel.favoriteFilterClicked()
        }

        viewModel.navigation.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is BookStoreListContract.ViewInstructions.NavigateToBookDetails -> {
                    val bundle = Bundle()
                    bundle.putParcelable("book", it.book)
                    findNavController().navigate(R.id.action_BookStoreList_to_BookStoreDetail, bundle)
                }
                else -> {}
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.message.orEmpty(), Toast.LENGTH_SHORT).show()
        })

        viewModel.pagedBooks.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
        })

        viewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            controller.favoriteList = it
        })

        viewModel.favoriteState.observe(viewLifecycleOwner, Observer {
            controller.favorite = it

            if (it) {
                binding.ivFavorite.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite))
            } else {
                binding.ivFavorite.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite_border))
            }
        })

        viewModel.networkStage.observe(viewLifecycleOwner, Observer {
            binding.pbLoading.toVisibility = it is Resource.Loading
        })

        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshFavoriteList()
    }
}