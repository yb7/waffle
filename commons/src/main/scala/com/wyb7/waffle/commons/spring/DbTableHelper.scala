package com.wyb7.waffle.commons.spring

import javax.sql.DataSource
import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.JdbcTemplate
import collection.JavaConverters._

/**
 * Author: Wang Yibin
 * Date: 11-9-16
 * Time: 下午12:57
 */

class DbTableHelper(dataSource: DataSource) {
    def this(springContext: ApplicationContext) = this(springContext.getBean(classOf[DataSource]))

    def listTables: List[String] = {
        new JdbcTemplate(dataSource).queryForList("show tables", classOf[String])
                .asScala.toList.asInstanceOf[List[String]]
    }
}