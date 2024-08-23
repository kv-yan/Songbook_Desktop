package data.lambda.template.makeTemplate

import domain.model.Song
import domain.model.SongTemplate

fun makeSongTemplate(
    createDate: String,
    performerName: String,
    weekday: String,
    favorite: Boolean,
    glorifyingSong: List<Song>,
    worshipSong: List<Song>,
    giftSong: List<Song>,
    singleModeSongs: List<Song>,
) = SongTemplate("SongTemplate", createDate, performerName, weekday, favorite, glorifyingSong, worshipSong, giftSong, singleModeSongs)

fun makeSong(
    id: String,
    createDate: String,
    performerName: String,
    weekday: String,
    favorite: Boolean,
    glorifyingSong: List<Song>,
    worshipSong: List<Song>,
    giftSong: List<Song>,
    singleModeSongs: List<Song>
) = SongTemplate(id, createDate, performerName, weekday, favorite, glorifyingSong, worshipSong, giftSong , singleModeSongs)
