package data.lambda.song.makeSong

import domain.model.Song

fun makeSong(
    title: String,
    tonality: String,
    words: String,
    temp: String,
    isGlorifyingSong: Boolean,
    isWorshipSong: Boolean,
    isGiftSong: Boolean,
    isFromSongbookSong: Boolean,
) = Song(
    id = "Song",
    title = title,
    tonality = tonality,
    words = words,
    temp = temp,
    isGlorifyingSong = isGlorifyingSong,
    isWorshipSong = isWorshipSong,
    isGiftSong = isGiftSong,
    isFromSongbookSong = isFromSongbookSong,

    )

fun makeSong(
    id: String,
    title: String,
    tonality: String,
    words: String,
    temp: String,
    isGlorifyingSong: Boolean,
    isWorshipSong: Boolean,
    isGiftSong: Boolean,
    isFromSongbookSong: Boolean,
) = Song(
    id = id,
    title = title,
    tonality = tonality,
    words = words,
    temp = temp,
    isGlorifyingSong = isGlorifyingSong,
    isWorshipSong = isWorshipSong,
    isGiftSong = isGiftSong,
    isFromSongbookSong = isFromSongbookSong
)

