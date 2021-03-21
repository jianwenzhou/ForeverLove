package com.richinfo.httpmodel.api.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @ClassName ImageEntity
 * @Author zjw
 * @Date 2020/12/12 17:48
 * 简介：
 */

/**
 * {
"id": 3063284,
"pageURL": "https://pixabay.com/photos/rose-flower-petal-floral-noble-3063284/",
"type": "photo",
"tags": "rose, flower, petal",
"previewURL": "https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg",
"previewWidth": 150,
"previewHeight": 99,
"webformatURL": "https://pixabay.com/get/g5b27d94a6f2dde45486616b62abe692b0e3eab7f1268d73cb575bada19690dcad67df8ef8c110debf143b2c1392ba639eb1ab61c7918f4e73545860f5c10e48c_640.jpg",
"webformatWidth": 640,
"webformatHeight": 426,
"largeImageURL": "https://pixabay.com/get/gcd1354be1f7a86213c8dd708240eea7ff282692bc4b5106ceb982bc30a683596909bb6bb33abdc568481bee65ab11ae7a4f09ec1cb6e4dd464c9b6fab3ca2072_1280.jpg",
"imageWidth": 6000,
"imageHeight": 4000,
"imageSize": 3574625,
"views": 847037,
"downloads": 538578,
"favorites": 1115,
"likes": 1275,
"comments": 285,
"user_id": 1564471,
"user": "anncapictures",
"userImageURL": "https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg"
},
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

@Parcelize
data class Hit(
    val comments: Int,
    val downloads: Int,
    val favorites: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,//原图，6000*4000
    val likes: Int,
    val pageURL: String,//图片网页
    val previewHeight: Int,
    val previewURL: String,//预览图片，150*99
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,//用户图像
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,//web图片，640*426
    val webformatWidth: Int


) : Parcelable {
    override fun toString(): String {
        return "Hit(comments=$comments, downloads=$downloads, favorites=$favorites, id=$id, imageHeight=$imageHeight, imageSize=$imageSize, imageWidth=$imageWidth, largeImageURL='$largeImageURL', likes=$likes, pageURL='$pageURL', previewHeight=$previewHeight, previewURL='$previewURL', previewWidth=$previewWidth, tags='$tags', type='$type', user='$user', userImageURL='$userImageURL', user_id=$user_id, views=$views, webformatHeight=$webformatHeight, webformatURL='$webformatURL', webformatWidth=$webformatWidth)"
    }
}