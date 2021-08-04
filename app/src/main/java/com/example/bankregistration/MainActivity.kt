package com.example.bankregistration

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.bankregistration.databinding.ActivityMainBinding
import com.example.model.BankDetails
import com.example.viewmodel.BankDetailViewModel

class MainActivity : AppCompatActivity(),android.text.TextWatcher {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val bankDetailList: BankDetails = BankDetails()
    var panNumberText:String ?= null
    var errorMessage :String? = null
    var bankDetailViewModel: BankDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bankDetailViewModel=  ViewModelProvider(this).get(BankDetailViewModel::class.java)

        val panDetailTextView = getString(R.string.providing_pan)
        val spannable: Spannable = SpannableString(panDetailTextView)
        val startIndex = panDetailTextView.length - 9
        val endIndex = panDetailTextView.length

        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.purple_500)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.panDetail.text = spannable
        setupObservers()
        binding.date.onFocusChangeListener = myEditTextFocus
        binding.month.onFocusChangeListener = myEditTextFocus
        binding.year.onFocusChangeListener = myEditTextFocus
    }

    override fun onStart() {
        super.onStart()
        binding.panNumberEdTv.setOnClickListener {
            val panvalue =binding.panNumberEdTv.text.toString()
            bankDetailViewModel?.panCardDetail?.observe(this, { panNumberText = it })
            if(panvalue.equals(panNumberText, ignoreCase = true)){
                binding.panNumberEdTv.error = null
            }else{
                setError(getString(R.string.providing_pan))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.idontPanTv.setOnClickListener {
            finish()
        }

    }
    private fun validatePanCard():Boolean{
        val panvalue =binding.panNumberEdTv.text.toString()
       if((panvalue.equals(panNumberText, ignoreCase = true) )){
           binding.panNumberEdTv.error = null
           binding.next.isEnabled = true
           return true
        }else{
            setError(getString(R.string.providing_pan))
           binding.next.isEnabled = false
        }
        return false
    }

    fun validateBithday():Boolean{
        if(binding.date.text.isNullOrEmpty() && binding.month.text.isNullOrEmpty() && binding.year.text.isNullOrEmpty()){
            binding.next.isEnabled = true
            return  false
        }
        binding.next.isEnabled = true
        return true
    }

    private fun setupObservers() {
        bankDetailViewModel?.panCardDetail?.observe(this, { panNumberText = it })
    }

    fun setError(error: String?){
        this.errorMessage = errorMessage
    }

    fun nextButtonClickEvent(view: View) {
        //todo note : plz check pan number as "Aydy123" which we will get from viewholder
        if(validatePanCard() && validateBithday()){
            Toast.makeText(this, getString(R.string.next_button_msg), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    //todo another method to take data from local assert file . Assert file added with name "books.json"
//    fun loadAssets():String? {
//        try {
//            val jsonStr = loadStringFromAsset(this, "books.json")
//            val jsonObj = JSONObject(jsonStr)
//            val jsonArray = jsonObj.getJSONArray("bankDetail")
//
//            val len = jsonArray.length() - 1
//
//            for (obj in 0..len) {
//                val jsonFinalObj = jsonArray.getJSONObject(obj)
//                val panNumber = jsonFinalObj.getString("panNumber")
//                panNumberText = panNumber
//                return panNumber
//            }
//        } catch (e: IOException) {
//            Log.d(TAG, "addItemsFromJSON: ", e)
//        }
//        return null
//    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        TODO("Not yet implemented")
    }

    override fun afterTextChanged(s: Editable?) {
        validatePanCard()
    }

    private val myEditTextFocus = OnFocusChangeListener { view, gainFocus ->
        //onFocus
        if (gainFocus) {
            //set the text
            val dateText = binding.date.text
            binding.date.text = dateText

            val monthText = binding.date.text
            binding.date.text = monthText

            val yearText = binding.date.text
            binding.date.text = yearText

            (view as EditText).error = null



        } else {
            //clear the text
            var getText:String = (view as EditText).text.toString()
            if(getText.isNullOrEmpty()){
                (view as EditText).error = ""
                binding.next.isEnabled = false
            }else{
                binding.next.isEnabled = true
            }
        }
    }

}
