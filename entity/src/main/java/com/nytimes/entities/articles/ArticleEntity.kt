package com.nytimes.entities.articles

import androidx.room.Entity
import com.nytimes.entities.articles.MediaEntity

@Entity(primaryKeys = ["id"])
data class ArticleEntity (
    val uri : String? = null,
    val url : String? = null,
    val id : String,
    val asset_id : String? = null,
    val source : String? = null,
    val published_date : String? = null,
    val updated : String? = null,
    val section : String? = null,
    val subsection : String? = null,
    val nytdsection : String? = null,
    val adx_adx_keywordswords : String? = null,
    val column : String? = null,
    val byline : String? = null,
    val type : String? = null,
    val title : String? = null,
    var abstract : String? = null,
    val des_facet : List<String>? = null,
    val org_facet : List<String>? = null,
    val per_facet : List<String>? = null,
    val geo_facet : List<String>? = null,
    val mediaEntity : List<MediaEntity>? = null,
    val eta_id : Int? = null
)