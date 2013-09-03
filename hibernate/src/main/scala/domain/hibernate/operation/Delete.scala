package com.wyb7.waffle.domain.operation

/**
 * Author: wangyibin
 * Date: 11-2-14
 * Time: 下午4:10
 */
trait Delete extends HibernateEntityHelper{
	def delete {
        session.delete(this)
        // flush session to check whether this operation will violate ConstraintViolationException
        session.flush
    }
}
