package com.wyb7.waffle.commons.spring

import org.springframework.web.servlet.view.document.AbstractExcelView
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}
import com.wyb7.waffle.commons.util.JTypePredef._
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import collection.JavaConverters._
import com.wyb7.waffle.commons.mvc.HttpServletUtil

/**
 * Author: Wang Yibin
 * Date: 11-8-24
 * Time: 上午10:20
 */

abstract class WaffleAbstractExcelView extends AbstractExcelView {
    def outputFilename(model: Map[String, Any]): String

    override def buildExcelDocument(model: JMap[String, Object], workbook: HSSFWorkbook, request: HttpServletRequest, response: HttpServletResponse) {
        val newModel = Map.empty[String, Any] ++ model.asScala
        buildDocument(newModel, workbook, request, response)
        HttpServletUtil.setResponseFileName(response, outputFilename(newModel) + ".xls")
    }

    def buildDocument(model: Map[String, Any], workbook: HSSFWorkbook, request: HttpServletRequest, response: HttpServletResponse) {

    }
}