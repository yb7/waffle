package com.wyb7.waffle.commons.util

/**
 * Author: Wang Yibin
 * Date: 11-4-14
 * Time: 上午11:36
 */

object JTypePredef {
    type JInt = java.lang.Integer
    type JLong = java.lang.Long
    type JBoolean = java.lang.Boolean
    type JList[T] = java.util.List[T]
    type JCollection[T] = java.util.Collection[T]
    type JArrayList[T] = java.util.ArrayList[T]
    type JMap[K, V] = java.util.Map[K, V]
    type JHashMap[K, V] = java.util.HashMap[K, V]
    type JSet[T] = java.util.Set[T]
    type JHashSet[T] = java.util.HashSet[T]
    type JBigDecimal = java.math.BigDecimal
}