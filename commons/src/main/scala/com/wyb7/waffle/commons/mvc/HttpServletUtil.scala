package com.wyb7.waffle.commons.mvc

import javax.servlet.http.HttpServletResponse

/**
 * Author: Wang Yibin
 * Date: 11-8-24
 * Time: 上午10:20
 */

object HttpServletUtil {
    def setResponseFileName(response: HttpServletResponse, fileName: String) {
        response.addHeader("Content-Disposition", "inline; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"))
    }
}