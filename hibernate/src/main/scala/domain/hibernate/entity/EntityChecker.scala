package com.wyb7.waffle.domain.entity

import com.wyb7.waffle.commons.value.GenericActionResult
import com.wyb7.waffle.commons.value.GenericActionResult._
import domain.hibernate.util.HibernateSessionAware

/**
 * Author: Wang Yibin
 * Date: 11-3-8
 * Time: 上午10:37
 */
trait EntityChecker extends HibernateSessionAware {
    def checkVersion(obj:HasVersion, currentVersion:Int):GenericActionResult =  {
        if (obj.version.intValue() != currentVersion) {
            return failureResult("Illegal Version")
        } else {
            return successResult()
        }
    }
    def checkVersion(entityClass:Class[HasVersion], id:Long, currentVersion: Int):GenericActionResult = {
        val obj:HasVersion = session.load(entityClass, id).asInstanceOf[HasVersion]
        return checkVersion(obj, currentVersion)
    }
}
