package com.example.calculator_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private lateinit var expression: TextView
    private lateinit var result: TextView
    private lateinit var clear: Button
    private lateinit var backSpace: Button
    private lateinit var percent: Button
    private lateinit var divide: Button
    private lateinit var multiply: Button
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var equal: Button
    private lateinit var dot: Button
    private lateinit var zero: Button
    private lateinit var doubleZero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button

    private lateinit var mplus: Button
    private lateinit var mminus: Button
    private lateinit var mr: Button
    private lateinit var mc: Button
    var eror = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        clear = findViewById(R.id.clear)
        backSpace = findViewById(R.id.backSpace)
        percent = findViewById(R.id.percent)
        divide = findViewById(R.id.divide)
        multiply = findViewById(R.id.multiply)
        add = findViewById(R.id.add)
        subtract = findViewById(R.id.subtract)
        equal = findViewById(R.id.equal)
        dot = findViewById(R.id.dot)
        zero = findViewById(R.id.zero)
        doubleZero = findViewById(R.id.doubleZero)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)

        mplus = findViewById(R.id.m_plus)
        mminus = findViewById(R.id.m_minus)
        mr = findViewById(R.id.m_r)
        mc = findViewById(R.id.m_c)

        expression.movementMethod = ScrollingMovementMethod()
        expression.isActivated = true
        expression.isPressed = true

        var str: String
        var memory: Double = 0.0
        val erorResult = false

        clear.setOnClickListener {
            expressionText("0")
            expression.textSize = 60F
            result.textSize = 30F
            resultText()
            eror = false
        }
        backSpace.setOnClickListener {
            if (expression.text.toString().isNotEmpty()){
                val lastIndex = expression.text.toString().lastIndex
                str = expression.text.toString().substring(0, lastIndex)
                expressionText(str)
                resultText()
                Log.d(TAG, "нажато backspace")
                eror = false
            }
        }
        percent.setOnClickListener {
            if (expression.text.toString().endsWith("%")|| expression.text.toString().endsWith("/")||expression.text.toString().endsWith("*")||expression.text.toString().endsWith("+")||expression.text.toString().endsWith("-")||expression.text.toString().endsWith(".")){
                str = expression.text.toString()
                expressionText(str)
                Log.d(TAG, "нажато percent")

            } else{
                str = expression.text.toString() + "%"
                expressionText(str)
                Log.d(TAG, "нажато percent")

            }
        }
        divide.setOnClickListener {
            if (expression.text.toString().endsWith("%")|| expression.text.toString().endsWith("/")||expression.text.toString().endsWith("*")||expression.text.toString().endsWith("+")||expression.text.toString().endsWith("-")||expression.text.toString().endsWith(".")){
                str = expression.text.toString()
                expressionText(str)
                Log.d(TAG, "нажато divide")
            } else{
                str = expression.text.toString() + "/"
                expressionText(str)
                Log.d(TAG, "нажато divide")
            }
        }
        multiply.setOnClickListener {
            if (expression.text.toString().endsWith("%")|| expression.text.toString().endsWith("/")||expression.text.toString().endsWith("*")||expression.text.toString().endsWith("+")||expression.text.toString().endsWith("-")||expression.text.toString().endsWith(".")){
                str = expression.text.toString()
                expressionText(str)
                Log.d(TAG, "нажато multiply")
            } else{
                str = expression.text.toString() + "*"
                expressionText(str)
                Log.d(TAG, "нажато multiply")
            }
        }
        add.setOnClickListener {
            if (expression.text.toString().endsWith("%")|| expression.text.toString().endsWith("/")||expression.text.toString().endsWith("*")||expression.text.toString().endsWith("+")||expression.text.toString().endsWith("-")||expression.text.toString().endsWith(".")){
                str = expression.text.toString()
                expressionText(str)
                Log.d(TAG, "нажато add")
            } else{
                str = expression.text.toString() + "+"
                expressionText(str)
                Log.d(TAG, "нажато add")
            }
        }
        subtract.setOnClickListener {
            if (expression.text.toString().endsWith("%")|| expression.text.toString().endsWith("/")||expression.text.toString().endsWith("*")||expression.text.toString().endsWith("+")||expression.text.toString().endsWith("-")||expression.text.toString().endsWith(".")){
            str = expression.text.toString()
            expressionText(str)
            Log.d(TAG, "нажато subtract")
        } else{
            str = expression.text.toString() + "-"
            expressionText(str)
                Log.d(TAG, "нажато subtract")
            } }
        equal.setOnClickListener {
            expression.textSize = 60F
            result.textSize = 30F
        }
        dot.setOnClickListener {
            if (expression.text.toString().endsWith("%")|| expression.text.toString().endsWith("/")||expression.text.toString().endsWith("*")||expression.text.toString().endsWith("+")||expression.text.toString().endsWith("-")||expression.text.toString().endsWith(".")){
                str = expression.text.toString()
                expressionText(str)
                Log.d(TAG, "нажато dot")
            } else{
                str = expression.text.toString() + "."
                expressionText(str)
                Log.d(TAG, "нажато dot")

            }
        }
        zero.setOnClickListener {
            if (expression.text.toString().startsWith("0")){
                str = expression.text.toString().replace("0", "") + "0"
                expressionText(str)
                resultText()
                Log.d(TAG, "нажато zero")
            }else{
                str = expression.text.toString() + "0"
                expressionText(str)
                resultText()
                Log.d(TAG, "нажато zero")
            }
        }
        doubleZero.setOnClickListener {
            if (expression.text.toString().startsWith("0")){
                str = expression.text.toString().replace("0", "") + "00"
                expressionText(str)
                resultText()
                Log.d(TAG, "нажато doubleZero")
            }else{
                str = expression.text.toString() + "00"
                expressionText(str)
                resultText()
                Log.d(TAG, "нажато doubleZero")
            }
        }
        one.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "1"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато one")
        }else{
            str = expression.text.toString() + "1"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато one")
        } }
        two.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "2"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато two")
        }else{
            str = expression.text.toString() + "2"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато two")
        } }
        three.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "3"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато three")
        }else{
            str = expression.text.toString() + "3"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато three")
        } }
        four.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "4"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато four")
        }else{
            str = expression.text.toString() + "4"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато four")
        } }
        five.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "5"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато five")
        }else{
            str = expression.text.toString() + "5"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато five")
        } }
        six.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "6"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато six")
        }else{
            str = expression.text.toString() + "6"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато six")
        } }
        seven.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "7"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато seven")
        }else{
            str = expression.text.toString() + "7"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато seven")
        } }
        eight.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "8"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато eight")
        }else{
            str = expression.text.toString() + "8"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато eight")
        } }
        nine.setOnClickListener {  if (expression.text.toString().startsWith("0")){
            str = expression.text.toString().replace("0", "") + "9"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато nine")
        }else{
            str = expression.text.toString() + "9"
            expressionText(str)
            resultText()
            Log.d(TAG, "нажато nine")
        } }

        mplus.setOnClickListener {
            val exp = expression.text.toString()
            val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
            try {
                val res = engine.eval(exp)
                memory += (res.toString().replace(".0", "")).toDouble()
                Toast.makeText(this, "Додано до пам'ять", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "нажато M+")
            }catch (e: Exception){
                expression.text = expression.text.toString()
                result.text = expression.text.toString()
            }
        }
        mminus.setOnClickListener {
            val exp = expression.text.toString()
            val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
            try {
                val res = engine.eval(exp)
                memory -= (res.toString().replace(".0", "")).toDouble()
                Toast.makeText(this, "Віднято з пам'яті", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "нажато M-")
            }catch (e: Exception){
                expression.text = expression.text.toString()
                result.text = expression.text.toString()
            }

        }
        mr.setOnClickListener {
            if (expression.text.toString().startsWith("0")){
                if (memory == 0.0){
                    Log.d(TAG, "нажато MR")
                }else{
                    str = expression.text.toString().replace("0", "") + memory.toString().replace(".0", "")
                    expressionText(str)
                    resultText()
                    Toast.makeText(this, "Завантажено з значення пам'яті", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "нажато MR")
                }
            }else{
                if (memory == 0.0){
                    Log.d(TAG, "нажато MR")
                }else{
                    str = expression.text.toString() + memory.toString().replace(".0", "")
                    expressionText(str)
                    resultText()
                    Toast.makeText(this, "Завантажено з значення пам'яті", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "нажато MR")
                }
            }
        }
        mc.setOnClickListener {
            memory = 0.0
            Toast.makeText(this, "Очищено пам'ять", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "нажато MC")

        }


    }

    private fun  expressionText(str: String){
        expression.text = str
        val patternDataType: Pattern = Pattern.compile("[/*%+-]0[0-9]")
        if (patternDataType.matcher(str).find()){
            Log.e(TAG, "не відповідає жодному типу даних")
            result.text = "Помилка"
            eror = true

        }
        val patternDivideZero: Pattern = Pattern.compile("/0[/|%|*|-|+]?")
        if (patternDivideZero.matcher(str).find()){
            Log.e(TAG, "ділення на ноль")
            result.text = "Помилка"
            eror = true
        }
    }

    fun isNumeric(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }

    @SuppressLint("SetTextI18n")
    private fun resultText(){
        val exp = expression.text.toString()
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
        try {
            val res = engine.eval(exp)
            if (res.toString().endsWith(".0")){
                val text = res.toString().replace(".0", "")
               if (eror){
                   result.text = "Помилка"
                   expression.text = "Помилка"
                   Log.e(TAG, "Виведення помилки")
               }else{
                   result.text = "=$text"
                   Log.d(TAG, "Виведення результату")
               }
            }else{
                val text = "$res"
                if (eror){
                    result.text = "Помилка"
                    expression.text = "Помилка"
                    Log.e(TAG, "Виведення помилки")
                }else{
                    result.text = "=$text"
                    Log.d(TAG, "Виведення результату")

                }
            }
        }catch (e: Exception){
            expression.text = expression.text.toString()
            result.text = expression.text.toString()
        }

    }

    companion object{
        private val TAG = "XXXXX"
    }
}