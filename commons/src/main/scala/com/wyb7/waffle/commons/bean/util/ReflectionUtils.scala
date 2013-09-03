package com.wyb7.waffle.commons.bean.util

import java.lang.annotation.Annotation
import java.lang.reflect.{ParameterizedType, Field}

/**
 * A collection of scala extensions to the Sting JVMUtils.
 */
object ReflectionUtils {

    /**
     * Returns true if field has the annotation.
     * @param field Field to check.
     * @param annotation Class of the annotation to look for.
     * @return true if field has the annotation.
     */
    def hasAnnotation(field: Field, annotation: Class[_ <: Annotation]) = field.getAnnotation(annotation) != null

    /**
     * Returns true if clazz or one of its superclasses has the annotation.
     * @param clazz Class to check.
     * @param annotation Class of the annotation to look for.
     * @return true if field has the annotation.
     */
    def hasAnnotation(clazz: Class[_], annotation: Class[_ <: Annotation]) = {
        var foundAnnotation = false
        while (!foundAnnotation && clazz != null)
            foundAnnotation = (clazz.getAnnotation(annotation) != null)
        foundAnnotation
    }

    /**
     * Gets the annotation or throws an exception if the annotation is not found.
     * @param field Field to check.
     * @param annotation Class of the annotation to look for.
     * @return The annotation.
     */
    def getAnnotation[T <: Annotation](field: Field, annotation: Class[T]): T = {
        field.getAnnotation(annotation) match {
            case null =>
                throw new RuntimeException("Field %s is missing annotation %s".format(field, annotation))
            case fieldAnnotation => fieldAnnotation.asInstanceOf[T]
        }
    }

    /**
     * Gets the annotation or throws an exception if the annotation is not found.
     * @param clazz Class to check.
     * @param annotation Class of the annotation to look for.
     * @return The annotation.
     */
    def getAnnotation[T <: Annotation](clazz: Class[_], annotation: Class[T]): T = {
        var result: T = null.asInstanceOf[T]
        while (result == null && clazz != null)
            result = clazz.getAnnotation(annotation)
        if (result == null)
            throw new RuntimeException("Class %s is missing annotation %s".format(clazz, annotation))
        result
    }

    /**
     * Returns all the declared fields on a class in order of sub type to super type.
     * @param clazz Base class to start looking for fields.
     * @return List[Field] found on the class and all super classes.
     */
    def getAllFields(clazz: Class[_]) = getAllTypes(clazz).map(_.getDeclaredFields).flatMap(_.toList)

    def getAllDeclaredMethods(clazz: Class[_]) = getAllTypes(clazz).map(_.getDeclaredMethods).flatMap(_.toList)

    /**
     * Gets all the types on a class in order of sub type to super type.
     * @param clazz Base class.
     * @return List[Class] including the class and all super classes.
     */
    def getAllTypes(clazz: Class[_]) = {
        var types = List.empty[Class[_]]
        var c = clazz
        while (c != null) {
            types :+= c
            c = c.getSuperclass
        }
        types
    }
}
