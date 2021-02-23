package org.sunt.formula.function.parser

@Target(AnnotationTarget.FUNCTION)
annotation class Description(val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Category(vararg val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Alias(vararg val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Translate(val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Overload

