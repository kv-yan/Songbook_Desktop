package data.lambda.search

import domain.model.Song

fun searchSongByContains(allSongList: List<Song>, query: String): List<Song> {
    var filteredList = mutableListOf<Song>()
    if (query.isNotEmpty()) {
        allSongList.forEach {
            if (it.words.contains(query) || it.words.startsWith(query) || it.title.contains(query) || it.title.startsWith(
                    query
                )
            ) filteredList.add(it)
        }
    } else {
        filteredList = allSongList.toMutableList()
    }
    return filteredList
}
