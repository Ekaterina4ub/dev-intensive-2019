package com.example.devintensive.models

class UserView (
    val id: String,
    val fullName: String,
    val nickName:String,
    val initials: String?,
    val avatar: String? = null,
    val status: String? = "offline"
){
}