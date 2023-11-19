package com.example.core.navigation

import com.example.core.navigation.base.BaseGraph


sealed class Graph(graphRoute: String) : BaseGraph(graphRoute) {
    object Root : BaseGraph("ROOT_GRAPH")
    object Login : BaseGraph("LOGIN_GRAPH")
    object Primary : BaseGraph("PRIMARY_GRAPH")
    object Basket : BaseGraph("BASKET_GRAPH")
}
