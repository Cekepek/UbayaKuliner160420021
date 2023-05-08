package com.cekepek.ubayakuliner160420021.model

import com.google.gson.annotations.SerializedName

data class Restaurant (
    val id:String?,
    @SerializedName("nama") //karena variabel name disini berbeda dengan yang di JSON yaitu student_name
    val name:String?,
    val alamat:String?,
    @SerializedName("noTelp")
    val phoneNum:String?,
    val description:String?,
    @SerializedName("photo")
    val photoUrl:String?,
    val rating:Float,
    @SerializedName("review")
    val totalReviews:Integer
)

data class Account (
    val id:String?,
    val username:String?,
    val password:String?,
    val name: String?,
    val studentId:String?,
    val phone:String?,
    val profilePic:String?
)

data class Review (
    val id:String?,
    val username:String?,
    val review:String?,
    val restoId:String?
        )