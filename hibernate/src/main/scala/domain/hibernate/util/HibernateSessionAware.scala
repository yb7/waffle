package domain.hibernate.util

import org.hibernate.{SessionFactory, Session}


/**
 * Author: Wang Yibin
 * Date: 11-4-12
 * Time: 下午3:17
 */

trait HibernateSessionAware {
    @transient
    def sessionFactory: SessionFactory
    @transient
    def session: Session = sessionFactory.getCurrentSession
}