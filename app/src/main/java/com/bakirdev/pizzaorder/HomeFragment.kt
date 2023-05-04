package com.bakirdev.pizzaorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bakirdev.pizzaorder.databinding.FragmentHomeBinding
import com.bakirdev.pizzaorder.model.PizzaMenuViewModel
import com.bakirdev.pizzaorder.model.PizzaModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: PizzaMenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeFragment = this
    }

    fun startYourOrder() {
        if (sharedViewModel.menuList.value == null) {
            val menuList = mutableListOf<PizzaModel>()
            menuList.add(PizzaModel("Margherita", R.drawable.pizza, 600.00))
            menuList.add(PizzaModel("Diavola", R.drawable.pizza, 1000.00))
            menuList.add(PizzaModel("San Matteo", R.drawable.pizza, 4200.00))
            menuList.add(PizzaModel("Quattaro Formaggio", R.drawable.pizza, 2600.00))
            menuList.add(PizzaModel("Shrimp Pepperonico", R.drawable.pizza, 1300.00))
            menuList.add(PizzaModel("Beef Classico", R.drawable.pizza, 900.00))
            menuList.add(PizzaModel("Korean Spicy Chicken", R.drawable.pizza, 1200.00))
            menuList.add(PizzaModel("Smoked Beef", R.drawable.pizza, 6000.00))
            sharedViewModel.updateMenuList(menuList)
        }
        findNavController().navigate(R.id.action_homeFragment_to_pizzaMenu)
    }
}