package com.richinfo.httpmodel.api.entity

/**
 * @ClassName ImageEntity
 * @Author zjw
 * @Date 2020/12/12 17:48
 * 简介：
 */
data class ImageEntity(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
) {
    override fun toString(): String {
        return "ImageEntity(hits=$hits, total=$total, totalHits=$totalHits)"
    }
}

data class Hit(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int


) {
    override fun toString(): String {
        return "Hit(comments=$comments, downloads=$downloads, favorites=$favorites, id=$id, imageHeight=$imageHeight, imageSize=$imageSize, imageWidth=$imageWidth, largeImageURL='$largeImageURL', likes=$likes, pageURL='$pageURL', previewHeight=$previewHeight, previewURL='$previewURL', previewWidth=$previewWidth, tags='$tags', type='$type', user='$user', userImageURL='$userImageURL', user_id=$user_id, views=$views, webformatHeight=$webformatHeight, webformatURL='$webformatURL', webformatWidth=$webformatWidth)"
    }
}