package com.wyb7.waffle.domain.operation

/**
 * Author: wangyibin
 * Date: 11-2-14
 * Time: 下午4:08
 */
trait Update extends HibernateEntityHelper{
    def update(){
        checkFieldsUnique
        checkCombinedFieldsUnique
        session.merge(this)
        session.flush()
    }
}
