package ru.netology.dz3_2_gencoll

data class Note (
    val ownerId: Int = 123,                 // ID автора заметки
    val title: String,                      // заголовок заметки
    var text: String,                       // текст заметки
    val privacy: Int = 0,                   // уровень доступа
    val commentPrivacy: Int = 0,            // уровень доступа к комментированию
    val privacyView: String = "",           // уровень приватности просмотра
    val privacyComment: String = "",        // уровень приватности комментирования
    var noteComments: MutableMap<Int, CommentToNote> = mutableMapOf(),
                                            // коллекция комментариев к заметке
    var commentCounter: Int = 0             // счетчик комментариев, он же определяет ID комментария
)

data class CommentToNote (
    val noteId: Int,                        // ID заметки
    val noteOwnerId: Int = 123,             // ID автора заметки
//    val commentId: Int,                     // ID комментария
    val ownerId: Int = 456,                 // ID автора комментария
    val date: String = "12.09.2022",        // дата создание комментария
    val replyTo: Int = 789,                 // ID пользователя, в ответ кому создается комментарий
    var message: String,                    // текст комментария
    var deleted: Boolean = false            // признак удаления
)