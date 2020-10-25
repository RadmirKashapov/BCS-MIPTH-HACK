package com.bscskol.main.article.entities

import com.bscskol.main.user.entities.User
import java.text.DecimalFormat
import java.util.*


/**
 * Slope One algorithm implementation
 */
object SlopeOne {
    private val diff: MutableMap<Item, MutableMap<Item, Double>> = HashMap<Item, MutableMap<Item, Double>>()
    private val freq: MutableMap<Item, MutableMap<Item, Int>> = HashMap<Item, MutableMap<Item, Int>>()
    private var inputData: Map<User, HashMap<Item, Double?>>? = null
    private val outputData: MutableMap<User, HashMap<Item, Double?>> = HashMap<User, HashMap<Item, Double?>>()

    fun slopeOne(users: List<User>): MutableMap<User, HashMap<Item, Double?>> {
        var inputData = InputData.initializeData(users)
        buildDifferencesMatrix(inputData)
        return predict(inputData)
    }

    /**
     * Based on the available data, calculate the relationships between the
     * items and number of occurences
     *
     * @param data
     * existing user data and their items' ratings
     */
    private fun buildDifferencesMatrix(data: Map<User, HashMap<Item, Double>>) {
        for (user in data!!.values) {
            for ((key, value) in user) {
                if (!diff.containsKey(key)) {
                    diff[key] = HashMap<Item, Double>()
                    freq[key] = HashMap<Item, Int>()
                }
                for ((key1, value1) in user) {
                    var oldCount = 0
                    if (freq[key]!!.containsKey(key1)) {
                        oldCount = freq[key]!![key1]!!.toInt()
                    }
                    var oldDiff = 0.0
                    if (diff[key]!!.containsKey(key1)) {
                        oldDiff = diff[key]!![key1]!!.toDouble()
                    }
                    val observedDiff = value!! - value1!!
                    freq[key]!![key1] = oldCount + 1
                    diff[key]!![key1] = oldDiff + observedDiff
                }
            }
        }
        for (j in diff.keys) {
            for (i in diff[j]!!.keys) {
                val oldValue = diff[j]!![i]!!.toDouble()
                val count = freq[j]!![i]!!.toInt()
                diff[j]!![i] = oldValue / count
            }
        }
    }

    /**
     * Based on existing data predict all missing ratings. If prediction is not
     * possible, the value will be equal to -1
     *
     * @param data
     * existing user data and their items' ratings
     */
    private fun predict(data: Map<User, HashMap<Item, Double>>): MutableMap<User, HashMap<Item, Double?>> {
        val uPred: HashMap<Item, Double> = HashMap<Item, Double>()
        val uFreq: HashMap<Item, Int> = HashMap<Item, Int>()
        for (j in diff.keys) {
            uFreq[j] = 0
            uPred[j] = 0.0
        }
        for ((key, value) in data!!) {
            for (j in value.keys) {
                for (k in diff.keys) {
                    try {
                        val predictedValue = diff[k]!![j]!!.toDouble() + value[j]!!.toDouble()
                        val finalValue = predictedValue * freq[k]!![j]!!.toInt()
                        uPred[k] = uPred[k]!! + finalValue
                        uFreq[k] = uFreq[k]!! + freq[k]!![j]!!.toInt()
                    } catch (e1: NullPointerException) {
                    }
                }
            }
            val clean: HashMap<Item, Double?> = HashMap<Item, Double?>()
            for (j in uPred.keys) {
                if (uFreq[j]!! > 0) {
                    clean[j] = uPred[j]!!.toDouble() / uFreq[j]!!.toInt()
                }
            }
            for (j in InputData.items) {
                if (value.containsKey(j)) {
                    clean[j] = value.get(j)
                } else if (!clean.containsKey(j)) {
                    clean[j] = -1.0
                }
            }
            outputData[key] = clean
        }
        return outputData
    }

}