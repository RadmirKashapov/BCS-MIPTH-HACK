package com.bscskol.main.article.entities

import com.bscskol.main.user.entities.User
import java.util.*


object InputData {
    internal var items: List<Item> = listOf()


    fun initializeItems(article: List<String>) {
        for (elem in article) {
            items.plus(Item(elem))
        }
    }

    fun initializeData(users: List<User>): Map<User, HashMap<Item, Double>> {

        val data: MutableMap<User, HashMap<Item, Double>> = HashMap<User, HashMap<Item, Double>>()

        for (item in users) {
            initializeItems(item.ratedArticles.map {it.articleId!!})
            val map = hashMapOf(*item.ratedArticles.map { Item(it.articleId) to it.preference.toDouble()}.toTypedArray())
            data.put(item, map)
        }

        return data
    }
}