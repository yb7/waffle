package com.wyb7.waffle.domain.operation

import org.hibernate.Criteria
import org.apache.commons.lang3.reflect.MethodUtils
import org.hibernate.criterion.{Projections, Criterion}
import domain.hibernate.util.HibernateSessionAware
import com.wyb7.waffle.commons.exception.UniqueFieldsConstraintViolatedException
import com.wyb7.waffle.commons.util.JTypePredef._

/**
 * Author: Wang Yibin
 * Date: 11-4-12
 * Time: 下午3:17
 */

trait HibernateEntityHelper extends HibernateSessionAware {

    /**
     * 该方法获取实例的类
     *
     * 简单的通过this.getClass是无法得到entity的class的。
     * 因为对于object对象，他的class带有$后缀。
     */
    @transient
    def entityClass:Class[_] = {
        var className = this.getClass.getName
        if (className.endsWith("$")) {
            className = className.substring(0, className.length - 1)
        }
        return Class.forName(className).asInstanceOf[Class[_]]
    }

    protected def findUniqueByCriteria(criterion:Criterion*):Any = {
        return prepareCriteria(entityClass, criterion:_*).uniqueResult
    }
    protected def findByCriteria(criterion:Criterion*): JList[_] = {
        return prepareCriteria(entityClass, criterion:_*).list
    }
    private def prepareCriteria(clazz: Class[_], criterion:Criterion*):Criteria = {
        val crit:Criteria = session.createCriteria(clazz)
        criterion.foreach(crit.add(_))
        return crit
    }

    protected def isFieldsUniqueForOtherEntity(fields: String*): Boolean = {
        import org.hibernate.criterion.Restrictions.{eq => eqs, or, not, idEq}
        val uniqueCriteria = fields.map(field => eqs(field, MethodUtils.invokeMethod(this, field)))
                                    .toIterable.reduceLeft(or)
        val id = MethodUtils.invokeMethod(this, "id")
        val result = session.createCriteria(entityClass).add(uniqueCriteria)
                        .add(not(idEq(id))).setProjection(Projections.rowCount())
                .uniqueResult()
        val rowCount = result.asInstanceOf[Long]
        return rowCount == 0
    }
    /**
     * fieldNameMap: fields name -> display name
     */
    protected def checkFieldsUnique {
        if (uniqueFields == null) {
            return
        }
        import org.hibernate.criterion.Restrictions.{eq => eqs, or, not, idEq}
        val uniqueCriteria = uniqueFields.keys.map(field => eqs(field.name, MethodUtils.invokeMethod(this, field.name)))
                                    .toIterable.reduceLeft(or)
        val id = MethodUtils.invokeMethod(this, "id")
        val result = session.createCriteria(entityClass).add(uniqueCriteria)
                        .add(not(idEq(id))).setProjection(Projections.rowCount())
                .uniqueResult()
        if (result.asInstanceOf[Long] > 0) {
            val nameValueList = for ((field, name) <- uniqueFields) yield {
                name + "[" + MethodUtils.invokeMethod(this, field.name) + "]"
            }
            val errorMsg = nameValueList.mkString(", ") + "必须符合唯一性约束"
            throw new UniqueFieldsConstraintViolatedException(errorMsg)
        }
    }

    protected def checkCombinedFieldsUnique {
        if (uniqueCombinedFields == null) {
            return
        }
        import org.hibernate.criterion.Restrictions.{eq => eqs, and, not, idEq}
        val uniqueCriteria = uniqueCombinedFields.keys.map(field => eqs(field.name, MethodUtils.invokeMethod(this, field.name)))
                                                            .toIterable.reduceLeft(and)
        val id = MethodUtils.invokeMethod(this, "id")
        val result = session.createCriteria(entityClass).add(uniqueCriteria)
                     .add(not(idEq(id))).setProjection(Projections.rowCount())
                     .uniqueResult()
        if (result.asInstanceOf[Long] > 0) {
            import com.wyb7.waffle.commons.exception.UniqueFieldsConstraintViolatedException

            val nameValueList = for ((field, name) <- uniqueCombinedFields) yield {
                    name + "[" + MethodUtils.invokeMethod(this, field.name) + "]"
            }
            val errorMsg = nameValueList.mkString(", ") + "的组合必须符合唯一性约束"
            throw new UniqueFieldsConstraintViolatedException(errorMsg)
        }
    }

    def uniqueFields: Map[Symbol, String] = null
    def uniqueCombinedFields: Map[Symbol, String] = null
}