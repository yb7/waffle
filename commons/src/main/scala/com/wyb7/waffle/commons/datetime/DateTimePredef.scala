package com.wyb7.waffle.commons.datetime

import org.apache.commons.lang3.time.DateUtils
import java.text.SimpleDateFormat
import org.apache.commons.lang3.StringUtils
import java.util.Calendar
import org.joda.time.{LocalTime, LocalDateTime, LocalDate}
import com.wyb7.waffle.commons.primitive.PrimitivePredef._

/**
 * Author: Wang Yibin
 * Date: 12-3-26
 * Time: 下午8:21
 */

object DateTimePredef {
    private val standardDateFormat = new SimpleDateFormat("yyyy-MM-dd")

    private val dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    case class WrappedJodaLocalDate(localDate: LocalDate) {
        def daysBetween(anotherDate: LocalDate): Int = {
            val times = anotherDate.toDate.getTime - this.localDate.toDate.getTime

            val days = math.ceil(times.abs / DateUtils.MILLIS_PER_DAY).toInt
            if (days < 0) 0 else days
        }

        def toLocalDateTimeOfStart: LocalDateTime = {
            localDate.toLocalDateTime(LocalTime.fromMillisOfDay(0))
        }
        def toLocalDateTimeOfEnd: LocalDateTime = {
            localDate.toLocalDateTime(LocalTime.fromMillisOfDay(1000*60*60*24-1))
        }
    }

    case class WrappedUtilDate(date: java.util.Date) {
        def toDateOnlyString: String = if (date == null) "" else standardDateFormat.format(date)

        def toDateTimeString: String = if (date == null) "" else dateTimeFormat.format(date)

        def startOfDay = DateUtils.truncate(date, Calendar.DATE)

        def endOfDay = new java.util.Date(DateUtils.addDays(startOfDay, 1).getTime -1)
    }

    case class WrappedStringForDate(str: String) {
        def toLocalDateTime: Option[LocalDateTime] = str.notBlankOption.map(_.replace(" ", "T")).map(LocalDateTime.parse(_))

        def toLocalDate: Option[LocalDate] = toLocalDateTime.map(_.toLocalDate)

        def toUtilDate: Option[java.util.Date] = toLocalDateTime.map(_.toDate)

        def toSqlDate: Option[java.sql.Date] = toUtilDate.map(t => new java.sql.Date(t.getTime))

//        def toLocalDateTime: org.joda.time.LocalDateTime = if (StringUtils.isBlank(str)) null else org.joda.time.LocalDateTime.parse(str)




//        def toJodaDateTime: org.joda.time.DateTime = if (StringUtils.isBlank(str)) null else {
//          try{
//              new org.joda.time.DateTime(dateTimeFormat.parse(str).getTime)
//          } catch {
//              case ex: java.text.ParseException => new org.joda.time.DateTime(standardDateFormat.parse(str).getTime)
//          }
////            org.joda.time.DateTime.parse(str)
//        }

//        def toJodaLocalDate: LocalDate = if (StringUtils.isBlank(str)) null else {
//            LocalDate.parse(str.split("T")(0))
//        }
    }

    case class TimePair(date: String, time: String, displayName: String) {
        private def toTimeString: String = {
            if (StringUtils.isNotBlank(date)) {
                if (StringUtils.isNotBlank(time)) {
                    List(date, time).mkString("T")
                } else {
                    throw new RuntimeException("[%s] 填写了日期，但没填写时间".format(displayName))
                }

            } else {
                if (StringUtils.isNotBlank(time)) {
                    throw new RuntimeException("[%s] 填写了时间，但没填写日期".format(displayName))
                } else {
                    null
                }

            }
        }

        def localDateTime = WrappedStringForDate(toTimeString).toLocalDateTime
    }

    class WrappedLocalDateTime(localDateTime: LocalDateTime) {
        def splitNullToEmpty: (String, String) = {
            (datePart, timePart)
        }
        def datePart: String = {
            notNull(localDateTime.toString("yyyy-MM-dd"))
        }
        def timePart: String = {
            notNull(localDateTime.toString("HH:mm:ss"))
        }

        private def notNull(f: => String): String = {
            if (localDateTime != null) { f} else { ""}
        }

    }

    implicit def localDateTimeToWrapped(localDateTime: LocalDateTime) = new WrappedLocalDateTime(localDateTime)

    implicit def localDateToWrapped(localDate: LocalDate) = WrappedJodaLocalDate(localDate)

    implicit def utilDateToWrapped(date: java.util.Date) = WrappedUtilDate(date)

    implicit def stringWrappedForDate(str: String) = WrappedStringForDate(str)
}


