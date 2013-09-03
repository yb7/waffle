package com.wyb7.waffle.domain.operation

/**
 * Author: wangyibin
 * Date: 11-2-14
 * Time: 下午4:21
 */
trait FindById[T] extends HibernateEntityHelper{
    def findById(id:Long): Option[T] = {
        if (id <= 0) return None
        val domain = session.get(entityClass, id).asInstanceOf[T]
        return if (domain == null) None else Some(domain)
    }
}
