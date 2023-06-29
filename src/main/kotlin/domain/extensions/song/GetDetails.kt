package domain.extensions.song

import domain.model.Song

fun Song.getWordsFirst2Lines(): String {
    val lines = this.words.split("\n")
    val firstTwoLines = lines.take(2)
    return firstTwoLines.joinToString(" ")
}