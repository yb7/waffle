package com.wyb7.waffle.commons.bean.util

import java.lang.reflect.{Modifier, Method}
import scala.collection.mutable
import com.wyb7.waffle.commons.util.Logging
import org.apache.commons.lang3.reflect.MethodUtils

/**
 * Author: Wang Yibin
 * Date: 11-3-10
 * Time: 上午10:02
 */

object ScalaPropertyUtils extends Logging {
    val SETTER_SUFFIX = "_$eq"

    def copyProperties(source: AnyRef, target: AnyRef): AnyRef = {
        return copyProperties(source, target, Array.empty[String])
    }

    def copyProperties(source: AnyRef, target: AnyRef, ignoreFields: Array[String]): AnyRef = {
        require(source != null)
        require(target != null)
        val targetProperties = getScalaProperties(target.getClass)

        val sourceMethodMap = ReflectionUtils.getAllDeclaredMethods(source.getClass).map(m => (m.getName->m)).toMap

        for (prop <- targetProperties) {
            trace("target property: " + prop.name)
            try {
                if (!ignoreFields.contains(prop.name)) {
                    sourceMethodMap.get(prop.getter.getName).map {sourceGetter =>
                        val actualType = sourceGetter.getReturnType
                        val expectedType = prop.setter.getParameterTypes()(0)
                        if (expectedType.isAssignableFrom(actualType)) {
                            val value = sourceGetter.invoke(source)
                            prop.setter.invoke(target, value)
                        }
                    }

//                    val value = MethodUtils.invokeMethod(source, prop.getter.getName)
//                    trace("set property " + prop.name + " with value " + value)
//                    prop.setter.invoke(target, value)
                }
            } catch {
                case _: NoSuchMethodException => trace("no such getter in source for property: " + prop.name)
                case _: IllegalArgumentException => trace("setter arguments with incompatible paramenter type")
            }
        }
        return target
    }

    private def getScalaProperties(cls: Class[_]): List[ScalaPropertyDescriptor] = {
        var result: List[ScalaPropertyDescriptor] = List()
        //        val methods = cls.getDeclaredMethods
        val methods = ReflectionUtils.getAllDeclaredMethods(cls)

        val map = mutable.Map.empty[String, Method]
        var getters = List.empty[Method]
        // split methods to getters and setters
        for (method <- methods if isPublic(method)) {
            trace("method: " + method.getName)
            if (isSetter(method)) {
                map(method.getName) = method
            }
            if (isGetter(method)) {
                getters ::= method
            }
        }

        for (getter <- getters) {
            val setterName = getter.getName + SETTER_SUFFIX
            map get setterName match {
                case Some(m) => result ::= new ScalaPropertyDescriptor(getter, m)
                case None =>
            }
        }
        return result
    }

    private def isSetter(method: Method): Boolean = {
        return method.getName.endsWith(SETTER_SUFFIX)
    }

    private def isPublic(method: Method): Boolean = {
        return Modifier.isPublic(method.getModifiers)
    }

    private def isGetter(method: Method): Boolean = {
        return method.getParameterTypes.size == 0 && !isSetter(method)
    }
}