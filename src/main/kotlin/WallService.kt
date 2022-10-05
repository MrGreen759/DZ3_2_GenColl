package ru.netology.dz3_2_gencoll

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostId: Int = 0
    private var comments = emptyArray<CommentToPost>()

    // добавление поста в хранилище
    fun addPost (post: Post): Post {
        lastPostId++
        println("\nСоздаем пост. Номер поста - $lastPostId.")
        posts += post.copy(id = lastPostId)
        return posts.last()
    }

    // внесение изменений в существующий пост
    fun updatePost (post: Post): Boolean {
        println("\nОбновляем пост #${post.id}")
        for ((index) in posts.withIndex()) {
            if (posts[index].id == post.id) {
                val old = posts[index]
                posts[index] = post.copy(publicDate = old.publicDate, ownerId = old.ownerId)
                return true
            }
        }
        return false
    }

    // создание комментария
    fun createComment(postId: Int, comment: CommentToPost): CommentToPost {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }
        throw PostNotFoundException("Поста с ID $postId не существует.")
    }

    // проверочная печать массива постов
    fun printPosts() {
        for (post in posts) {
            println("----------------")
            println("ID поста: ${post.id}, ID автора: ${post.ownerId}, опубликовано: ${post.publicDate}")
            println("Текст поста: ${post.content}")
            println("Комментариев: ${post.comments.count}, лайков: ${post.likes.count}, просмотров: ${post.views}")
        }
    }

    fun clear() {
        posts = emptyArray()
        lastPostId = 0
        comments = emptyArray()
    }

}
