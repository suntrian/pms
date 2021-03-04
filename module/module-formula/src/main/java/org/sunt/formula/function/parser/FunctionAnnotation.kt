package org.sunt.formula.function.parser

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
annotation class Description(val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Category(vararg val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Alias(vararg val value: String)

@Target(AnnotationTarget.FUNCTION)
annotation class Translate(
    val value: String = "",
    val translator: KClass<out FunctionTranslator> = EmptyTranslator::class,
    vararg val constructArg: String = []
)

@Target(AnnotationTarget.FUNCTION)
annotation class Overload

/**
 * 常量参数，不能是字段，函数等
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
annotation class Constant()

/**
 * 参数枚举值
 */
@Constant
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Option(vararg val value: String)

/**
 * 关键字参数
 */
@Constant
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Reserved(vararg val value: String)

@Retention(AnnotationRetention.SOURCE)
@Repeatable
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Suggest(val scope: String, val value: String = "")