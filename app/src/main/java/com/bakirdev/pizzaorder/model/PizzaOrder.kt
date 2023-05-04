package com.bakirdev.pizzaorder.model

import androidx.lifecycle.ViewModel

class PizzaOrder : ViewModel() {

    private var _orderImage: Int = 0
    val orderImage: Int
        get() = _orderImage

    private var _orderName: String = ""
    val orderName: String
        get() = _orderName

    private var _orderPrix: Double = 0.00
    val orderPrix: Double
        get() = _orderPrix

    private var _orderTime: String = ""
    val orderTime: String
        get() = _orderTime

    fun updateImage(orderImage: Int) {
        _orderImage = orderImage
    }

    fun updateName(orderName: String) {
        _orderName = orderName
    }

    fun updateTime(orderTime: String) {
        _orderTime = orderTime
    }

    fun updatePrix(orderPrix: Double) {
        _orderPrix = orderPrix
    }

    fun clearOrder() {
        _orderImage = 0
        _orderName = ""
        _orderPrix = 0.0
        _orderTime = ""
    }
}