package com.nytimes.entities.articles

data class ArticleEntity (
	val uri : String?,
	val url : String?,
	val id : Int?,
	val asset_id : Int?,
	val source : String?,
	val published_date : String?,
	val updated : String?,
	val section : String?,
	val subsection : String?,
	val nytdsection : String?,
	val adx_adx_keywordswords : String?,
	val column : String?,
	val byline : String?,
	val type : String?,
	val title : String?,
	val abstract : String?,
	val des_facet : List<String>?,
	val org_facet : List<String>?,
	val per_facet : List<String>?,
	val geo_facet : List<String>?,
	val mediaEntity : List<MediaEntity>?,
	val eta_id : Int?
)