package com.ilya.composition_game.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ilya.composition_game.R
import com.ilya.composition_game.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.setImageDrawable(getDrawable(winner, imageView))
}

private fun getDrawable(winner: Boolean, imageView: ImageView): Drawable? {
    val id: Int = if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.sad
    }
    return AppCompatResources.getDrawable(imageView.context, id)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, requiredScore: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        requiredScore
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, requiredPerc: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        requiredPerc
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getScorePercent(gameResult)
    )
}

private fun getScorePercent(gameResult: GameResult) =
    if (gameResult.countOfQuestions == 0) {
        0
    } else {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    }

@BindingAdapter("enoughAnswersProgress")
fun bindAnswersProgress(textView: TextView, enoughCountAnswers: Boolean) {
    val color = getColorByState(textView.context, enoughCountAnswers)
    textView.setTextColor(color)
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercentRightAnswers(progressBar: ProgressBar, enoughPercent: Boolean) {
    val color = getColorByState(progressBar.context, enoughPercent)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}