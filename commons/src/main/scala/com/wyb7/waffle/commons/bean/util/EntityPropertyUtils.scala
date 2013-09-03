package com.wyb7.waffle.commons.bean.util

/**
 * Author: Wang Yibin
 * Date: 11-3-10
 * Time: 上午10:02
 */

object EntityPropertyUtils {
    val SETTER_SUFFIX = "_$eq"

    def copy(source: AnyRef, target: AnyRef): AnyRef =
        ScalaPropertyUtils.copyProperties(source, target, Array("id", "version"))
}