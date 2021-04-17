package com.fd.kso.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fd.kso.MyApplication
import com.fd.kso.data.model.MyItem
import com.fd.kso.databinding.FragmentDetailBinding
import com.fd.kso.utils.Utils

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    var myItem : MyItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(activity?.application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        myItem = arguments?.getParcelable<MyItem>(Utils.ITEM_BUNDLE_ARG)

        return view
    }
}