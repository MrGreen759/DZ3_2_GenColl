package ru.netology.dz3_2_gencoll

fun main() {

    // создаем пост 1
    var newCommentsInfo = Comments(1)
    var newLike = Like(1, true, false)
    var newPostSource = PostSource(platform = "android", data = "profile_photo")
    var newRepostsInfo = RepostsInfo(12)
    var newPost = Post(comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo)
    WallService.addPost(newPost)
    WallService.printPosts()

    // создаем пост 2
    newCommentsInfo = Comments(10)
    newLike = Like(10, true, false)
    newPost = Post(comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo)
    WallService.addPost(newPost)
    WallService.printPosts()

    // создаем пост 3
    newCommentsInfo = Comments(20)
    newLike = Like(20, true, true)
    newPost = Post(comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo,
        views = 144,
        attachments = arrayOf(GiftAttachment(Gift(1, "t1", "t2", "t3")),
            GraffityAttachment(Graffity(1, 2, "url", 400, 400)))
    )
    WallService.addPost(newPost)
    WallService.printPosts()

    // обновляем пост 2 (меняем количество комментариев, лайков и просмотров)
    newCommentsInfo = Comments(100)
    newLike = Like(30, userLikes = true)
    newPost = Post(2,
        comments = newCommentsInfo,
        likes = newLike,
        postSource = newPostSource,
        reposts = newRepostsInfo,
        views = 214)
    if (!WallService.updatePost(newPost)) println("Пост не найден.")
    WallService.printPosts()

    // добавляем комментарий к существующему посту
    var newComment = CommentToPost(345, 456, "15.09.2022", "This is comment")
    WallService.createComment(2, newComment)

    // добавляем комментарий к несуществующему посту
//    newComment = CommentToPost(345, 456, "15.09.2022", "This is comment")
//    WallService.createComment(4, newComment)


    // -------------- Заметки --------------

    // создаем заметку 1
    var newNoteTitle = "Заметка"
    var newNoteText = "Текст заметки"
    var newNote = Note(title = newNoteTitle, text = newNoteText)
    NoteService.addNote(newNote)
    NoteService.printNote(1)

    // создаем комментарий 1 к заметке 1
    var newCommwentToNote = CommentToNote(1, message = "Комментарий 1 к заметке 1")
    NoteService.addCommentToNote(newCommwentToNote)
    NoteService.printNote(1)

    // создаем комментарий 2 к заметке 1
    newCommwentToNote = CommentToNote(1, message = "Комментарий 2 к заметке 1")
    NoteService.addCommentToNote(newCommwentToNote)
    NoteService.printNote(1)

    // создаем комментарий 3 к заметке 1
    newCommwentToNote = CommentToNote(1, message = "Комментарий 3 к заметке 1")
    NoteService.addCommentToNote(newCommwentToNote)
    NoteService.printNote(1)

    // удаляем комментарий 2
    NoteService.deleteComment(1, 2)
    NoteService.printNote(1)

     // восстанавливаем комментарий 2
    NoteService.restoreComment(1, 2)
    NoteService.printNote(1)

    // редактируем комментарий 3
    NoteService.editCommentToNote(1, 3, "Это новый комментарий 3")
    NoteService.printNote(1)

    // редактируем заметку
    NoteService.editNote(1, "Это уже новый текст заметки 1")
    NoteService.printNote(1)
}
