package com.bakirdev.pizzaorder.model

import androidx.annotation.DrawableRes

data class PizzaModel(var name: String, @DrawableRes var image: Int, var prix: Double)
