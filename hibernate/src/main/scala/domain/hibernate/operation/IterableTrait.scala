package com.wyb7.waffle.domain.operation

/**
 * User: bin
 * Date: 11-5-5
 * Time: 下午3:26
 */

import com.wyb7.waffle.commons.util.JTypePredef._
import org.hibernate.Criteria
import collection.mutable.Buffer
import scala.collection.JavaConverters._

trait IterableTrait[T] extends HibernateEntityHelper {
    def iterate(firstResult:Int = 0, maxResults:Int = 0):Iterable[T] = {
        var criteria:Criteria = null
        criteria = session.createCriteria(entityClass)
        criteria.setFirstResult(firstResult)
        if (maxResults > 0) {
            criteria.setMaxResults(maxResults)
        }
        return criteria.list.asInstanceOf[JList[T]].asScala.asInstanceOf[Iterable[T]]
    }
}