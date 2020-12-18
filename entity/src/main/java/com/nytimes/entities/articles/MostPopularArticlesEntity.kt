package com.nytimes.entities.articles

data class MostPopularArticlesEntity (
	var status : String? = null,
	var copyright : String? = null,
	var num_results : Int? = null,
	var results : List<ArticleEntity>? = null
)