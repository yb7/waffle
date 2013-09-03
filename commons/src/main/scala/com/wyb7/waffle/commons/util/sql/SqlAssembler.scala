package com.wyb7.waffle.commons.util.sql

/**
 * Author: Wang Yibin
 * Date: 11-6-13
 * Time: 上午9:25
 */

class SqlAssembler {
    protected var whereAdded: Boolean = false

    def isWhereAdded = whereAdded

    protected var whereStatement = new StringBuilder

    protected var groupByAdded: Boolean = false

    def isGroupByAdded = groupByAdded

    protected var groupByStatement = new StringBuilder

    protected var selectStatement: String = _
    protected var fromStatement: String = _


    def select(s: String) = {
        selectStatement = s; this
    }

    def from(f: String) = {
        fromStatement = f; this
    }

    def appendWhereClause(whereClause: String) = {
        if (whereAdded) {
            whereStatement.append(" and ");
        } else {
            whereAdded = true
        }
        whereStatement.append(whereClause);
        this
    }

    def appendGroupBy(groupByClause: String) = {
        if (isGroupByAdded) {
            groupByStatement.append(" and ");
        } else {
            groupByAdded = true
        }
        groupByStatement.append(groupByClause);
        this
    }

    override def toString = {
        val str = (new StringBuilder)
                .append("select ").append(selectStatement)
                .append(" from ").append(fromStatement)
        if (isWhereAdded) {
            str.append(" where ").append(whereStatement)
        }
        if (isGroupByAdded) {
            str.append(" group by ").append(groupByStatement)
        }
        str.toString
    }
}