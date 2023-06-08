package com.example.core.navigation

import com.example.core.navigation.graphs.BaseGraph


sealed class Graph(val graphRoute: String) : BaseGraph(graphRoute) {
    object Root : BaseGraph("ROOT_GRAPH")
    object Login : BaseGraph("LOGIN_GRAPH")
    object Main : BaseGraph("MAIN_GRAPH")
}
