package com.vishnu.viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.vishnu.viewbinding.databinding.FragmentNewBinding


class NewFragment : Fragment() {
    private lateinit var binding: FragmentNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonChangeColor.setOnClickListener {
            val color = (Math.random() * 16777215).toInt() or (0xFF shl 24)
            binding.textView.setTextColor(color)
        }
    }

}
