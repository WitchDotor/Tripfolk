package com.tripfolk.ui_kit.util

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

/**
 * Generate a lorem ipsum text of predefined length.
 *
 * Use only for preview purposes.
 *
 * @param words count of words, 8 by default (one full sentence).
 * @return lorem ipsum text.
 */
fun loremIpsum(words: Int = 8): String = LoremIpsum(words).values.joinToString(" ")