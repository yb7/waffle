package com.wyb7.waffle.commons.spring

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

/**
 * Author: Wang Yibin
 * Date: 11-7-4
 * Time: 下午3:29
 */

object SpringSecurityUtil {
    def userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal.asInstanceOf[UserDetails]
}