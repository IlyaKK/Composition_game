package com.ilya.composition_game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilya.composition_game.R
import com.ilya.composition_game.databinding.FragmentChooseLevelBinding
import com.ilya.composition_game.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLevelButtonsClick()
    }

    private fun initLevelButtonsClick() {
        with(binding) {
            levelTestButton.setOnClickListener {
                lunchGameFragment(Level.TEST)
            }
            levelEasyButton.setOnClickListener {
                lunchGameFragment(Level.EASY)
            }
            levelNormalButton.setOnClickListener {
                lunchGameFragment(Level.NORMAL)
            }
            levelHardButton.setOnClickListener {
                lunchGameFragment(Level.HARD)
            }
        }
    }

    private fun lunchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "choose_level_fragment"
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}