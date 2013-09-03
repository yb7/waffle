package com.wyb7.waffle.commons.xls

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.commons.lang3.StringUtils
import org.joda.time.LocalDate
import org.apache.commons.lang3.math.NumberUtils
import com.wyb7.waffle.commons.exception.InvalidFormatException

/**
 * Author: Wang Yibin
 * Date: 11-9-15
 * Time: 下午7:02
 */

object HSSFCellHelper {
    implicit def hssfCellToStr(cell: HSSFCell): String = if (cell != null) trim(cell.toString) else null

    implicit def hssfCellToLong(cell:HSSFCell): Long = hssfCellToBigDecimal(cell).longValue()

    implicit def hssfCellToInt(cell:HSSFCell): Int = hssfCellToBigDecimal(cell).intValue()

    implicit def hssfCellToDate(cell: HSSFCell): java.util.Date = {
        if (cell != null) {
            try{
                cell.getDateCellValue
            } catch {
                case _: Throwable => throw new InvalidFormatException("%s行%s列, 值[%s],不是正常的日期格式".format(cell.getRowIndex + 1, cell.getColumnIndex + 1, cell.toString))
            }
        } else {
            null
        }
    }

    implicit def hssfCellToJodaLocalDate(cell: HSSFCell): LocalDate = {
        if (cell != null) {
            val tryDateCell = try {
                if (cell.getDateCellValue != null) {
                    new LocalDate(cell.getDateCellValue)
                } else {
                    null
                }
            } catch {
                case _: Throwable => null
            }
            val tryStringCell = try {
                if (StringUtils.isNotBlank(cell.getStringCellValue)) {
                    LocalDate.parse(cell.getStringCellValue)
                } else {
                    null
                }
            } catch {
                case _: Throwable => null
            }
            if (tryDateCell != null) tryDateCell else tryStringCell
        } else {
            null
        }
    }
    implicit def hssfCellToBigDecimal(cell: HSSFCell): BigDecimal = {
        import org.apache.poi.ss.usermodel.Cell._
        if (cell == null) {
            return BigDecimal("0")
        }
        val result = cell.getCellType match {
            case CELL_TYPE_STRING => {
                val str = cell.getStringCellValue
                if (StringUtils.isBlank(str)) {
                    BigDecimal("0")
                } else if (NumberUtils.isNumber(str)) {
                    BigDecimal(str)
                } else {
                    null
                }
            }
            case _ => try {
                BigDecimal(cell.getNumericCellValue)
            } catch {
                case _: Throwable => null
            }
        }
        if (result == null) {
            throw new InvalidFormatException("%s行%s列, 值[%s],不是正常的数字格式".format(cell.getRowIndex + 1, cell.getColumnIndex + 1, cell.toString))
        } else {
            result
        }
    }

    private def trim(str: String): String = StringUtils.trim(str)
}