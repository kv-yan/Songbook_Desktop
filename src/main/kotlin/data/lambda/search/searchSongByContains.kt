package data.lambda.search

import domain.model.Song
import java.text.Normalizer
import java.util.*

fun searchSongByContains(allSongList: List<Song>, query: String): List<Song> {
    if (query.isBlank()) return allSongList

    val normalizedSearchText = normalizeText(query)
    val regexPattern = ".*${normalizedSearchText}.*".toRegex(RegexOption.IGNORE_CASE)

    return allSongList.filter { song ->
        val normalizedTitle = normalizeText(song.title)
        val normalizedWords = normalizeText(song.words)
        regexPattern.matches(normalizedTitle) || regexPattern.matches(normalizedWords)
    }
}

fun normalizeText(text: String): String {
    return Normalizer.normalize(text, Normalizer.Form.NFD).replace("[\\p{M}]".toRegex(), "")
        .replace("[&\\/՝#,+()$~%.'\":*?<>{}br0-9\\s]+".toRegex(), "")
        .lowercase(Locale.getDefault())
}

