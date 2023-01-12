package com.unciv.utilities.util


abstract class Node(
    var parent: Node?,
    var children: MutableList<Node>?
) {
    fun isRootNode() = parent == null
    fun isLeafNode() = children == null

    fun rootNode(): Node = rootNode(this)

    private tailrec fun rootNode(current: Node): Node {
        return if (current.isRootNode()) current
        else rootNode(current.parent!!)
    }
}

class RootNode(val treeName: String) : Node(null, mutableListOf())

class Category<C>(
    parent: Node,
    val category: C,
    children: MutableList<Node>
) : Node(parent, children) {
    fun parentNode(): Node? = parent
    fun childrenNodes(): List<Node> = children!!
}

class Item<T>(parent: Node,val data: T) : Node(parent, null) {
    fun parentNode(): Node = parent!!
}

