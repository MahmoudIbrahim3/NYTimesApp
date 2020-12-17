package com.nytimes.entities.articles

data class MediaEntity (
	val type : String? = null,
	val subtype : String? = null,
	val caption : String? = null,
	val copyright : String? = null,
	val approved_for_syndication : Int? = null,
	val mediasMetaData : List<MediaMetaDataEntity>? = null
)