package com.wyb7.waffle.commons.exception

/**
 * Author: Wang Yibin
 * Date: 11-8-17
 * Time: 下午10:31
 */

class BizRuntimeException(message: String) extends RuntimeException(message)

class BizDbException(message: String) extends BizRuntimeException(message)

class UniqueFieldsConstraintViolatedException(message: String) extends BizDbException(message)

class UpdateBizStatusException(message: String) extends BizRuntimeException(message)

class ValidateFailureException(message: String) extends BizRuntimeException(message)

class VersionConflictException(message: String) extends BizRuntimeException(message)
//class BizExceptionToTextHtmlFormat(message: String) extends BizRuntimeException(message)

class InvalidFormatException(message: String) extends BizRuntimeException(message)