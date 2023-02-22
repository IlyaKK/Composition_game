package com.ilya.composition_game.presentation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ilya.composition_game.R
import com.ilya.composition_game.databinding.FragmentGameFinishedBinding
import com.ilya.composition_game.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private lateinit var initGameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnBackPressedListener()
        setButtonRetryListener()
        setResultGame()
    }

    private fun setResultGame() {
        with(binding) {
            emojiResult.setImageDrawable(getDrawByState())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                initGameResult.gameSettings.minCountOfRightValue
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                initGameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                initGameResult.gameSettings.minPercentOfRightAnswer
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                initGameResult.getPercentOfRightAnswers()
            )
        }
    }

    private fun GameResult.getPercentOfRightAnswers(): Int {
        return if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getDrawByState(): Drawable? {
        val id: Int = if (initGameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.sad
        }
        return getDrawable(requireContext(), id)
    }

    private fun setButtonRetryListener() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun setOnBackPressedListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            },
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            initGameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {
        private const val KEY_GAME_RESULT = "game_result"
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}