package data.lambda.search

import domain.model.Song

fun searchBySongWords(allSongList: List<Song>, query: String): List<Song> {
    var filteredList = mutableListOf<Song>()
    if (query.isNotEmpty()) {
        allSongList.forEach {
            if (it.words.lowercase().contains(query.lowercase())) filteredList.add(it)
        }
    } else {
        filteredList = allSongList.toMutableList()
    }
    return filteredList
}
