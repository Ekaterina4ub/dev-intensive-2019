package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView

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
