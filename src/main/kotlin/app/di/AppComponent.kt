package app.di

import data.repository.song.delete.DeleteSongFromFirebaseImpl
import data.repository.song.get.all.GetSongsFromFirebaseImpl
import data.repository.song.get.gift.GetGiftSongsFromFirebaseImpl
import data.repository.song.get.glorifying.GetGlorifyingSongsFromFirebaseImpl
import data.repository.song.get.worship.GetWorshipSongsFromFirebaseImpl
import data.repository.song.set.SaveSongToFirebaseImpl
import data.repository.song.update.UpdateSongInFirebaseImpl
import data.repository.template.delete.DeleteSongTemplateFromFirebaseImpl
import data.repository.template.get.GetTemplatesFromFirebaseImpl
import data.repository.template.set.SaveSongTemplateToFirebaseImpl
import data.repository.template.update.UpdateSongTemplateInFirebaseImpl
import domain.useCase.song.delete.DeleteSongFromFirebaseUseCase
import domain.useCase.song.get.all.GetSongsFromFirebaseUseCase
import domain.useCase.song.get.gift.GetGiftSongsFromFirebaseUseCase
import domain.useCase.song.get.glorifying.GetGlorifyingSongsFromFirebaseUseCase
import domain.useCase.song.get.worship.GetWorshipSongsFromFirebaseUseCase
import domain.useCase.song.set.SaveSongToFirebaseUseCase
import domain.useCase.song.update.UpdateSongInFirebaseUseCase
import domain.useCase.template.delete.DeleteSongTemplateFromFirebaseUseCase
import domain.useCase.template.get.GetTemplatesFromFirebaseUseCase
import domain.useCase.template.set.SaveSongTemplateInFirebaseUseCase
import domain.useCase.template.update.UpdateSongTemplateInFirebaseUseCase

object AppComponent {

    /* **********  Song  ***********/
    //    get songs
    private val getSongsFromFirebase = GetSongsFromFirebaseImpl()
    val getSongsFromFirebaseUseCase = GetSongsFromFirebaseUseCase(getSongsFromFirebase)

    //    save song to Firebase
    private val saveSongToFirebase = SaveSongToFirebaseImpl()
    val saveSongToFirebaseUseCase = SaveSongToFirebaseUseCase(saveSongToFirebase)

    //    delete song from Firebase
    private val deleteSongFromFirebase = DeleteSongFromFirebaseImpl()
    val deleteSongFromFirebaseUseCase = DeleteSongFromFirebaseUseCase(deleteSongFromFirebase)

    //    update song in Firebase
    private val updateSongInFirebase = UpdateSongInFirebaseImpl()
    val updateSongFromFirebaseUseCase = UpdateSongInFirebaseUseCase(updateSongInFirebase)

    //    get glorifying songs
    private val getGlorifyingSongsFromFirebase = GetGlorifyingSongsFromFirebaseImpl()
    val getGlorifyingSongsFromFirebaseUseCase = GetGlorifyingSongsFromFirebaseUseCase(getGlorifyingSongsFromFirebase)

    //    get worship songs
    private val getWorshipSongsFromFirebase = GetWorshipSongsFromFirebaseImpl()
    val getWorshipSongsFromFirebaseUseCase = GetWorshipSongsFromFirebaseUseCase(getWorshipSongsFromFirebase)

    //    get gift songs
    private val getGiftSongsFromFirebase = GetGiftSongsFromFirebaseImpl()
    val getGiftSongsFromFirebaseUseCase = GetGiftSongsFromFirebaseUseCase(getGiftSongsFromFirebase)


    /* **********  Template  ***********/

    //    get templates
    private val getSongTemplateFromFirebase = GetTemplatesFromFirebaseImpl()
    val getSongTemplatesFromFirebaseUseCase = GetTemplatesFromFirebaseUseCase(getSongTemplateFromFirebase)

    //    delete templates
    private val deleteSongTemplateFromFirebase = DeleteSongTemplateFromFirebaseImpl()
    val deleteSongTemplatesFromFirebaseUseCase = DeleteSongTemplateFromFirebaseUseCase(deleteSongTemplateFromFirebase)

    //    save templates
    private val saveSongTemplateInFirebase = SaveSongTemplateToFirebaseImpl()
    val saveSongTemplateInFirebaseUseCase = SaveSongTemplateInFirebaseUseCase(saveSongTemplateInFirebase)

    //    save templates
    private val updateSongTemplateToFirebase = UpdateSongTemplateInFirebaseImpl()
    val updateSongTemplateInFirebaseUseCase = UpdateSongTemplateInFirebaseUseCase(updateSongTemplateToFirebase)


}