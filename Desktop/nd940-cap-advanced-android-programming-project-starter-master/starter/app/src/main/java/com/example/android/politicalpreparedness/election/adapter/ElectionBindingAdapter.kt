package com.example.android.politicalpreparedness.election.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.android.politicalpreparedness.R

@BindingAdapter("voterFollowOrUnfollowLabel")
fun bindButtonText(textView: TextView, saved: Boolean?){
    if (saved != null) {
        val text =
            textView.context.getString(if (saved) R.string.voter_remove_from_saved else R.string.voter_add_to_saved)
         textView.text = text
        textView.contentDescription = text
    }else{
        textView.visibility = View.GONE

    }
}
@BindingAdapter("showProgress")
fun bindProgressView(view: ProgressBar, loadingData: MutableLiveData<Boolean>) {
    loadingData.value?.let {
        if (it) {
            view.fadeIn()
        } else {
            view.fadeOut()
        }
    }
}

fun View.fadeIn() {
    this.visibility = View.VISIBLE
    this.alpha = 0f
    this.animate().alpha(1f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            this@fadeIn.alpha = 1f
        }
    })
}

//animate changing the view visibility
fun View.fadeOut() {
    this.animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            this@fadeOut.alpha = 1f
            this@fadeOut.visibility = View.GONE
        }
    })
}


