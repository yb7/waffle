package com.wyb7.waffle.domain.entity

import org.hibernate.cfg.ImprovedNamingStrategy
import org.hibernate.AssertionFailure
import org.hibernate.internal.util.StringHelper

/**
 * Author: Wang Yibin
 * Date: 11-6-9
 * Time: 上午11:17
 */

class UnderscoreNamingStrategy extends ImprovedNamingStrategy {
    override def foreignKeyColumnName(propertyName: String, propertyEntityName: String,
                  propertyTableName: String, referencedColumnName: String) = {
        var header: String = if (propertyName != null) StringHelper.unqualify(propertyName + "_" + referencedColumnName)
                            else propertyTableName
        if (header == null) throw new AssertionFailure("NamingStrategy not properly filled")
        columnName(header)
    }
}