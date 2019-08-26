package com.mucahitkambur.tdksozluk.ui.about


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mucahitkambur.tdksozluk.BuildConfig

import com.mucahitkambur.tdksozluk.R
import com.mucahitkambur.tdksozluk.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var dataBinding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater,
            R.layout.fragment_about,
            container,
            false
        ).apply {
            lifecycleOwner = this@AboutFragment
            version = BuildConfig.VERSION_NAME
            url = BuildConfig.GITHUB_URL
            mail = BuildConfig.MAIL_ADDRESS
        }

        return dataBinding.root
    }
}
