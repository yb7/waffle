package domain.hibernate.operation

import com.wyb7.waffle.domain.operation.HibernateEntityHelper

/**
 * Author: Wang Yibin
 * Date: 12-5-3
 * Time: 下午3:49
 */

trait Refresh extends HibernateEntityHelper {
    /**
     * Re-read the state of the given instance from the underlying database. It is
     * inadvisable to use this to implement long-running sessions that span many
     * business tasks. This method is, however, useful in certain special circumstances.
     * For example
     * <ul>
     * <li>where a database trigger alters the object state upon insert or update
     * <li>after executing direct SQL (eg. a mass update) in the same session
     * <li>after inserting a <tt>Blob</tt> or <tt>Clob</tt>
     * </ul>
     *
     */
    def refresh() {
        session.refresh(this)
    }

}
