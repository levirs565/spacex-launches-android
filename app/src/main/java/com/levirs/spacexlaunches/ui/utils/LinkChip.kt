package com.levirs.spacexlaunches.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.util.Patterns
import androidx.core.util.PatternsCompat
import com.google.android.material.chip.Chip

class LinkChip @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Chip(context, attrs, defStyleAttr) {
    private var link: String? = null

    init {
        setOnClickListener {
            if (link == null) return@setOnClickListener

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            context.startActivity(intent)
        }
    }

    fun setLink(link: String) {
        if (!PatternsCompat.WEB_URL.matcher(link).matches())
            throw IllegalArgumentException("$link is not web url")

        this.link = link
    }
}