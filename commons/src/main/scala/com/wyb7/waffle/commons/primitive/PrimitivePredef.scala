package com.wyb7.waffle.commons.primitive

import org.apache.commons.lang3.StringUtils

/**
 * Author: Wang Yibin
 * Date: 12-10-8
 * Time: 上午11:40
 */
object PrimitivePredef {
    case class WrappedStringPredef(str: String) {
        def notBlankOption: Option[String] = if (StringUtils.isNotBlank(str)) Some(str) else None
    }
    case class WrappedLongPredef(num: Long) {
        def greatThenZeroOption: Option[Long] = if (num > 0) Some(num) else None
    }
    case class WrappedIntPredef(num: Int) {
        def greatThenZeroOption: Option[Int] = if (num > 0) Some(num) else None
    }

    case class WrappedAnyRefPredef[T](obj: T) {
        def notNullOption: Option[T] = if (obj != null) Some(obj) else None
    }

    implicit def anyRefToWrappedPredef[T](obj: T): WrappedAnyRefPredef[T] = WrappedAnyRefPredef(obj)
    implicit def strToWrappedPredef(str: String) = WrappedStringPredef(str)
    implicit def longToWrappedPredef(num: Long) = WrappedLongPredef(num)
    implicit def intToWrappedPredef(num: Int) = WrappedLongPredef(num)
}
