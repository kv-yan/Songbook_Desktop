package domain.extensions.template

import domain.model.SongTemplate

fun SongTemplate.getDetails(): String {
    val details = ""
    this.glorifyingSong.forEach { song ->
        details.plus("song.title\n")
    }
    details.plus("\n")

    this.worshipSong.forEach { song ->
        details.plus("song.title\n")
    }
    details.plus("\n")
    this.giftSong.forEach { song ->
        details.plus("song.title\n")
    }
    return details
}