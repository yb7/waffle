package com.wyb7.waffle.commons.bean.util

import java.lang.reflect.{Method, Field}

/**
 * Author: Wang Yibin
 * Date: 11-3-10
 * Time: 下午1:51
 */

class ScalaPropertyDescriptor(g: Method, s: Method) {
    require(g != null)
    require(s != null)
    val getter = g;
    val setter = s;

    def name: String = getter.getName

}