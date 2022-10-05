package ru.netology.dz3_2_gencoll

object NoteService {
    private var notes = mutableMapOf<Int, Note>()
    private var lastNoteId: Int = 0

    // ---------------- Обработка заметок ----------------

    // добавление заметки
    fun addNote(note: Note): Int {
        lastNoteId++
        notes.put(lastNoteId, note)
        return lastNoteId
    }

    // возвращаем заметку по её ID
    fun getNote(noteId: Int): Note? {
        if (!notes.containsKey(noteId)) throw NoteNotFoundException("Заметки $noteId не существует")
        else return notes.get(noteId)
    }

    // редактирование заметки
    fun editNote(noteId: Int, newText: String): Boolean {
        if (!notes.containsKey(noteId)) {
            println ("Заметки $noteId не существует")
            return false
        } else {
            notes.get(noteId)?.text = newText
            return true
        }
    }

    // удаление заметки
    fun removeNote(noteId: Int): Boolean {
        if (!notes.containsKey(noteId)) {
            println("Заметки $noteId не существует")
            return false
        } else {
            notes.remove(noteId)
            return true
        }
    }

    // возвращаем список заметок
    fun getAllNotes(): List<Pair<Int, Note>> {
        return notes.toList()
    }
    // ---------------------------------------------------


    // -------------- Обработка комментариев --------------

    // добавление комментария к заметке
    fun addCommentToNote(comment: CommentToNote): Int? {
        val nid = comment.noteId
        if (!notes.containsKey(nid)) throw NoteNotFoundException("Заметки $nid не существует")
        else {
            var tempNote = notes[nid]
            if (tempNote != null) {
                var tempCounter = tempNote.commentCounter + 1
                tempNote.noteComments[tempCounter] = comment
                tempNote.commentCounter = tempCounter
                notes[nid] = tempNote
                return tempCounter
            } else return null
        }
    }

    // возвращаем список комментариев к заметке
    fun getCommentList(noteId: Int) : List<CommentToNote> {
        var tempList = mutableListOf<CommentToNote>()
        if (!notes.containsKey(noteId)) throw NoteNotFoundException("Заметки $noteId не существует")
        else {
            val tempCommentsMap = notes[noteId]?.noteComments
            if (tempCommentsMap != null) {
                for (comment in tempCommentsMap) {
                    if(!comment.value.deleted) tempList.add(comment.value)
                }
            }
        }
        return tempList
    }

    // редактирование комментария
    fun editCommentToNote(noteId: Int, commentId: Int, newText: String): Boolean {
        if (!notes.containsKey(noteId)) {
            println("Заметки $noteId не существует")
            return false
        }

        var tempCommentsMap = notes[noteId]?.noteComments
        if (tempCommentsMap != null) {
            if (!tempCommentsMap.containsKey(commentId)) {
                println("Комментария $commentId не существует")
                return false
            }
            if (tempCommentsMap[commentId]?.deleted == false) {
                tempCommentsMap[commentId]?.message = newText
                notes[noteId]?.noteComments = tempCommentsMap
                return true
            }
        }
        return false
    }

    // удаление комментария
    fun deleteComment(noteId: Int, commentId: Int) : Boolean {
        if (!notes.containsKey(noteId)) {
            println("Заметки $noteId не существует")
            return false
        }
        var tempCommentsMap = notes[noteId]?.noteComments
        if (tempCommentsMap != null) {
            if (tempCommentsMap[commentId]?.deleted == false) {
                tempCommentsMap[commentId]?.deleted = true
                notes[noteId]?.noteComments = tempCommentsMap
                return true
            }
        }
        return false
    }

    // восстановление комментария
    fun restoreComment(noteId: Int, commentId: Int) : Boolean {
        if (!notes.containsKey(noteId)) {
            println("Заметки $noteId не существует")
            return false
        }
        var tempCommentsMap = notes[noteId]?.noteComments
        if (tempCommentsMap != null) {
            if (tempCommentsMap[commentId]?.deleted == true) {
                tempCommentsMap[commentId]?.deleted = false
                notes[noteId]?.noteComments = tempCommentsMap
                return true
            }
        }
        return false
    }

    // проверочная печать заметки
    fun printNote(noteId: Int) {
        if (!notes.containsKey(noteId)) throw NoteNotFoundException("Заметки $noteId не существует")
        println("---------- Заметка:")
        println("ID: $noteId")
        println("Заголовок: " + (notes[noteId]?.title))
        println("Текст: " + (notes[noteId]?.text))

        println("Количество комментариев (включая удалённые): " + (notes[noteId]?.commentCounter))
        if(notes[noteId]!=null) {
            val temp = notes[noteId]?.commentCounter
            val tempC = notes[noteId]?.noteComments
            if (temp != null) {
                if (temp > 0) {
                    if (tempC != null) {
                        println("Актуальные комментарии:")
                        for ((key, value) in tempC) {
                            if (!value.deleted) println("Комментарий #$key: " + value.message)
                        }
                    }
                }
            }
        }
    }

    // подготовка к тестам
    fun prepareForTest() {
        NoteService.notes = mutableMapOf()
        NoteService.lastNoteId = 0

        val newNoteTitle = "Заметка"
        val newNoteText = "Текст заметки"
        val newNote = Note(title = newNoteTitle, text = newNoteText)
        NoteService.addNote(newNote)

        val newCommwentToNote = CommentToNote(1, message = "Комментарий 1 к заметке 1")
        NoteService.addCommentToNote(newCommwentToNote)
    }


}