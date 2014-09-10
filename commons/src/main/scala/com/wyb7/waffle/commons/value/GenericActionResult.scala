package com.wyb7.waffle.commons.value

//import net.liftweb.json.{NoTypeHints, Serialization}
import scala.beans.BeanProperty

/**
 * generic result type, with message and data.
 * The toString method produce json string.
 *
 * The field data should be a case class.
 * Otherwise the toString method cannot produce the json representation correct
 */
class GenericActionResult (
          @BeanProperty val success:Boolean, @BeanProperty val message:String,
          @BeanProperty val data:Any) {
//	override def toString():String = {
//        implicit val formats = Serialization.formats(NoTypeHints)
//		return Serialization.write(this)
//	}
}
object GenericActionResult {
    implicit def genericActionResultToBoolean(result: GenericActionResult): Boolean = if (result == null) false else result.success
    
    def successResult(message:String = "", data:Any = null) = new GenericActionResult(true, message, data)
    def failureResult(message:String = "", data:Any = null) = new GenericActionResult(false, message, data)
}
