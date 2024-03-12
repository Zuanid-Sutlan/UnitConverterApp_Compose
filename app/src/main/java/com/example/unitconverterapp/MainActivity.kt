package com.example.unitconverterapp

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverterUi()
                }
            }
        }
    }
}

@Composable
fun UnitConverterUi() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val conversationFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversationFactor = remember { mutableDoubleStateOf(1.00) }


    fun convertValue(){
        // ?: is an Elvis operator it is similar as if else
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversationFactor.doubleValue * 100 / oConversationFactor.doubleValue).roundToInt() / 100
        outputValue = "$result $outputUnit"

    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Unit Converter App",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(70.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertValue()},
            label = { Text(text = "Enter Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // input box
            Box {
                // input button
                OutlinedButton(onClick = {
                    inputExpanded = true
                })
                {
                    Text(text = inputUnit, modifier = Modifier.padding(5.dp))
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select source unit")
                }

                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }, modifier = Modifier) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        inputUnit = "Centimeters"
                        conversationFactor.doubleValue = 0.01
                        inputExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        inputUnit = "Meters"
                        conversationFactor.doubleValue = 1.0
                        inputExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Feet's") }, onClick = {
                        inputUnit = "Feet's"
                        conversationFactor.doubleValue = 0.3048
                        inputExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        inputUnit = "Millimeters"
                        conversationFactor.doubleValue = 0.001
                        inputExpanded = false
                    })
                }
            }


            Spacer(modifier = Modifier.width(16.dp))

            OutlinedButton(onClick = {
                outputExpanded = true
            })
            {
                Text(text = outputUnit, Modifier.padding(5.dp))
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select source unit")

                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        oConversationFactor.doubleValue = 0.01
                        outputExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        outputUnit = "Meters"
                        oConversationFactor.doubleValue = 1.0
                        outputExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Feet's") }, onClick = {
                        outputUnit = "Feet's"
                        oConversationFactor.doubleValue = 0.3048
                        outputExpanded = false
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        outputUnit = "Millimeters"
                        oConversationFactor.doubleValue = 0.001
                        outputExpanded = false
                    })
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { convertValue() }) {
            Text(text = "Result: $outputValue", fontSize = 18.sp)
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestingAndPreviewing() {
    UnitConverterAppTheme {
        UnitConverterUi()
    }
}

