package com.wyb7.waffle.domain.entity

import org.hibernate.usertype.UserType
import java.lang.String
import java.sql.{PreparedStatement, ResultSet}
import java.io.Serializable
import org.hibernate.`type`.StandardBasicTypes
import java.math.{BigDecimal=>JBigDecimal}
import org.hibernate.engine.spi.SessionImplementor

/**
 * Author: Wang Yibin
 * Date: 11-4-13
 * Time: 下午3:18
 */

class ScalaBigDecimalUserType extends UserType {

    def replace(original: AnyRef, target: AnyRef, owner: AnyRef) = original

    def assemble(cached: Serializable, owner: AnyRef) = cached.asInstanceOf[AnyRef]

    def disassemble(value: AnyRef) = value.asInstanceOf[Serializable]

    def isMutable = false

    def deepCopy(value: AnyRef) = value

    def nullSafeSet(st: PreparedStatement, value: AnyRef, index: Int, session: SessionImplementor) = {
        if (value == null) {
            st.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType)
        } else {
            st.setBigDecimal(index, new JBigDecimal(value.toString))
        }
    }

    def nullSafeGet(rs: ResultSet, names: Array[String], session: SessionImplementor, owner: AnyRef): AnyRef = {
        // be careful the call sequence of rs.getBigDecimal, and rs.wasNull
        // u must call rs.getBigDecimal before rs.wasNull
        // the description from mysql is
        // "Reports whether the last column read had a value of SQL NULL. Note that you must first
        // call one of the getter methods on a column to try to read its value and then call the
        // method wasNull to see if the value read was SQL NULL. "
        val jDecimal = rs.getBigDecimal(names(0))
        if (rs.wasNull) return null
        return BigDecimal(jDecimal)
    }

    def hashCode(x: AnyRef) = x.hashCode

    def equals(x: AnyRef, y: AnyRef):Boolean = {
        if (x == y) return true
        if (x == null || y == null) return false
        return x.equals(y)
    }

    def returnedClass = classOf[BigDecimal]

    def sqlTypes:Array[Int] = Array(StandardBasicTypes.BIG_DECIMAL.sqlType)

}