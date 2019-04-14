package ru.gruzdev.livedatadaggerexample

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.reactivex.Observable
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.main_view.view.*
import ru.gruzdev.livedatadaggerexample.databinding.MainViewBinding
import ru.gruzdev.livedatadaggerexample.models.Person

class PersonView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    val activity: FragmentActivity = context as FragmentActivity
    val PHONE_PATERN = """^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}${'$'}"""
    val EMAIL_PATERN = """^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"""

    init {
        val binding = DataBindingUtil.inflate<MainViewBinding>(
            LayoutInflater.from(context), R.layout.main_view, this, true
        )
        val viewModel = ViewModelProviders.of(activity).get(PersonViewModel::class.java)
        viewModel.observerData(activity, Observer {
            binding.person = it
        })

        val nameObserver = addObserver(editText)
        val phoneObserver = addObserver(editText2)
        val emailObserver = addObserver(editText3)
        val lambdaForFields = Function3 { s1: String, s2: String, s3: String ->
            s1.isNotBlank()
                    &&
                    validete(s2, PHONE_PATERN)
                    &&
                    validete(s3, EMAIL_PATERN)
        }

        button.setOnClickListener {

            viewModel.savePerson(
                Person(
                    editText.text.toString(),
                    editText2.text.toString(),
                    editText3.text.toString()
                )
            )
            editText.text?.clear()
            editText2.text?.clear()
            editText3.text?.clear()

        }

        Observable.combineLatest(nameObserver, phoneObserver, emailObserver, lambdaForFields).subscribe {
            button.isEnabled = it
        }
    }

    fun validete(str: String, patern: String): Boolean =
        Regex(patern).matches(str)


    fun addObserver(
        editTextView: EditText
    ): Observable<String> {
        val observer = Observable.create<String> {
            editTextView.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    it.onNext(s.toString())
                    println(it)
                }
            }

            )
        }

        return observer
    }


}