package com.wyb7.waffle.domain.entity

import org.hibernate.usertype.UserType
import java.lang.String
import java.sql.{PreparedStatement, ResultSet}
import java.io.Serializable
import org.hibernate.`type`.StandardBasicTypes
import com.wyb7.waffle.commons.util.EnumerationUtil
import java.lang.reflect.ParameterizedType
import com.wyb7.waffle.commons.bean.util.ScalaObjectUtil
import org.hibernate.engine.spi.SessionImplementor

/**
 * Author: Wang Yibin
 * Date: 11-4-13
 * Time: 下午3:18
 */

abstract class StringEnumUserType[T] extends UserType {
    def enumClass:Class[_] =
        ScalaObjectUtil.classOfObject(getClass.getGenericSuperclass().asInstanceOf[ParameterizedType]
                .getActualTypeArguments()(0).asInstanceOf[Class[_]])
    

    def replace(original: AnyRef, target: AnyRef, owner: AnyRef) = original

    def assemble(cached: Serializable, owner: AnyRef) = cached.asInstanceOf[AnyRef]

    def disassemble(value: AnyRef) = value.asInstanceOf[Serializable]

    def isMutable = false

    def deepCopy(value: AnyRef) = value


    def nullSafeSet(st: PreparedStatement, value: AnyRef, index: Int, session: SessionImplementor) = {
        if (value == null) {
            st.setNull(index, StandardBasicTypes.STRING.sqlType)
        } else {
            st.setString(index, value.asInstanceOf[Enumeration#Value].toString)
        }
    }

    def nullSafeGet(rs: ResultSet, names: Array[String], session: SessionImplementor, owner: AnyRef): AnyRef = {
        val name = rs.getString(names(0))
        if (rs.wasNull) return null
        return EnumerationUtil.reflectEnumValue(enumClass, name)
    }

    def hashCode(x: AnyRef) = x.hashCode

    def equals(x: AnyRef, y: AnyRef):Boolean = {
        if (x == y) return true
        if (x == null || y == null) return false
        return x.equals(y)
    }

    def returnedClass = enumClass

    def sqlTypes:Array[Int] = Array(StandardBasicTypes.STRING.sqlType)

//    def setParameterValues(parameters: Properties) = {
//        val enumClassName = parameters.getProperty("enumClassName")
//        enumClass = Class.forName(enumClassName).asInstanceOf[Class[_<: Enumeration]]
//    }
}