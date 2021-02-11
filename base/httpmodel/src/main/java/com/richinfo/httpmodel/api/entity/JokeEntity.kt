package com.richinfo.httpmodel.api.entity

/**
 * @ClassName JokeEntity
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/12 13:46
 * @Version 1.0
 * 简介：笑话数据实体类
 */
data class JokeEntity(
    val error_code: Int,
    val reason: String,
    val result: JokeResult

) {
    override fun toString(): String {
        return "JokeEntity(error_code=$error_code, reason='$reason', result=$result)"
    }
}

data class JokeResult(
    val data: List<Data>

) {
    override fun toString(): String {
        return "Result(data=$data)"
    }
}

data class Data(
    val content: String,
    val hashId: String,
    val unixtime: Int,
    val updatetime: String

) {
    override fun toString(): String {
        return "Data(content='$content', hashId='$hashId', unixtime=$unixtime, updatetime='$updatetime')"
    }
}