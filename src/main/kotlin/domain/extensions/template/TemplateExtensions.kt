package domain.extensions.template

import domain.model.SongTemplate

fun SongTemplate.getDetails(): String {
    val details = StringBuilder()
    this.glorifyingSong.forEach { song ->
        details.append("${song.title}\n")
    }
    details.append("\n")

    this.worshipSong.forEach { song ->
        details.append("${song.title}\n")
    }
    details.append("\n")
    this.giftSong.forEach { song ->
        details.append("${song.title}\n")
    }
    return details.toString()
}