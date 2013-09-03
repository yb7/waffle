package com.wyb7.waffle.domain.operation

trait Count extends HibernateEntityHelper {
	def count = session.createQuery(
            "select COALESCE(count(*), 0) from " + entityClass.getName())
            .uniqueResult().asInstanceOf[Long]
	
}
