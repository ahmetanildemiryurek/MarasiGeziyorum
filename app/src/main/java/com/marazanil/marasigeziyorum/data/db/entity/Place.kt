package com.marazanil.marasigeziyorum.data.db.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    var id: String = "",
    var name: String = "",
    var imageUrl: String = "",
    var description: String = "",
    var category: String = ""
) : Parcelable
