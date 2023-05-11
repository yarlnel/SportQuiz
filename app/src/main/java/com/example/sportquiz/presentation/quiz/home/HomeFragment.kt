package com.example.sportquiz.presentation.quiz.home

import android.os.Bundle
import android.view.View
import com.example.sportquiz.R
import com.example.sportquiz.databinding.FragmentHomeBinding
import com.example.sportquiz.databinding.ViewHolderQuizPageBinding
import com.example.sportquiz.presentation.common.fragment.BaseFragment
import com.example.sportquiz.presentation.navigation.graph.Screens
import com.example.sportquiz.presentation.utils.getPageImageRes
import com.example.sportquiz.presentation.utils.onclick
import com.github.terrakok.cicerone.Router
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    @Inject
    lateinit var router: Router

    private fun getPagesDelegate() = adapterDelegateViewBinding<
            Int,
            Int,
            ViewHolderQuizPageBinding
    >(viewBinding = { layoutInflater, parent ->
        ViewHolderQuizPageBinding.inflate(layoutInflater, parent, false)
    }) {
        binding.root onclick {
            router.navigateTo(Screens.QuizPage(item))
        }
        bind {
            binding.renderItem(item)
        }
    }

    private fun ViewHolderQuizPageBinding.renderItem(pageNumber: Int) {
        txtSection.text = getString(R.string.section_template)
        val imageRes = getPageImageRes(pageNumber)
        image.setImageResource(pageNumber)
    }

    private val adapter = ListDelegationAdapter(getPagesDelegate())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() = with(binding) {
        pagesRecycler.adapter = adapter
        adapter.items = (1..11).toList()
    }
}