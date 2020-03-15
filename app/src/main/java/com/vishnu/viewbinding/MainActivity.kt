package com.vishnu.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.vishnu.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mFragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mFragmentManager = supportFragmentManager

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> changeImageShape()
            R.id.btn2 -> openFragment()
        }
    }

    private fun changeImageShape() {
        val mRadius = binding.roundImageView.radius
        if (mRadius > 0) {
            binding.roundImageView.radius = 0
        } else {
            binding.roundImageView.radius = 100
        }
    }

    private fun openFragment() {
        val ft = mFragmentManager.beginTransaction()
        ft.addToBackStack("fragment")
        ft.add(R.id.root, NewFragment()).commit()
    }

    override fun onBackPressed() {
        if (mFragmentManager.backStackEntryCount > 0)
            mFragmentManager.popBackStackImmediate();
        else super.onBackPressed();
    }
}
