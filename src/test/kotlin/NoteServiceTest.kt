package ru.netology.dz3_2_gencoll

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class NoteServiceTest {

    @Before
    fun beforeTest() {
        NoteService.prepareForTest()
    }

    @Test
    fun addNote() {
        val newNoteTitle = "Заметка"
        val newNoteText = "Текст заметки"
        val newNote = Note(title = newNoteTitle, text = newNoteText)
        val result = NoteService.addNote(newNote)
        assertEquals(2, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrow() {
        NoteService.getNote(3)
    }

    @Test
    fun editNoteSuccess() {
        val result = NoteService.editNote(1, "Новый текст")
        assertEquals(true, result)
    }

    @Test
    fun editNoteFailure() {
        val result = NoteService.editNote(3, "Новый текст")
        assertEquals(false, result)
    }

    @Test
    fun removeTestSuccess() {
        val result = NoteService.removeNote(1)
        assertEquals(true, result)
    }

    @Test
    fun removeTestFailure() {
        val result = NoteService.removeNote(3)
        assertEquals(false, result)
    }

    @Test
    fun getAllNotes() {
        val result = NoteService.getAllNotes()
        assertNotNull(result)
    }

    @Test
    fun addCommentToNote() {
        var newCommwentToNote = CommentToNote(1, message = "Комментарий 2 к заметке 1")
        val result = NoteService.addCommentToNote(newCommwentToNote)
        assertEquals(2, result)
    }

    @Test
    fun getCommentList() {
        val result = NoteService.getCommentList(1)
        assertNotNull(result)
    }

    @Test
    fun editCommentSuccess() {
        val result = NoteService.editCommentToNote(1, 1, "Новый текст комментария")
        assertTrue(result)
    }

    @Test
    fun editCommentFailure1() {
        val result = NoteService.editCommentToNote(4, 1, "Новый текст комментария")
        assertFalse(result)
    }

    @Test
    fun editCommentFailure2() {
        val result = NoteService.editCommentToNote(1, 3, "Новый текст комментария")
        assertFalse(result)
    }

    @Test
    fun deleteCommentSuccess() {
        val result = NoteService.deleteComment(1, 1)
        assertTrue(result)
    }

    @Test
    fun deleteCommentFailure() {
        val result = NoteService.deleteComment(3, 1)
        assertFalse(result)
    }

    @Test
    fun restoreCommentSuccess() {
        NoteService.deleteComment(1, 1)
        val result = NoteService.restoreComment(1, 1)
        assertTrue(result)
    }

    @Test
    fun restoreCommentFailure() {
        val result = NoteService.restoreComment(1, 1)
        assertFalse(result)
    }


}