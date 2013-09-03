package com.wyb7.waffle.commons.util

import com.wyb7.waffle.commons.exception.VersionConflictException


/**
 * Author: Wang Yibin
 * Date: 11-12-7
 * Time: 下午3:59
 */

object BizPreconditions {
    def checkVersionConflict(currentVersion: Int, systemVersion: Int) {
        if (currentVersion != systemVersion) {
            throw new VersionConflictException("版本冲突：当前对象已被修改请重新获取。 [当前版本%d， 系统版本%d]"
                    .format(currentVersion, systemVersion))
        }
    }
}