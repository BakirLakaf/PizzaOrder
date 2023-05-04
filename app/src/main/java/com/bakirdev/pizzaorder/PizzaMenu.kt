package com.bakirdev.pizzaorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bakirdev.pizzaorder.adapter.PizzaMenuAdapter
import com.bakirdev.pizzaorder.databinding.FragmentPizzaMenuBinding
import com.bakirdev.pizzaorder.model.PizzaMenuViewModel
import com.bakirdev.pizzaorder.model.PizzaOrder
import com.bakirdev.pizzaorder.utils.PIZZA_IMAGE
import com.bakirdev.pizzaorder.utils.PIZZA_NAME
import com.bakirdev.pizzaorder.utils.PIZZA_PRICE

class PizzaMenu : Fragment() {

    private lateinit var binding: FragmentPizzaMenuBinding
    private val sharedViewModel: PizzaMenuViewModel by activityViewModels()
    private val orderViewModel: PizzaOrder by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPizzaMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            pizzaMenuFragment = this@PizzaMenu
            pizzaMenuViewModel = sharedViewModel
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val adapter = PizzaMenuAdapter(requireContext(), sharedViewModel.menuList.value!!) { pizzaModel ->
            val bundle = Bundle()
            bundle.putString(PIZZA_NAME, pizzaModel.name)
            bundle.putString(PIZZA_PRICE, pizzaModel.prix.toString())
            bundle.putInt(PIZZA_IMAGE, pizzaModel.image)
            findNavController().navigate(R.id.action_pizzaMenu_to_datePickup, bundle)

            orderViewModel.apply {
                updateImage(pizzaModel.image)
                updateName(pizzaModel.name)
                updatePrix(pizzaModel.prix)
            }
        }
        binding.pizzaMenuRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.pizzaMenuRecyclerView.adapter = adapter
    }
}