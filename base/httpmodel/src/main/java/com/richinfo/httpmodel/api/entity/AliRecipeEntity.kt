package com.richinfo.httpmodel.api.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @ClassName AliRecipeEntity
 * @Author zjw
 * @Date 2021/3/2 16:54
 * 简介：阿里菜谱
 */

/**
 *
 *      {
"showapi_res_code": 0,
"showapi_res_error": "",
"showapi_res_body": {
"datas": [
{
"des": "生活里真的是创意无限,虽然都是一样的柴米油盐,有些家吃的欢,有些家吃的愁。所以要吃的好，不在于你买了什么，而是你即使用简单的材料也能做出让家人有惊喜的美食。今天这道美食就是这样，简单的食材，只用到了一个鸡蛋和一张海苔就能让你尝到从没吃过的味道，不用加一点调料，不用盐，不用味精！",
"smallImg": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151775155_smallImg.jpg",
"steps": [
{
"content": "准备好一张海苔",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801150911864_1.jpg",
"orderNum": 1
},
{
"content": "把海苔放在一制盘子里",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151018866_2.jpg",
"orderNum": 2
},
{
"content": "准备好一点温水",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/147880115109369_3.jpg",
"orderNum": 3
},
{
"content": "在海苔的四周抹上温水",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151135287_4.jpg",
"orderNum": 4
},
{
"content": "打入一个鸡蛋",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151293194_5.jpg",
"orderNum": 5
},
{
"content": "把海苔包起，捏紧",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151375757_6.jpg",
"orderNum": 6
},
{
"content": "锅里放入适量的油，烧热后放入油炸",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151439629_7.jpg",
"orderNum": 7
},
{
"content": "炸到外面有点松脆的感觉就可以了",
"imgUrl": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151541259_8.jpg",
"orderNum": 8
}
],
"type": "热门专题 蛋奶豆制品 菜式 蛋类 创意菜 鸡蛋",
"tip": "1. 海苔四周抹水是因为放入鸡蛋后可以把海苔包拢，鸡蛋不会流出； 2. 如果有的海苔太薄，建议用两张比较安全； 3. 煎之前油要烧热再放入才会炸的快，大约炸一分钟左右就可以了。 4. 用平底锅也可以少放油，就是把平底锅倾斜一下油就集中到一边了。",
"id": "5819e55df2954c15f83d3bbd",
"type_v3": "创意菜 鸡蛋",
"type_v2": "菜式 蛋类",
"type_v1": "热门专题 蛋奶豆制品",
"_id": {
"timestamp": 1478092125,
"time": 1478092125000,
"machineIdentifier": 15897932,
"processIdentifier": 5624,
"counter": 4012989,
"timeSecond": 1478092125,
"date": 1478092125000
},
"cpName": "爆浆鸡蛋",
"yl": [
{
"ylName": "鸡蛋",
"ylUnit": ""
},
{
"ylName": "海苔",
"ylUnit": ""
}
],
"largeImg": "http://app2.showapi.com/img/caipuImg3/201611110205/1478801151716465_largeImg.jpg"
}
],
"ret_code": "0",
"flag": true,
"remark": "查询成功!",
"page": 1,
"allNum": 1,
"msg": "查询成功!",
"allPage": 1
}
}
 *
 */

data class AliRecipeEntity(
    val showapi_res_body: ShowapiResBody,
    val showapi_res_code: Int,
    val showapi_res_error: String,
    val showapi_res_id: String


) {
    override fun toString(): String {
        return "AliRecipeEntity(showapi_res_body=$showapi_res_body, showapi_res_code=$showapi_res_code, showapi_res_error='$showapi_res_error', showapi_res_id='$showapi_res_id')"
    }
}

data class ShowapiResBody(
    val allNum: Int,
    val allPage: Int,
    val datas: MutableList<CaiPuDatas>,
    val flag: Boolean,
    val maxResult: Int,
    val msg: String,
    val page: Int,
    val remark: String,
    val ret_code: String

) {
    override fun toString(): String {
        return "ShowapiResBody(allNum=$allNum, allPage=$allPage, datas=$datas, flag=$flag, maxResult=$maxResult, msg='$msg', page=$page, remark='$remark', ret_code='$ret_code')"
    }
}

@Parcelize
data class CaiPuDatas(
    val cpName: String,
    val ct: String,
    val des: String,
    val largeImg: String,
    val smallImg: String,
    val steps: MutableList<Step>,
    val tip: String,
    val type: String,
    val type_v1: String,
    val type_v2: String,
    val type_v3: String,
    val yl: List<Yl>

) : Parcelable {
    override fun toString(): String {
        return "Datas(cpName='$cpName', ct='$ct', des='$des', largeImg='$largeImg', smallImg='$smallImg', steps=$steps, tip='$tip', type='$type', type_v1='$type_v1', type_v2='$type_v2', type_v3='$type_v3', yl=$yl)"
    }
}

@Parcelize
data class Step(
    val content: String,
    val imgUrl: String,
    val old_imgUrl: String,
    val orderNum: Int

) : Parcelable {
    override fun toString(): String {
        return "Step(content='$content', imgUrl='$imgUrl', old_imgUrl='$old_imgUrl', orderNum=$orderNum)"
    }
}

@Parcelize
data class Yl(
    val ylName: String,
    val ylUnit: String

) : Parcelable {
    override fun toString(): String {
        return "Yl(ylName='$ylName', ylUnit='$ylUnit')"
    }
}