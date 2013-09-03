package com.wyb7.waffle.commons.util

/**
 * Author: Wang Yibin
 * Date: 11-4-13
 * Time: 下午5:32
 */

object EnumerationUtil extends Logging {
    def reflectEnumValue(enumClass:Class[_], valueName:String) : Enumeration#Value = {
        debug("enumClass: " + enumClass.getName + " with valueName: " + valueName)
        val method = enumClass.getMethod("withName", classOf[String])
        return method.invoke(enumClass, valueName).asInstanceOf[Enumeration#Value]
    }
}