package com.example.core.navigation

import com.example.core.navigation.base.BaseGraph


sealed class Graph(graphRoute: String) : BaseGraph(graphRoute) {
    object Root : BaseGraph("ROOT_GRAPH")
    object Login : BaseGraph("LOGIN_GRAPH")
    object PrimaryScreen : BaseGraph("PRIMARY_GRAPH")
    object Basket : BaseGraph("BASKET_GRAPH")
    object Products : BaseGraph("PRODUCTS_GRAPH")
    object Workers : BaseGraph("WORKERS_GRAPH")
    object More : BaseGraph("MORE_GRAPH")
}
