package com.ilya.composition_game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        val args = Bundle().apply {
            putParcelable(GameFragment.KEY_LEVEL, level)
        }
        findNavController().navigate(R.id.action_chooseLevelFragment_to_gameFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}