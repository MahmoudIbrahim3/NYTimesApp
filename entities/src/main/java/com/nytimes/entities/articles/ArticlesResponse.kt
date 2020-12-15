package com.nytimes.entities.articles

data class ArticlesResponse (
	val status : String?,
	val copyright : String?,
	val num_results : Int?,
	val results : List<ArticleEntity>?
)