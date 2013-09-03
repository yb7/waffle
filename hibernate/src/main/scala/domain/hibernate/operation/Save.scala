package com.wyb7.waffle.domain.operation

/**
 * Author: wangyibin
 * Date: 11-2-14
 * Time: 下午4:08
 */
trait Save extends HibernateEntityHelper {
	def save():Long = {
        checkFieldsUnique
        checkCombinedFieldsUnique
        val id = session.save(this).asInstanceOf[Long]
        // flush session to check whether this operation will violate ConstraintViolationException
        session.flush
        return id
    }
}
