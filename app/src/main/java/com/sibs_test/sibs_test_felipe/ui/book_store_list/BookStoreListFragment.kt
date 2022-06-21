package com.sibs_test.sibs_test_felipe.ui.book_store_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sibs_test.sibs_test_felipe.databinding.FragmentBookStoreListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookStoreListFragment : Fragment() {
    private var _binding: FragmentBookStoreListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: BookStoreListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookStoreListBinding.inflate(inflater, container, false)
        val root = binding.root


        return root
    }
}