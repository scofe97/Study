package com.example.ch3_4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// @SerializeName -> api 의 정보와 일치시키기 위해 사용
// @Parcelize -> 직렬화가 가능해짐 (데이터 자체를 바로 사용가능한>)

@Parcelize
data class Book(
    @SerializedName("itemId") val itemId : Long,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("coverSmallUrl") val coverSmallUri : String,
    @SerializedName("coverLargeUrl") val coverLargeUri : String
) : Parcelable
