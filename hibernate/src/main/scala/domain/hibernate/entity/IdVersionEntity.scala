package com.wyb7.waffle.domain.entity

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

import javax.persistence._

/**
 * Author: Wang Yibin
 * Date: 11-3-7
 * Time: 下午2:49
 */
@MappedSuperclass
abstract class IdVersionEntity extends HasVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id : Long = _

    @Version
    @Column(name = "version")
    var version : Int = _

    
    override def hashCode():Int = {
        return new HashCodeBuilder().append(id).hashCode()
    }


    override def equals(obj:Any):Boolean =  {
        if (obj == null) {
            return false
        }
        if (this eq obj.asInstanceOf[AnyRef]) {
            return true
        }
        if (!obj.asInstanceOf[AnyRef].getClass.getName.equals(getClass.getName)) {
            return false
        }
        val rhs:IdVersionEntity = obj.asInstanceOf[IdVersionEntity]
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(id, rhs.id)
                .isEquals()
    }
}
