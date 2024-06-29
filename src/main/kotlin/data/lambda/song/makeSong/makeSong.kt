package data.lambda.song.makeSong

import domain.model.Song

fun makeSong(
    title: String,
    tonality: String,
    words: String,
    temp: Int,
    isGlorifyingSong: Boolean,
    isWorshipSong: Boolean,
    isGiftSong: Boolean,
    isFromSongbookSong: Boolean
) = Song("Song", title, tonality, words,temp, isGlorifyingSong, isWorshipSong, isGiftSong, isFromSongbookSong)

fun makeSong(
    id: String,
    title: String,
    tonality: String,
    words: String,
    temp: Int,
    isGlorifyingSong: Boolean,
    isWorshipSong: Boolean,
    isGiftSong: Boolean,
    isFromSongbookSong: Boolean
) = Song(id, title, tonality,words,temp,isGlorifyingSong, isWorshipSong, isGiftSong, isFromSongbookSong)
