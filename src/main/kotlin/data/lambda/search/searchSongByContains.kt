package data.lambda.search

import domain.model.Song
import java.text.Normalizer
import java.util.*

fun searchSongByContains(allSongList: List<Song>, query: String): List<Song> {
    if (query.isBlank()) return allSongList.sortedBy { armenianSortKey(it.title) }

    val normalizedSearchText = normalizeText(query)
    val regexPattern = ".*${normalizedSearchText}.*".toRegex(RegexOption.IGNORE_CASE)

    return allSongList
        .filter { song ->
            val normalizedTitle = normalizeText(song.title)
            val normalizedWords = normalizeText(song.words)
            regexPattern.matches(normalizedTitle) || regexPattern.matches(normalizedWords)
        }
        .sortedBy { armenianSortKey(it.title) }
}

fun armenianSortKey(text: String): String {
    val armenianAlphabet = "ԱԲԳԴԵԶԷԸԹԺԻԼԽԾԿՀՁՂՃՄՅՆՇՈՉՊՋՌՍՎՏՐՑՈՒՓՔԵՎՕՖ"
    val indexMap = armenianAlphabet.withIndex().associate { it.value to it.index }

    return text.map { indexMap[it] ?: Int.MAX_VALUE }.joinToString("-")
}


fun normalizeText(text: String): String {
    return Normalizer.normalize(text, Normalizer.Form.NFD).replace("[\\p{M}]".toRegex(), "")
        .replace("[&\\/՝#,+()$~%.'\":*?<>{}br0-9\\s]+".toRegex(), "")
        .lowercase(Locale.getDefault())
}

