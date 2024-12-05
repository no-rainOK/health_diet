package com.example.health.pojo.dto

import android.os.Parcel
import android.os.Parcelable

data class UpdateUserInfo(
        val userId: Int,
        val name: String?,
        val sex: String?,
        val email: String?,
        val introduction: String?
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString() ?: "",
                parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(userId)
                parcel.writeString(name)
                parcel.writeString(sex)
                parcel.writeString(email)
                parcel.writeString(introduction)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<UpdateUserInfo> {
                override fun createFromParcel(parcel: Parcel): UpdateUserInfo {
                        return UpdateUserInfo(parcel)
                }

                override fun newArray(size: Int): Array<UpdateUserInfo?> {
                        return arrayOfNulls(size)
                }
        }
}

