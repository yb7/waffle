package com.wyb7.waffle.domain.query

import org.hibernate.Query
import com.wyb7.waffle.commons.util.JTypePredef._
import collection.JavaConverters._
import domain.hibernate.util.HibernateSessionAware

/**
 * User: bin
 * Date: 11-5-3
 * Time: 下午2:43
 */

abstract class AbstractQuery[T] extends HibernateSessionAware {
    protected var queryMode:QueryMode.Value = _
    protected var isWhereAdded:Boolean = false

    protected var page:Page = null
    
    def hql:String
    def applyParameters(query:Query):Unit

    def count:Long = {
        queryMode = QueryMode.Count
        return execute().asInstanceOf[Long]
    }

    def singleResult:T = {
        queryMode = QueryMode.SingleResult
        return execute().asInstanceOf[T]
    }

    def list:List[T] = {
        queryMode = QueryMode.List
        execute().asInstanceOf[JList[T]].asScala.toList
    }

    def list(firstResult:Int, pageSize:Int):List[T] = {
        queryMode = QueryMode.Pagination
        page = new Page(firstResult, pageSize)
        execute().asInstanceOf[JList[T]].asScala.toList
    }

    protected def execute():Any = {
        var result:Any = null
        val query:Query = session.createQuery(hql)
        applyParameters(query)
        if (queryMode == QueryMode.Count || queryMode == QueryMode.SingleResult) {
            result = query.uniqueResult
        } else {
            if (queryMode == QueryMode.Pagination) {
                query.setFirstResult(page.firstResult);
                query.setMaxResults(page.maxResults);
            }
            result = query.list
        }
        resetQuery
        return result

    }
    protected def resetQuery() = isWhereAdded = false

    protected def appendWhereClause(whereClause:String, hql:StringBuilder):Unit = {
        if (isWhereAdded) {
            hql.append(" and ");
        } else {
            isWhereAdded = true;
            hql.append("where ");
        }
        hql.append(whereClause);
    }
}
object QueryMode extends Enumeration {
    val List, Pagination, SingleResult, Count = Value
}

class Page(val firstResult:Int, val maxResults:Int)