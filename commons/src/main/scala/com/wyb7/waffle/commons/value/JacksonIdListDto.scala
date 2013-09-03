package com.wyb7.waffle.commons.value

import collection.JavaConverters._
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility

/**
 * Author: Wang Yibin
 * Date: 11-8-23
 * Time: 下午9:41
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class JacksonIdListDto extends java.util.ArrayList[Number] {
    def values = this.asScala.map(_.longValue()).toList
}