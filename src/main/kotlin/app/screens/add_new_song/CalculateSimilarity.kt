package app.screens.add_new_song

import domain.model.Song
import java.text.Normalizer
import java.util.*
import kotlin.math.min

fun calculateSimilarity(text1: String, text2: String): Double {
    val normalizedText1 = normalizeText(text1)
    val normalizedText2 = normalizeText(text2)

    val maxLength = min(normalizedText1.length, normalizedText2.length)
    if (maxLength == 0) return 0.0

    val commonChars = normalizedText1.commonPrefixWith(normalizedText2).length
    return (commonChars.toDouble() / maxLength) * 100
}

fun findMatchingSongs(newSong: Song, existingSongs: List<Song>, threshold: Double = 40.0): List<Song> {
    return existingSongs.filter { song ->
        val titleMatch = calculateSimilarity(newSong.title, song.title) >= threshold
        val wordsMatch = calculateSimilarity(newSong.words, song.words) >= threshold
        titleMatch || wordsMatch
    }
}

private fun normalizeText(text: String): String {
    return Normalizer.normalize(text, Normalizer.Form.NFD)
        .replace("[\\p{M}]".toRegex(), "")
        .replace("[&\\/՝#,+()$~%.'\":*?<>{}br0-9\\s]+".toRegex(), "")
        .lowercase(Locale.getDefault())
}
