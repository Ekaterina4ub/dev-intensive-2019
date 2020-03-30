package com.example.devintensive.extensions

import com.example.devintensive.models.User
import com.example.devintensive.models.UserView
import java.util.*

fun User.toUserView(): UserView {

    val nickName = ""
    val initials = ""
    val status = if (lastVisit == null) "Уще ни разу не был" else if (isOnline) "online" else "Послений раз был $lastVisit.humanizeDiff()"

    return UserView(
        id,
        fullName = "$firstName + $lastName",
        avatar = avatar,
        nickName = nickName,
        initials = initials,
        status = status
    )
}
