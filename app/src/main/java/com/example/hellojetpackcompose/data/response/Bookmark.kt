package com.example.hellojetpackcompose.data.response

import com.google.gson.annotations.SerializedName

data class Bookmark(
    @field:SerializedName("bookmark")
    val bookmark: List<BookmarkItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("error")
    val error: String? = null,

    @field:SerializedName("user")
    val user: String? = null
)

data class BookmarkItem(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("isSelect")
    val isSelect: Boolean? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)


data class bookrequest(

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)
