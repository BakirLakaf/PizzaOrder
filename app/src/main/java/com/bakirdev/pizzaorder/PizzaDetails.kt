package com.bakirdev.pizzaorder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bakirdev.pizzaorder.databinding.FragmentPizzaDetailsBinding
import com.bakirdev.pizzaorder.model.PizzaOrder
import com.bakirdev.pizzaorder.utils.PIZZA_IMAGE
import com.bakirdev.pizzaorder.utils.PIZZA_NAME
import com.bakirdev.pizzaorder.utils.PIZZA_PRICE
import com.bumptech.glide.Glide


class PizzaDetails : Fragment(), CompoundButton.OnCheckedChangeListener {

    private lateinit var binding: FragmentPizzaDetailsBinding
    private val orderViewModel: PizzaOrder by activityViewModels()
    private val radioButtonsList = ArrayList<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPizzaDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pizzaName = arguments?.getString(PIZZA_NAME, "")
        val pizzaPrice = arguments?.getString(PIZZA_PRICE, "")
        val pizzaImage = arguments?.getInt(PIZZA_IMAGE)
        binding.apply {
            pizzaDetailsFragment = this@PizzaDetails
            this.pizzaName = pizzaName
            this.pizzaPrice = pizzaPrice
            Glide.with(this@PizzaDetails).load(pizzaImage).into(this.pizzaDetailsImage)

            one.setOnCheckedChangeListener(this@PizzaDetails)
            two.setOnCheckedChangeListener(this@PizzaDetails)
            three.setOnCheckedChangeListener(this@PizzaDetails)
            four.setOnCheckedChangeListener(this@PizzaDetails)
            five.setOnCheckedChangeListener(this@PizzaDetails)
            six.setOnCheckedChangeListener(this@PizzaDetails)
            seven.setOnCheckedChangeListener(this@PizzaDetails)
            eight.setOnCheckedChangeListener(this@PizzaDetails)
        }

        setUpListOfRadioButtons()
    }

    private fun setUpListOfRadioButtons() {
        radioButtonsList.add(R.id.one)
        radioButtonsList.add(R.id.two)
        radioButtonsList.add(R.id.three)
        radioButtonsList.add(R.id.four)
        radioButtonsList.add(R.id.five)
        radioButtonsList.add(R.id.six)
        radioButtonsList.add(R.id.seven)
        radioButtonsList.add(R.id.eight)
    }


    private fun setQuantity(quantity: Int) {
        binding.pizzaQuantity.text = quantity.toString()
    }

    private fun getQuantity(): Int {
        return binding.pizzaQuantity.text.toString().toInt()
    }

    fun plusQuantity() {
        setQuantity(getQuantity() + 1)
    }

    fun minusQuantity() {
        if (getQuantity() != 1) {
            setQuantity(getQuantity() - 1)
        }
    }

    fun confirmOrder() {
        val textOrder = getString(
            R.string.order_confirmation,
            orderViewModel.orderName,
            orderViewModel.orderPrix.toInt(),
            orderViewModel.orderTime
        )
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TITLE, "Your order has been confirmed, check details")
        intent.putExtra(Intent.EXTRA_TEXT, textOrder)
        intent.putExtra(Intent.EXTRA_EMAIL, "bakir.abou.lakaf@gmail.com")
        startActivity(intent)
    }

    private fun setUpRadioButtons(itemId: Int) {
        val clickedRadioButton = view?.findViewById(itemId) as RadioButton

        radioButtonsList.forEach { id ->
            if (itemId != id) {
                val radioButton = view?.findViewById(id) as RadioButton
                radioButton.isChecked = false
            }
        }

        val time = clickedRadioButton.text.toString()
        orderViewModel.updateTime(time)
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (isChecked) setUpRadioButtons(buttonView.id)
    }

}