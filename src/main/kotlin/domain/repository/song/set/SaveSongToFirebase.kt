package domain.repository.song.set

import domain.model.Song

interface SaveSongToFirebase {
     fun saveSong(song: Song)
}