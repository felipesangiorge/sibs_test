package com.sibs_test.sibs_test_felipe.ui.book_store_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.databinding.FragmentBookDetailBinding
import com.sibs_test.sibs_test_felipe.extensions.EventObserver
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

        binding.topAppBar.setNavigationOnClickListener {
            viewModel.onBackClicked()
        }

        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.message.orEmpty(), Toast.LENGTH_SHORT).show()
        })

        viewModel.navigation.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                BookStoreDetailsContract.ViewInstructions.NavigateBack -> findNavController().popBackStack()
            }
        })

        viewModel.book.observe(viewLifecycleOwner, Observer {

            Glide.with(binding.ivThumbnail)
                .load(it.volumeInfo.imageLinks?.thumbnail?.replace("http", "https"))
                .error(R.drawable.ic_image_placeholder)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(binding.ivThumbnail)

            binding.tvTitle.text = it.volumeInfo.title
            binding.tvPublication.text = it.volumeInfo.publishedDate
            binding.tvDescription.text = it.volumeInfo.description

            var author = requireContext().getString(R.string.book_store_details_fragment)
            it.volumeInfo.authors?.forEach {
                author += "$it \n"
            }

            if (!it.volumeInfo.authors.isNullOrEmpty())
                binding.tvAuthor.text = author

            binding.ivBuyAvailability.toVisibility = it.saleInfo?.buyLink != null
            it.saleInfo?.buyLink?.let {
                binding.tvBuyLink.text = it
            }

            if (it.favorite) {
                Glide.with(binding.fbFavorite)
                    .load(R.drawable.ic_favorite)
                    .into(binding.fbFavorite)

                binding.fbFavorite.setOnClickListener { _ ->
                    viewModel.favoriteClicked(it, false)
                }
            } else {
                Glide.with(binding.fbFavorite)
                    .load(R.drawable.ic_favorite_border)
                    .into(binding.fbFavorite)

                binding.fbFavorite.setOnClickListener { _ ->
                    viewModel.favoriteClicked(it, true)
                }
            }
        })

        return root
    }
}