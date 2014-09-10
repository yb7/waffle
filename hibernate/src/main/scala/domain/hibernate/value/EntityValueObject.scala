package com.wyb7.waffle.commons.value

import scala.beans.BeanProperty

/**
 * Author: Wang Yibin
 * Date: 11-5-9
 * Time: 下午8:06
 */

abstract class EntityValueObject {
    @BeanProperty var id:Long = _
    @BeanProperty var version:Int = _
}

trait IdVersionTrait {
    @BeanProperty var id:Long = _
    @BeanProperty var version:Int = _
}