package com.wyb7.waffle.commons.util


/**
 * Author: Wang Yibin
 * Date: 11-3-8
 * Time: 下午1:12
 */
object MathPredef {
    case class WrappedBigDecimal(bd: BigDecimal) {
        def ceilDivision(right: BigDecimal): Int = {
            val (intValue, remainder) = bd /% right
            if (remainder > 0) intValue.toInt + 1 else intValue.toInt
        }
    }

    implicit def bigDecimalToWrapped(bd: BigDecimal): WrappedBigDecimal = WrappedBigDecimal(bd)
}
