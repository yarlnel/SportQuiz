package servolne.cima.presentation.quiz.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isGone
import servolne.cima.R
import servolne.cima.databinding.FragmentHomeBinding
import servolne.cima.databinding.ViewHolderQuizPageBinding
import servolne.cima.presentation.common.fragment.BaseFragment
import servolne.cima.presentation.utils.getPageImageRes
import servolne.cima.presentation.utils.onclick
import com.github.terrakok.cicerone.Router
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import servolne.cima.presentation.common.backpress.BackPressedStrategyOwner
import servolne.cima.presentation.navigation.graph.Screens
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), BackPressedStrategyOwner {

    @Inject
    lateinit var router: Router

    private data class Page(
        val number: Int,
        val drawable: Drawable?
    )

    private fun getPagesDelegate() = adapterDelegateViewBinding<
            Page,
            Page,
            ViewHolderQuizPageBinding
    >(viewBinding = { layoutInflater, parent ->
        ViewHolderQuizPageBinding.inflate(layoutInflater, parent, false)
    }) {
        binding.root onclick {
            router.navigateTo(Screens.QuizPage(item.number))
        }
        bind {
            binding.renderItem(item)
        }
    }

    private fun ViewHolderQuizPageBinding.renderItem(page: Page) {
        txtSection.text = getString(R.string.section_template, page.number)
        image.setImageDrawable(page.drawable)
    }

    private val adapter = ListDelegationAdapter(getPagesDelegate())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() = with(binding) {
        pagesRecycler.adapter = adapter
        adapter.items = (1..11).map { pageNumber ->
            val imageRes = getPageImageRes(pageNumber)
            val drawable = AppCompatResources.getDrawable(requireContext(), imageRes)
            return@map Page(
                number = pageNumber,
                drawable = drawable
            )
        }
        hideLoadingIndicator()
    }

    private fun hideLoadingIndicator() {
        binding.loadingIndicator.isGone = true
    }

    override fun handleBackPress() {
        requireActivity().finish()
    }
}
