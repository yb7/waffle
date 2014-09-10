package com.wyb7.waffle.commons.value

import scala.beans.BeanProperty

/**
 * User: bin
 * Date: 11-5-6
 * Time: 上午8:46
 */

class PaginationBase[T](
       @BeanProperty val total:Long, @BeanProperty val start:Long,
       @BeanProperty val size:Long, @BeanProperty val data:List[T],
       @BeanProperty val message:String = "", @BeanProperty val success:Boolean = true)