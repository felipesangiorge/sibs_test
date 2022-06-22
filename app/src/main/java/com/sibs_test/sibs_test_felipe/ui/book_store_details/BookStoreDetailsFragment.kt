package com.sibs_test.sibs_test_felipe.ui.book_store_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.databinding.FragmentBookDetailBinding
import com.sibs_test.sibs_test_felipe.extensions.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookStoreDetailsFragment : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null

    private val binding get() = _binding!!

    private val viewModel: BookStoreDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        val root = binding.root

        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.message.orEmpty(), Toast.LENGTH_SHORT).show()
        })

        viewModel.book.observe(viewLifecycleOwner, Observer {
            
            Glide.with(binding.ivThumbnail)
                .load(it.volumeInfo.imageLinks.thumbnail?.replace("http", "https"))
                .error(R.drawable.ic_image_placeholder)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.ivThumbnail)

            binding.tvTitle.text = it.volumeInfo.title
            binding.tvPublication.text = it.volumeInfo.publishedDate
            binding.tvDescription.text = it.volumeInfo.description

            binding.ivBuyAvailability.toVisibility = it.saleInfo?.buyLink != null
            it.saleInfo?.buyLink?.let {
                binding.tvBuyLink.text = it
            }
        })

        return root
    }
}