package com.wyb7.waffle.commons.bean.util

/**
 * Author: Wang Yibin
 * Date: 11-5-10
 * Time: 下午6:38
 */

object ScalaObjectUtil {
    def classOfObject(objClass: Class[_]): Class[_] = {
        var className = objClass.getName
        if (className.endsWith("$")) {
            className = className.substring(0, className.size - 1)
            return Class.forName(className).asInstanceOf[Class[_]]
        } else {
            return objClass
        }

    }
}