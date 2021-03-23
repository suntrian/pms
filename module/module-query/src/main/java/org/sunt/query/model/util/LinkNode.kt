package org.sunt.query.model.util

interface LinkNode<T: LinkNode<T>> {
    var next: T?
    var prev: T?
}