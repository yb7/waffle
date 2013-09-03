package com.wyb7.waffle.commons.value

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility


/**
 * Author: Wang Yibin
 * Date: 11-7-14
 * Time: 上午11:23
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class ExtJsChartItem(val name: String, val percent: BigDecimal)