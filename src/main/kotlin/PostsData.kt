package ru.netology.dz3_2_gencoll

data class Post (
    val id: Int = 0,                    // id поста
    val ownerId: Int = 12345,           // id владельца стены
    val fromId: Int = 123,                    // id автора поста
    val createdBy: Int = 12,                  // id администратора
    val publicDate: String = "12.09.2022", // дата публикации
    val content: String = "It's test content", // текст поста
    val replyOwnerId: Int? = null,              // id автора поста, в ответ на который создан пост
    val replyPostId: Int? = null,               // id поста, в ответ на который создан пост
    val friendsOnly: Boolean = false,   // только для друзей
    val comments: Comments,              // комментарии
    val copyright: Copyright? = null,           // источник информации
    val likes: Like,                    // лайки
    val reposts: RepostsInfo,           // инфо о репостах
    val views: Int = 124,               // просмотры
    val postType: String = "post",               // тип поста
    val postSource: PostSource,         // способ размещения записи
    val geo: Geo? = null,                       // местоположение
    val signerId: Int? =null,                  // id автора, если пост из сообщества
    val copyHistory: Array<Post>? = null,       // история репостов
    val canPin: Boolean = true,                // закрепление
    val canDelete: Boolean = false,             // удаление
    val canEdit: Boolean = false,               // редактирование
    val isPinned: Boolean = false,              // закреплен ли пост
    val isAds: Boolean = false,         // реклама ли
    val isFavorite: Boolean = false,            // в закладках ли
    val donut: Donut? = null,                   // VK Donut
    val postponed: Boolean = false,      // отложенный пост
    val attachments: Array<Attachment> = emptyArray()   // вложения
)

data class Comments (
    val count: Int = 0,
    val canComment: Boolean = true,
    val groupsCanComment: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = true,
)

data class CommentToPost (
    val id: Int,
    val fromId: Int,
    val date: String,
    val text: String
)

data class Copyright (
    val id: Int,
    val link: String,
    val name: String,
    val type: String
)

data class Like (
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canRepost: Boolean = true
)

data class RepostsInfo (
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class PostSource (
    val type: String = "vk",
    val platform: String,
    val data: String,
    val url: String? = null
)

data class Geo (
    val type: String = "city",
    val coordinates: String? = null,
    val place: Place? = null
)

data class Place (
    val id: Int,
    val title: String,
    val latitude: Int,
    val longtitude: Int,
    val created: String,
    val icon: Int,
    val checkins: Int,
    val updated: String,
    val type: Int,
    val country: Int,
    val city: Int,
    val address: String
)

data class Donut (
    val isDonut: Boolean = false,
    val paidDuration: Int,
    val placeHolder: String,
    val canOpenForAll: Boolean = false,
    val editMode: String,
)
