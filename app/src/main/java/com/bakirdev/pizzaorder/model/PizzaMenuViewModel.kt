package com.bakirdev.pizzaorder.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PizzaMenuViewModel : ViewModel() {

    private val _menuList = MutableLiveData<List<PizzaModel>>()
    val menuList: LiveData<List<PizzaModel>>
        get() = _menuList

    fun updateMenuList(menuList: List<PizzaModel>) {
        _menuList.postValue(menuList)
    }

}