package com.nytimes.entities.articles

data class MediaEntity (
	val type : String?,
	val subtype : String?,
	val caption : String?,
	val copyright : String?,
	val approved_for_syndication : Int?,
	val mediasMetaData : List<MediaMetaDataEntity>?
)